package com.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.model.Map;
import com.model.Tile;
import com.visualisation.Renderable;
import com.visualisation.View;

public class MapReader {
	private static MapReader _instance;

	public static MapReader getInstance() {
		if (_instance == null) {
			_instance = new MapReader();
		}
		return _instance;
	}

	private MapReader() {
	}

	int[] flags = new int[] { 0B0001, // NORTH
			0B0010, // EAST
			0B0100, // SOUTH
			0B1000 // WEST
	};

	int entryDir = 0, exitDir = 0, pathSize = 0;
	
	public void loadMap(String mapName, Map map) {
		int entryDir = 0, exitDir = 0, pathSize = 0;
		int[][] parsedMap = parseFile(mapName);
		TileType[][] typedMap = getTypedMap(parsedMap);
		map.setPath(loadPath(parsedMap));
		map.setTileMap(createTiles(typedMap));
		map.setBuildingMap(createBuildingMap(parsedMap));
	}

	private int[][] parseFile(String mapName) {
		ArrayList<String> fileLines = null;
		try {
			fileLines = readMapFile("maps/" + mapName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Get dimensions of tile map
		String widthHeight = fileLines.get(0);
		String[] split = widthHeight.split(",");
		int width = Integer.parseInt(split[0]);
		int height = Integer.parseInt(split[1]);
		// Get the tile info
		int[][] intMap = new int[width][height];
		for (int y = 0; y < height; y++) {
			String line = fileLines.get(y + 1);
			String[] tiles = line.split(",");
			for (int x = 0; x < width; x++) {
				// Go through, check to see if the tile needs to be a part of
				// the path
				intMap[x][y] = Integer.parseInt(tiles[x]);
				if (intMap[x][y] > pathSize) {
					pathSize = intMap[x][y];
				}
			}
		}

		split = fileLines.get(height + 1).split(",");
		// TODO: Test, is passed in by reference??
		entryDir = Integer.parseInt(split[0]);
		exitDir = Integer.parseInt(split[1]);

		return intMap;
	}

	private ArrayList<String> readMapFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return (ArrayList<String>) Files.readAllLines(path);
	}

	private TileType[][] getTypedMap(int[][] parsedMap) {
		int width = parsedMap.length;
		int height = parsedMap[0].length;
		TileType[][] typedMap = new TileType[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int flag = 0;

				if(parsedMap[x][y] == 0)
				{
					typedMap[x][y] = TileType.NONE;
					continue;
				}
				
				//NORTH
				if (y < height - 1) {
					if (parsedMap[x][y + 1] != 0)
						flag |= flags[2];
				}
				//SOUTH
				if (y != 0) {
					if (parsedMap[x][y - 1] != 0)
						flag |= flags[0];
				}
				//EAST
				if (x < width - 1) {
					if (parsedMap[x + 1][y] != 0)
						flag |= flags[1];
				}
				//WEST
				if (x != 0) {
					if (parsedMap[x - 1][y] != 0) {
						flag |= flags[3];
					}
				}

				if (parsedMap[x][y] == 1) {
					flag |= flags[entryDir];
				} else if (parsedMap[x][y] == pathSize) {
					flag |= flags[exitDir];
				}

				typedMap[x][y] = getTileType(flag);
			}
		}
		return typedMap;
	}

	private TileType getTileType(int flag) {
		switch (flag) {
		case 0b0011:
			return TileType.NORTH_EAST;
		case 0b0101:
			return TileType.NORTH_SOUTH;
		case 0b1001:
			return TileType.WEST_NORTH;
		case 0b0110:
			return TileType.EAST_SOUTH;
		case 0b1010:
			return TileType.WEST_EAST;
		case 0b1100:
			return TileType.SOUTH_WEST;
		default:
			System.out.println("ERROR: No appropriate tile type found for tile.");
			System.out.println("This will be shown as a 'NONE' type. Fix this.");
			return TileType.NONE;
		}
	}

	private ArrayList<Vector2D> loadPath(int[][] parsedMap) {
		ArrayList<Vector2D> orderedPoints = getOrderedPathVectors(parsedMap);
		ArrayList<Vector2D> pathPoints = new ArrayList<>();

		Vector2D viewOffset = new Vector2D(View.SCALE/2, View.SCALE/2);
		//TODO: Use entry dir to evaluate prevPoint
		Vector2D entry = new Vector2D(0,0);
		switch(entryDir)
		{
		case 0:
			entry = Vector2D.add(orderedPoints.get(0), new Vector2D(0, -1)); 
			break;
		case 1:
			entry = Vector2D.add(orderedPoints.get(0), new Vector2D(-1, 0)); 
			break;
		case 2:
			entry = Vector2D.add(orderedPoints.get(0), new Vector2D(0, 1)); 
			break;
		case 3:
			entry = Vector2D.add(orderedPoints.get(0), new Vector2D(1, 0)); 
			break;
		}
		entry = Vector2D.mult(entry, View.SCALE).add(viewOffset);
		Vector2D exit = new Vector2D(0,0);
		
		for(int i = 0; i < orderedPoints.size() - 1; i++)
		{
			Vector2D point = orderedPoints.get(i);
			exit = orderedPoints.get(i+1);
			point = Vector2D.mult(point, View.SCALE).add(viewOffset);
			exit = Vector2D.mult(exit, View.SCALE).add(viewOffset);
			pathPoints.addAll(getTileControlPoints(entry, point, exit));
			
			entry = point;
		}
		
		//TODO: Use exit direction
		switch(exitDir)
		{
		case 0:
			exit = Vector2D.add(orderedPoints.get(orderedPoints.size() - 1), new Vector2D(0, -1)); 
			break;
		case 1:
			exit = Vector2D.add(orderedPoints.get(orderedPoints.size() - 1), new Vector2D(-1, 0)); 
			break;
		case 2:
			exit = Vector2D.add(orderedPoints.get(orderedPoints.size() - 1), new Vector2D(0, 1)); 
			break;
		case 3:
			exit = Vector2D.add(orderedPoints.get(orderedPoints.size() - 1), new Vector2D(1, 0)); 
			break;
		}
		exit = Vector2D.mult(exit, View.SCALE).add(viewOffset);
		Vector2D point = Vector2D.mult(orderedPoints.get(orderedPoints.size()-1), View.SCALE).add(viewOffset);
		pathPoints.addAll(getTileControlPoints(entry, point, exit));
		
		BezierPath bp = new BezierPath(pathPoints, 12);
		return bp.getPath();
	}
	
	private ArrayList<Vector2D> getTileControlPoints(Vector2D a, Vector2D b, Vector2D c)
	{
		ArrayList<Vector2D> controlPoints = new ArrayList<>();	
		Vector2D p0 = Vector2D.add(a,Vector2D.sub(b, a).div(2));
		Vector2D p1 = Vector2D.add(p0, Vector2D.sub(b, p0).div(2));
		Vector2D p3 = Vector2D.add(b, Vector2D.sub(c, b).div(2));
		Vector2D p2 = Vector2D.add(b, Vector2D.sub(p3, b).div(2));
		controlPoints.add(p0);
		controlPoints.add(p1);
		controlPoints.add(p2);
		controlPoints.add(p3);
		return controlPoints;
	}

	private ArrayList<Vector2D> getOrderedPathVectors(int[][] parsedMap) {
		ArrayList<Vector2D> orderedPoints = new ArrayList<>();
		orderedPoints.ensureCapacity(pathSize);
		for(int i = 0; i < pathSize; i++)
		{
			orderedPoints.add(new Vector2D());
		}
		for (int x = 0; x < parsedMap.length; x++) {
			for (int y = 0; y < parsedMap[x].length; y++) {
				if (parsedMap[x][y] != 0) {
					orderedPoints.set(parsedMap[x][y] - 1, new Vector2D(x, y));
				}
			}
		}
		return orderedPoints;
	}

	private Tile[][] createTiles(TileType[][] typedMap) {
		Tile[][] tileMap = new Tile[typedMap.length][typedMap[0].length];
		for (int x = 0; x < typedMap.length; x++) {
			for (int y = 0; y < typedMap[x].length; y++) {
				tileMap[x][y] = new Tile(associatedFileName(typedMap[x][y]), Renderable.Layer.GROUND,
						getTileLocation(x, y));
			}
		}

		return tileMap;
	}

	// Returns the screen position of the tile
	private Vector2D getTileLocation(int x, int y) {
		return new Vector2D(x, y).mult(View.SCALE);
	}

	private int[][] createBuildingMap(int[][] parsedMap) {
		int[][] buildingMap = parsedMap;
		for (int[] row : buildingMap) {
			for (int i : row) {
				if (i != 0) {
					i = -1;
				}
			}
		}
		return buildingMap;
	}

	enum TileType {
		NONE, WEST_EAST, NORTH_SOUTH, WEST_NORTH, NORTH_EAST, EAST_SOUTH, SOUTH_WEST
	}

	private static String associatedFileName(TileType t) {
		switch (t) {
		case NONE:
			return "none.png";
		case WEST_EAST:
			return "west_east.png";
		case NORTH_SOUTH:
			return "north_south.png";
		case WEST_NORTH:
			return "west_north.png";
		case NORTH_EAST:
			return "north_east.png";
		case EAST_SOUTH:
			return "east_south.png";
		case SOUTH_WEST:
			return "south_west.png";
		}
		return null;
	}
}

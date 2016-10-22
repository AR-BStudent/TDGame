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

	private static ArrayList<String> readMapFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		return (ArrayList<String>) Files.readAllLines(path);
	}

	public static void setMap(String mapName, Map map) {
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
		int[][] intMap = new int[width][height];
		int pathSize = 0;
		// Get the tile info
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

		// Get entry/exit direction
		split = fileLines.get(height + 1).split(",");
		int entryDirection = Integer.parseInt(split[0]);
		int exitDirection = Integer.parseInt(split[1]);

		// System.out.println("entryDir = " + entryDirection + ", exitDir = " +
		// exitDirection);
		// Add 2 to path size to allow for a prior and post positioning
		Vector2D[] pathPoints = new Vector2D[pathSize + 2];

		Tile[][] newMap = new Tile[width][height];
		int[] flags = new int[] { 0B0001, // UP
				0B0010, // RIGHT
				0B0100, // DOWN
				0B1000 // LEFT
		};
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				Vector2D tileLocation = new Vector2D(x, y).mult(View.SCALE);

				if (intMap[x][y] == 0) {
					newMap[x][y] = new Tile(associatedFileName(TileType.NONE), Renderable.Layer.GROUND, tileLocation);
					continue;
				}

				Vector2D vec = new Vector2D(x, y).mult(View.SCALE);
				pathPoints[intMap[x][y]] = vec;

				int flag = 0;

				if (y < height - 1) {
					if (intMap[x][y + 1] != 0)
						flag |= flags[2];
				}
				if (y != 0) {
					if (intMap[x][y - 1] != 0)
						flag |= flags[0];
				}
				if (x < width - 1) {
					if (intMap[x + 1][y] != 0)
						flag |= flags[1];
				}
				if (x != 0) {
					if (intMap[x - 1][y] != 0) {
						flag |= flags[3];
					}
				}

				if (intMap[x][y] == 1) {
					flag |= flags[entryDirection];
				} else if (intMap[x][y] == pathSize) {
					flag |= flags[exitDirection];
				}
				String name = "NONE";

				switch (flag) {
				case 0b0011:
					name = associatedFileName(TileType.NORTH_EAST);
					break;
				case 0b0101:
					name = associatedFileName(TileType.NORTH_SOUTH);
					break;
				case 0b1001:
					name = associatedFileName(TileType.WEST_NORTH);
					break;
				case 0b0110:
					name = associatedFileName(TileType.EAST_SOUTH);
					break;
				case 0b1010:
					name = associatedFileName(TileType.WEST_EAST);
					break;
				case 0b1100:
					name = associatedFileName(TileType.SOUTH_WEST);
					break;
				default:
					System.out.println("ERROR: No appropriate tile type found for tile [" + x + "," + y + "]");
					System.out.println("This will be shown as a 'NONE' type. Fix this.");
					name = "none.png";
				}

				newMap[x][y] = new Tile(name, Renderable.Layer.GROUND, tileLocation);
			}
		}
		map.setTileMap(newMap);
		switch (entryDirection) {
		case 0:
			pathPoints[0] = Vector2D.sub(pathPoints[1], new Vector2D(0, View.SCALE));
			break;
		case 1:
			pathPoints[0] = Vector2D.sub(pathPoints[1], new Vector2D(View.SCALE, 0));
			break;
		case 2:
			pathPoints[0] = Vector2D.sub(pathPoints[1], new Vector2D(0, -View.SCALE));
			break;
		case 3:
			pathPoints[0] = Vector2D.sub(pathPoints[1], new Vector2D(-View.SCALE, 0));
			break;
		}
		switch (exitDirection) {
		case 0:
			pathPoints[pathSize + 1] = Vector2D.sub(pathPoints[pathSize], new Vector2D(0, View.SCALE));
			break;
		case 1:
			pathPoints[pathSize + 1] = Vector2D.sub(pathPoints[pathSize], new Vector2D(View.SCALE, 0));
			break;
		case 2:
			pathPoints[pathSize + 1] = Vector2D.sub(pathPoints[pathSize], new Vector2D(0, -View.SCALE));
			break;
		case 3:
			pathPoints[pathSize + 1] = Vector2D.sub(pathPoints[pathSize], new Vector2D(-View.SCALE, 0));
			break;
		}
		map.setPath(pathPoints);
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

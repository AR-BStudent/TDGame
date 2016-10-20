package com.model;

import com.utility.MapReader;
import com.utility.Vector2D;

public class Map {
	Tile[][] tileMap;
	Path mapPath;

	public Map()
	{
		//Load in from a file
		//For now...
		mapPath = new Path();
		MapReader.setMap("map_01.txt", this);
	}
	
	public void setTileMap(Tile[][] newMap)
	{
		tileMap = newMap;
	}
	
	public Tile[][] getTileMap()
	{
		return tileMap;
	}
	
	public Path getPath()
	{
		return mapPath;
	}
	
	public void setPath(Vector2D...points)
	{
		mapPath.clear();
		mapPath.addPoints(points);
	}
}

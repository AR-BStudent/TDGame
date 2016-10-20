package com.model;

import com.utility.MapReader;

public class Map {
	Tile[][] tileMap;

	public Map()
	{
		//Load in from a file
		//For now...
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
}

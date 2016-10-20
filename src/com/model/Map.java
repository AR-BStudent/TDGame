package com.model;

import com.utility.MapReader;

public class Map {
	Tile[][] tileMap;

	public Map()
	{
		//Load in from a file
		//For now...
		MapReader.setMap("map_01", this);
	}
	
	public void setTileMap(Tile[][] newMap)
	{
		tileMap = newMap;
	}
}

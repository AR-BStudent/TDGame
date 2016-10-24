package com.model;

import java.util.ArrayList;

import com.utility.MapReader;
import com.utility.Vector2D;

public class Map {
	Tile[][] tileMap;
	int[][] buildingMap;
	Path mapPath;

	public Map(String fileName)
	{
		//Load in from a file
		//For now...
		mapPath = new Path();
		MapReader.getInstance().loadMap(fileName, this);
	}
	
	public void setTileMap(Tile[][] newMap)
	{
		tileMap = newMap;
	}
	
	public Tile[][] getTileMap()
	{
		return tileMap;
	}
	
	public void setBuildingMap(int[][] newMap)
	{
		buildingMap = newMap;
	}
	
	//Used to remove a building from the tile map
	public void remove(Vector2D gridLocation)
	{
		if(buildingMap[(int) gridLocation.x][(int) gridLocation.y] != -1)
			buildingMap[(int) gridLocation.x][(int) gridLocation.y] = 0;
	}
	
	public boolean place(Vector2D gridLocation, int buildingID)
	{
		if(gridLocation.x >= buildingMap.length || gridLocation.y >= buildingMap[0].length)
			return false;
		if(buildingMap[(int) gridLocation.x][(int) gridLocation.y] == 0)
		{
			buildingMap[(int) gridLocation.x][(int) gridLocation.y] = buildingID;
			return true;
		}
		return false;
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
	
	public void setPath(ArrayList<Vector2D> points)
	{
		mapPath.clear();
		mapPath.addAll(points);
	}
}

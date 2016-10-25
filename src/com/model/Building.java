package com.model;

import com.utility.Vector2D;
import com.visualisation.Renderable;
import com.visualisation.View;

public abstract class Building extends Renderable {

	//TODO: Building should supply imagePath to constructor
	public Building(String _imagePath, Vector2D gridLocation) {
		super(_imagePath, Layer.UNIT);
		this.gridLocation = gridLocation;
		location = new Vector2D(gridLocation.x, gridLocation.y);
	}
	
	protected Vector2D gridLocation;
	protected int ID;
	
	public int getID()
	{
		return ID;
	}
	
	public void recycle(Map map)
	{
		map.remove(gridLocation);
	}
}

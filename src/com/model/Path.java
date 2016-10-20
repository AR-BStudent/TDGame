package com.model;

import java.util.ArrayList;
import com.utility.Vector2D;

public class Path {
	private ArrayList<Vector2D> points;
	
	public Path()
	{
		points = new ArrayList<Vector2D>();
	}
	
	public void addPoints(Vector2D...points)
	{
		for(Vector2D p : points)
		{
			this.points.add(p);
		}
	}
	
	public void clear()
	{
		points.clear();
	}
}

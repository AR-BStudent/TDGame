package com.utility;

public class Vector2D {
	public float x,y;
	
	public float getDistance(Vector2D otherVector)
	{
		float distX = otherVector.x - x;
		float distY = otherVector.y - y;
		
		return (float) Math.sqrt((distX * distX) + (distY * distY));
	}
	
	public float getMagnitude()
	{
		return (float) Math.sqrt((x * x) + (y * y));
	}
	
	//TODO: Implement these functions
	
	public float getSqrMagnitude()
	{
		return (x*x) + (y*y);
	}
	
	/*
	//Note - this will normalize the vector
	public Vector2D normalize();
	
	public Vector2D mult();
	
	public Vector2D add();
	
	public Vector2D sub();
	
	public Vector2D div();
	
	public Vector2D limit();
	
	public String toString();
	*/
}

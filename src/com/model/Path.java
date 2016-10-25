package com.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import com.utility.Vector2D;
import com.visualisation.View;

public class Path {

	public ArrayList<Vector2D> points = new ArrayList<>();
	public float radius = 1f;

	public void addPoints(Vector2D... p) {
		points.addAll(Arrays.asList(p));
	}
	
	public void addAll(ArrayList<Vector2D> points)
	{
		this.points.addAll(points);
	}

	public void clear() {
		points.clear();
	}

	public Vector2D getStart() {
		return points.get(0);
	}

	public Vector2D getEnd() {
		return points.get(points.size() - 1);
	}

	public void debugDraw(Graphics2D g) {
		for (int i = 0; i < points.size() - 1; i++) {
			Vector2D point = Vector2D.mult(points.get(i), View.SCALE);
			Vector2D point2 = Vector2D.mult(points.get(i + 1), View.SCALE);
			g.drawLine((int)point.x, (int)point.y, (int)point2.x, (int)point2.y);
		}
	}
}

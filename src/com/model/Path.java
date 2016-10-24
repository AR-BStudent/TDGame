package com.model;

import java.util.ArrayList;
import java.util.Arrays;

import com.utility.Vector2D;

public class Path {

	public ArrayList<Vector2D> points = new ArrayList<>();
	public float radius = 32;

	public void addPoints(Vector2D... p) {
		points.addAll(Arrays.asList(p));
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

}

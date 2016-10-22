package com.model;

import java.util.ArrayList;
import com.utility.Vector2D;

public class Path {

	public float radius = 10;
	private ArrayList<Vector2D> points;
	private int currentPoint = 0;

	public Path() {
		points = new ArrayList<>();
	}

	public Path(Vector2D... points) {
		this();
		addPoints(points);
	}

	public void addPoints(Vector2D... points) {
		for (Vector2D p : points) {
			this.points.add(p);
		}
	}

	public void clear() {
		points.clear();
	}

	public Vector2D currentPoint() {
		return points.get(currentPoint);
	}

	public Vector2D nextPoint() {
		if (currentPoint < points.size() - 1) {
			currentPoint++;
			return points.get(currentPoint);
		} else {
			return points.get(points.size() - 1);
		}
	}

	public Vector2D end() {
		return points.get(points.size() - 1);
	}

}

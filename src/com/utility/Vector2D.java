package com.utility;

public class Vector2D {

	public float x;
	public float y;

	public Vector2D(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public Vector2D() {
		x = 0;
		y = 0;
	}

	public Vector2D(Vector2D vector) {
		x = vector.x;
		y = vector.y;
	}

	public float dist(Vector2D v) {
		Vector2D diff = Vector2D.sub(v, this);
		return diff.mag();
	}

	public float mag() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float sqrMag() {
		return x * x + y * y;
	}
	
	//TODO: daisy chainging

	public void add(Vector2D v) {
		x += v.x;
		y += v.y;
	}

	public void sub(Vector2D v) {
		x -= v.x;
		y -= v.y;
	}

	public static Vector2D add(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x + b.x, a.y + b.y);
		return c;
	}

	public static Vector2D sub(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x - b.x, a.y - b.y);
		return c;
	}

	public void mult(Vector2D v) {
		x *= v.x;
		y *= v.y;
	}

	public void mult(float m) {
		x *= m;
		y *= m;
	}

	public void div(float d) {
		x /= d;
		y /= d;
	}

	public void div(Vector2D v) {
		x /= v.x;
		y /= v.y;
	}

	public void normalize() {
		float m = mag();
		if (m != 0) {
			div(m);
		}
	}

	public void limit(float max) {
		if (mag() > max) {
			setMag(max);
		}
	}

	public void setMag(float m) {
		normalize();
		mult(m);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}

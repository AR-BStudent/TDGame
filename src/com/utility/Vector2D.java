package com.utility;

public class Vector2D {

	public float x = 0;
	public float y = 0;

	public Vector2D(float _x, float _y) {
		x = _x;
		y = _y;
	}

	public Vector2D() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
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

	public Vector2D add(Vector2D v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public Vector2D sub(Vector2D v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	public static Vector2D add(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x + b.x, a.y + b.y);
		return c;
	}

	public static Vector2D sub(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x - b.x, a.y - b.y);
		return c;
	}

	public static Vector2D div(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x / b.x, a.y / b.y);
		return c;
	}

	public static Vector2D mult(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D(a.x * b.x, a.y * b.y);
		return c;
	}

	public static Vector2D normalize(Vector2D a) {
		Vector2D c = copy(a);
		c.normalize();
		return c;
	}

	public static Vector2D copy(Vector2D a) {
		return new Vector2D(a.x, a.y);
	}

	public Vector2D copy() {
		return new Vector2D(x, y);
	}

	public Vector2D mult(Vector2D v) {
		x *= v.x;
		y *= v.y;
		return this;
	}

	public Vector2D mult(float m) {
		x *= m;
		y *= m;
		return this;
	}

	public Vector2D div(float d) {
		x /= d;
		y /= d;
		return this;
	}

	public Vector2D div(Vector2D v) {
		x /= v.x;
		y /= v.y;
		return this;
	}

	public Vector2D normalize() {
		float m = mag();
		if (m != 0) {
			div(m);
		}
		return this;
	}

	public Vector2D limit(float max) {
		if (mag() > max) {
			setMag(max);
		}
		return this;
	}

	public Vector2D setMag(float m) {
		normalize();
		mult(m);
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}

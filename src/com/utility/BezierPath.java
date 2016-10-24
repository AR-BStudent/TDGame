package com.utility;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BezierPath {

	ArrayList<Vector2D> controlPoints;
	int segmentsPerCurve;

	public BezierPath(ArrayList<Vector2D> _controlPoints, int segments) {
		controlPoints = _controlPoints;
		segmentsPerCurve = segments;
	}

	public ArrayList<Vector2D> getPath() {
		ArrayList<Vector2D> path = new ArrayList<>();

		for (int i = 0; i < controlPoints.size() - 3; i += 3) {
			Vector2D p0 = controlPoints.get(i);
			Vector2D p1 = controlPoints.get(i + 1);
			Vector2D p2 = controlPoints.get(i + 2);
			Vector2D p3 = controlPoints.get(i + 3);

			if (i == 0) {
				path.add(CalculateBezierPoint(0, p0, p1, p2, p3));
			}

			for (int j = 1; j <= segmentsPerCurve; j++) {
				float t = j / (float) segmentsPerCurve;
				path.add(CalculateBezierPoint(t, p0, p1, p2, p3));
			}
		}
		return path;
	}

	private Vector2D CalculateBezierPoint(float t, Vector2D p0, Vector2D p1, Vector2D p2, Vector2D p3) {
		float u = 1 - t;
		float tt = t * t;
		float uu = u * u;
		float uuu = uu * u;
		float ttt = tt * t;

		Vector2D p = Vector2D.mult(p0, uuu);

		p.add(Vector2D.mult(p1, 3 * uu * t)); // second term
		p.add(Vector2D.mult(p2, 3 * u * tt)); // third term
		p.add(Vector2D.mult(p3, ttt)); // fourth term

		return p;
	}

	public void drawCurve(Graphics2D g, ArrayList<Vector2D> ps) {
		for (Vector2D p : ps) {
			g.draw(new Ellipse2D.Float(p.x, p.y, 4, 4));
		}
	}

}

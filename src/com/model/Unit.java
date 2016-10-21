package com.model;

import java.util.Collection;

import com.utility.Vector2D;
import com.visualisation.Renderable;

public class Unit extends Renderable {

	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	private float maxSpeed = 6;
	private float maxForce = 0.5f;
	private float mass = 1;
	private float radius = 8;
//	private Collection<Unit> neighbours;

	private float smoothedAngle;
	private float smoothing = 5f;

	public Unit(Vector2D pos) {
		this();
		location = pos;
	}

	public Unit() {
		super(Layer.UNIT);
	}

	@Override
	public void update() {
		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);

		applyRotation();
		// preventOverlap(neighbours);
	}

	private void applyRotation() {
		smoothedAngle += (getHeading() - smoothedAngle) / smoothing;
		setRotation(smoothedAngle);
	}

	public void applyForce(Vector2D force) {
		force.div(mass);
		acceleration.add(force);
	}

	public Vector2D seek(Vector2D target) {
		Vector2D desired = Vector2D.sub(target, location);
		desired.setMag(maxSpeed);
		return steer(desired);
	}

	public Vector2D arrive(Vector2D target) {
		Vector2D desired = Vector2D.sub(target, location);

		float dsq = desired.sqrMag();

		float minStopDist = getStoppingDist();

		desired.normalize();

		if (dsq < minStopDist * minStopDist) {
			float m = ((float) Math.sqrt(dsq) / minStopDist) * maxSpeed;
			desired.mult(m);
		} else {
			desired.mult(maxSpeed);
		}

		return steer(desired);
	}

	private float getStoppingDist() {
		return (maxSpeed * maxSpeed) / (2 * maxForce);
	}

	private Vector2D steer(Vector2D desired) {
		Vector2D steer = Vector2D.sub(desired, velocity);
		steer.limit(maxForce);
		return steer;
	}

	// Radians
	public float getHeading() {
		return (float) Math.atan2(velocity.y, velocity.x);
	}

	// Basic N.B. will need separate flocking methods
	public Vector2D follow(Path p) {
		Vector2D target = p.currentPoint();

		Vector2D dir = Vector2D.sub(target, location);
		float dsq = dir.sqrMag();

		float stopD = getStoppingDist();

		if (dsq < stopD * stopD) {
			target = p.nextPoint();
		}

		if (target == p.end()) {
			return arrive(target);
		} else {
			return seek(target);
		}
	}

	public Vector2D separate(Collection<Unit> units) {
		// todo neigbour dist based on separation and radius
		float desiredSeparation = 100;

		Vector2D sum = new Vector2D();
		int count = 0;

		for (Unit u : units) {
			Vector2D dir = Vector2D.sub(u.location, location);
			float d = dir.mag();
			if (d > 0 && d < desiredSeparation) {
				Vector2D diff = Vector2D.sub(location, u.location);
				diff.normalize();
				diff.div(d);
				sum.add(diff);
				count++;
			}
		}

		if (count > 0) {
			sum.div(count);
			sum.normalize();
			sum.mult(maxForce);
			Vector2D steer = Vector2D.sub(sum, velocity);
			steer.limit(maxForce);
			return steer;
		}
		return new Vector2D();
	}

	public Vector2D cohesion(Collection<Unit> units) {
		float neighbourDist = 100;
		Vector2D sum = new Vector2D();
		int count = 0;

		for (Unit u : units) {
			Vector2D dir = Vector2D.sub(u.location, location);
			float d = dir.mag();
			if (d > 0 && d < neighbourDist) {
				sum.add(u.location);
				count++;
			}
		}
		if (count > 0) {
			sum.div(count);
			return seek(sum);
		} else {
			return new Vector2D();
		}
	}

	public void applyBehaviours(Path p, Collection<Unit> units) {
		Vector2D follow = follow(p);
		Vector2D sep = separate(units);
		Vector2D coh = cohesion(units);

		follow.mult(1f);
		sep.mult(1.9f);
		coh.mult(1f);

		Vector2D force = new Vector2D();
		force.add(follow);
		force.add(sep);
		force.add(coh);

		force.limit(maxForce);

		applyForce(force);

//		neighbours = units;
		// preventOverlap(units);
	}

	// public void preventOverlap(Collection<Unit> units) {
	// for (Unit other : units) {
	// Vector2D dir = Vector2D.sub(location, other.location);
	// float dsq = dir.sqrMag();
	// float minDist = radius + other.radius;
	// if (dsq <= minDist * minDist) {
	// dir.setMag(minDist);
	// location.add(dir);
	// }
	//
	// }
	// }

}

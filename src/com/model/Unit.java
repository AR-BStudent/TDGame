package com.model;

import java.beans.PropertyVetoException;

import com.utility.Vector2D;
import com.visualisation.Renderable;

public class Unit extends Renderable {

	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	private float maxSpeed = 4;
	private float maxForce = 0.2f;
	private float mass = 1;

	public Unit(Vector2D pos) {
		location = pos;
	}

	public Unit() {

	}

	@Override
	public void update() {
		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);
	}

	public void applyForce(Vector2D force) {
		force.div(mass);
		acceleration.add(force);
	}

	public void seek(Vector2D target) {
		Vector2D desired = Vector2D.sub(target, location);
		desired.setMag(maxSpeed);
		steer(desired);
	}

	public void arrive(Vector2D target) {
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

		steer(desired);
	}

	private float getStoppingDist() {
		return (maxSpeed * maxSpeed) / (2 * maxForce);
	}

	private void steer(Vector2D desired) {
		Vector2D steer = Vector2D.sub(desired, velocity);
		steer.limit(maxForce);
		applyForce(steer);
	}

	// Radians
	public float getHeading() {
		return (float) Math.atan2(velocity.y, velocity.x);
	}

	// Basic N.B. will need separate flocking methods
	public void follow(Path p) {
		Vector2D target = p.currentPoint();

		Vector2D dir = Vector2D.sub(target, location);
		float dsq = dir.sqrMag();

		float stopD = getStoppingDist();

		if (dsq < stopD * stopD) {
			target = p.nextPoint();
		}

		if (target == p.end()) {
			arrive(target);
		} else {
			seek(target);
		}
	}

}

package com.model;

import java.time.LocalDate;

import com.utility.Vector2D;

public class Unit extends GameObject {

	private Vector2D location;
	private Vector2D velocity;
	private Vector2D acceleration;
	private float maxSpeed = 5;
	private float maxForce = 4;
	private float mass = 1;

	public Unit(Vector2D pos) {

	}

	public Unit() {

	}

	public void update(float deltaT) {
		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);
	}

	public void applyForce(Vector2D force) {
		force.div(mass);
		acceleration.add(force);
	}
	
	public void seek(Vector2D target){
		Vector2D desired = Vector2D.sub(target, location);
		desired.setMag(maxSpeed);
		Vector2D steer = Vector2D.sub(desired, velocity);
		steer.limit(maxForce);
		applyForce(steer);
	}

}

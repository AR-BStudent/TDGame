package com.model;

import java.time.LocalDate;

import com.utility.Vector2D;

public class Unit extends GameObject {

	private Vector2D location = new Vector2D();
	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	private float maxSpeed = 5;
	private float maxForce = 4;
	private float mass = 1;

	public Unit(Vector2D pos) {

	}

	public Unit() {
		
	}

	//TODO: Use delta T
	@Override
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

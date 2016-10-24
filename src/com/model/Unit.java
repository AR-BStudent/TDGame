package com.model;

import java.util.ArrayList;

import com.utility.Vector2D;
import com.visualisation.Renderable;

public class Unit extends Renderable implements Updateable {

	private Vector2D velocity = new Vector2D();
	private Vector2D acceleration = new Vector2D();
	private float maxSpeed = 6;
	private float maxForce = 0.5f;
	private float mass = 1;
	private float radius = 8;
	private Squad squad;
	private float smoothedAngle;
	private float smoothing = 5f;

	public Unit(Vector2D pos) {
		this();
		location = pos;
		registerUpdates();
	}

	public Unit() {
		super("towerDefense_tile248.png", Layer.UNIT);
	}

	@Override
	public void update() {
		applyBehaviours();

		velocity.add(acceleration);
		velocity.limit(maxSpeed);
		location.add(velocity);
		acceleration.mult(0);

		applyRotation();
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

	public Vector2D follow(Path p) {
		Vector2D predict = velocity.copy();
		predict.normalize();
		predict.mult(50);
		Vector2D predictPos = Vector2D.add(location, predict);

		Vector2D target = null;

		float worldRecord = 9999999999f;

		for (int i = 0; i < p.points.size() - 1; i++) {
			Vector2D a = p.points.get(i);
			Vector2D b = p.points.get(i + 1);

			Vector2D normalPoint = getNormalPoint(predictPos, a, b);

			if (normalPoint.x < Math.min(a.x, b.x) || normalPoint.x > Math.max(a.x, b.x)) {
				normalPoint = b.copy();
			}

			float distance = Vector2D.dist(predictPos, normalPoint);

			if (distance < worldRecord) {
				worldRecord = distance;
				Vector2D dir = Vector2D.sub(b, a);
				dir.normalize();
				dir.mult(10);
				target = normalPoint.copy();
				target.add(dir);
			}
		}

		if (worldRecord > p.radius) {
			return seek(target);
		} else {
			return new Vector2D();
		}
	}

	private Vector2D getNormalPoint(Vector2D p, Vector2D a, Vector2D b) {
		Vector2D ap = Vector2D.sub(p, a);
		Vector2D ab = Vector2D.sub(b, a);
		ab.normalize();
		ab.mult(ap.dot(ab));
		Vector2D normalPoint = Vector2D.add(a, ab);
		return normalPoint;
	}

	// Basic N.B. will need separate flocking methods
	// public Vector2D follow() {
	// Path path = squad.getPath();
	//
	// if (path == null) {
	// return new Vector2D();
	// }
	//
	// Vector2D target = path.currentPoint();
	//
	// Vector2D dir = Vector2D.sub(target, location);
	// float dsq = dir.sqrMag();
	//
	// float stopD = getStoppingDist();
	//
	// if (dsq < stopD * stopD) {
	// target = path.nextPoint();
	// }
	//
	// if (target == path.end()) {
	// return arrive(target);
	// } else {
	// return seek(target);
	// }
	// }

	public Vector2D separate() {
		// todo neigbour dist based on separation and radius
		float desiredSeparation = 100;

		Vector2D sum = new Vector2D();
		int count = 0;

		ArrayList<Unit> neighbours = squad.getMembers();

		for (Unit u : neighbours) {
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

	public Vector2D cohesion() {
		float neighbourDist = 100;
		Vector2D sum = new Vector2D();
		int count = 0;

		ArrayList<Unit> neighbours = squad.getMembers();

		for (Unit u : neighbours) {
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

	public void applyBehaviours() {
		Vector2D follow = follow(squad.getPath());
		Vector2D sep = separate();
		Vector2D coh = cohesion();

		follow.mult(1f);
		sep.mult(1.9f);
		coh.mult(1f);

		Vector2D force = new Vector2D();
		force.add(follow);
		// force.add(sep);
		// force.add(coh);

		force.limit(maxForce);

		applyForce(force);
	}

	public Squad getSquad() {
		return squad;
	}

	public void setSquad(Squad squad) {
		this.squad = squad;
	}

}

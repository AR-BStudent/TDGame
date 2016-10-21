package com.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Squad {

	private ArrayList<Unit> members;
	private Path path;

	@SafeVarargs
	public Squad(Unit... _members) {
		members = new ArrayList<>(Arrays.asList(_members));
		setSquad();
	}

	public Squad() {
		members = new ArrayList<>();
	}

	public Squad(ArrayList<Unit> _members) {
		members = new ArrayList<>(_members);
		setSquad();
	}

	public void addMembers(Unit... _members) {
		members.addAll(Arrays.asList(_members));
		setSquad();
	}

	public void removeMembers(Unit... _members) {
		members.removeAll(Arrays.asList(_members));
	}

	private void setSquad() {
		for (Unit m : members) {
			m.setSquad(this);
		}
	}

	public void setPath(Path p) {
		path = p;
	}

	public Path getPath() {
		return path;
	}

	public ArrayList<Unit> getMembers() {
		return members;
	}

	public void update() {
		for (Unit m : members) {
			m.update();
		}
	}

}

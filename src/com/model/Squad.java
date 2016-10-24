package com.model;

import java.util.ArrayList;
import java.util.Arrays;

import com.utility.RandomUtil;
import com.utility.Vector2D;

public class Squad<T extends Unit> {

	private ArrayList<T> members;
	private Path path;

	public Squad() {
		members = new ArrayList<>();
	}

	public Squad(ArrayList<T> _members) {
		members = new ArrayList<>(_members);
		setSquad();
	}

	@SuppressWarnings("unchecked")
	public Squad(Class<T> unitType, int count, Vector2D location) {
		members = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			//spacing units out
			Vector2D spawn = new Vector2D(RandomUtil.randFloatRange(0, 64), RandomUtil.randFloatRange(0, 64));
			T m = (T) new Unit(new Vector2D(0,0));

			members.add(m);
			m.setSquad(this);
		}
	}

	public void addMembers(T... _members) {
		members.addAll(Arrays.asList(_members));
		setSquad();
	}

	public void removeMembers(T... _members) {
		members.removeAll(Arrays.asList(_members));
	}

	private void setSquad() {
		for (T m : members) {
			m.setSquad(this);
		}
	}

	public void setPath(Path p) {
		path = p;
	}

	public Path getPath() {
		return path;
	}

	public ArrayList<T> getMembers() {
		return members;
	}

	public void update() {
		for (T m : members) {
			m.update();
		}
	}

}

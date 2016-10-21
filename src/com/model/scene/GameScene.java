package com.model.scene;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.model.Map;
import com.model.Path;
import com.model.Squad;
import com.model.Tile;
import com.model.Unit;
import com.utility.Vector2D;
import com.visualisation.Renderable;

public class GameScene extends Scene {

	Map map;
	private Squad squad;

	@Override
	public void update() {
		squad.update();
	}

	@Override
	public void onEnter() {
		map = new Map("map_02.txt");

		Tile[][] tiles = map.getTileMap();
		for (Tile[] rows : tiles) {
			for (Tile t : rows) {
				renderables.add(t);
			}
		}

		ArrayList<Unit> units = new ArrayList<>();

		for (int i = 0; i < 10; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, 100);
			int y = ThreadLocalRandom.current().nextInt(0, 100);
			Unit u = new Unit(new Vector2D(x, y));
			u.setImage("towerDefense_tile248.png");
			units.add(u);
			renderables.add(u);
		}

		squad = new Squad(units);
		squad.setPath(map.getPath());
	}

	@Override
	public void onExit() {

	}

}

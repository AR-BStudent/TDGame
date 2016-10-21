package com.model.scene;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.model.Map;
import com.model.Path;
import com.model.Soldier;
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
		
		int chance = ThreadLocalRandom.current().nextInt(0, 100);
		if(chance > 95){
			int i = ThreadLocalRandom.current().nextInt(0, squad.getMembers().size());
			((Soldier)squad.getMembers().get(i)).takeDamage(1000);
			System.out.println("Removed soldier");
		}
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
			Soldier u = new Soldier();
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

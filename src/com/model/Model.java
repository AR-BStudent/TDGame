package com.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import com.utility.Vector2D;
import com.visualisation.Renderable;
import com.visualisation.View;

public class Model extends Thread{

	private static PriorityQueue<Renderable> renderables = new PriorityQueue<>();
	private static Map map;
	private static Model _instance = null;

	public static Model getInstance() {
		if (_instance == null) {
			_instance = new Model();
			_instance.init();
		}
		return _instance;
	}

	private void init() {
		map = new Map("map_02.txt");
	}

	public PriorityQueue<Renderable> getRenderables() {
		return renderables;
	}

	public Map getMap() {
		return map;
	}

	public void addRenderable(Renderable r) {
		if (r == null) {
			System.out.println("Cannot add null object");
			return;
		}
		renderables.add(r);
	}

	public void run() {
		ArrayList<Unit> units = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, 100);
			int y = ThreadLocalRandom.current().nextInt(0, 100);
			Unit u = new Unit(new Vector2D(x, y));
			u.setImage("towerDefense_tile248.png");
			units.add(u);
		}
		
		Squad squad = new Squad(units);

		squad.setPath( map.getPath());

		long lastTime = System.currentTimeMillis();
		boolean run = true;
		float updateRatePerSecond = 60f;
		float updateInterval = 1000f / updateRatePerSecond;

		Vector2D a = new Vector2D(5, 10);


		while (run) {
			float dt = (float) (System.currentTimeMillis() - lastTime);

			if (dt > updateInterval) {
				squad.update();
				
				//System.out.println(System.currentTimeMillis());
				for (Renderable r : renderables) {
					r.update();
				}
				
				View.getInstance().update();
				
				lastTime = System.currentTimeMillis();
			}
		}
	}
}

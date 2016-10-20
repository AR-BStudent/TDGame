package com.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.utility.Vector2D;
import com.visualisation.Renderable;
import com.visualisation.View;
import java.lang.Object;

public class Model extends JFrame {

	private static ArrayList<Renderable> renderables = new ArrayList<>();
	private Map map;
	private static Model _instance = null;

	public static Model getInstance() {
		if (_instance == null) {
			_instance = new Model();
			_instance.init();
		}
		return _instance;
	}

	private void init() {
		map = new Map();
		View view = new View();
		add(view);
		setSize(662, 696);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public ArrayList<Renderable> getRenderables() {
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

	public static void main(String[] args) {
		ArrayList<Unit> units = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, 100);
			int y = ThreadLocalRandom.current().nextInt(0, 100);
			Unit u = new Unit(new Vector2D(x, y));
			u.setImage("towerDefense_tile248.png");
			units.add(u);
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Model game = Model.getInstance();
				game.setVisible(true);
			}
		});

		long lastTime = System.currentTimeMillis();
		boolean run = true;
		float updateRatePerSecond = 60f;
		float updateInterval = 1000f / updateRatePerSecond;

		Vector2D a = new Vector2D(5, 10);

		Path path = new Path(new Vector2D(0, 0), new Vector2D(0, 400), new Vector2D(100, 400), new Vector2D(100, 0),
				new Vector2D(200, 0), new Vector2D(200, 400), new Vector2D(300, 400), new Vector2D(300, 0),
				new Vector2D(400, 0), new Vector2D(400, 400));

		while (run) {
			float dt = (float) (System.currentTimeMillis() - lastTime);

			if (dt > updateInterval) {
				for (Unit u : units) {
					u.follow(path);
				}

				System.out.println(System.currentTimeMillis());
				for (Renderable r : renderables) {
					r.update();
				}
				lastTime = System.currentTimeMillis();
			}
		}
	}
}

package com.model.scene;

import com.model.Map;
import com.model.Squad;
import com.model.Tile;
import com.model.Unit;
import com.utility.RandomUtil;
import com.utility.Vector2D;

public class GameScene extends Scene {

	Map map;
	private Squad<Unit> squad;

	@Override
	public void onEnter() {
		// map is a collection of tiles, which are renderable, so no need to add
		// to render queue manually
		map = new Map("map_02.txt");

		squad = new Squad<>(Unit.class, 20, new Vector2D(500, 500));
		squad.setPath(map.getPath());
	}

}

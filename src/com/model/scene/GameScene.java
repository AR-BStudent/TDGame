package com.model.scene;

import com.controller.Player;
import com.model.Building;
import com.model.Map;
import com.model.Path;
import com.model.Squad;
import com.model.Unit;
import com.ui.UIButton;
import com.utility.Vector2D;
import com.visualisation.View;

public class GameScene extends Scene {

	Player player = new Player();
	Map map;
	private Squad<Unit> squad;

	@Override
	public void onEnter() {
		// map is a collection of tiles, which are renderable, so no need to add
		// to render queue manually
		map = new Map("map_02.txt");

		squad = new Squad<>(Unit.class, 10, new Vector2D(32,-32));
		squad.setPath(map.getPath());
		View.getInstance().addPathForDebug(map.getPath());
		
//		Path p = new Path();
//		p.addPoints(
//				new Vector2D(0, 0),
//				new Vector2D(100, 0),
//				new Vector2D(200, 0),
//				new Vector2D(300, 400),
//				new Vector2D(0, 400)
//				
//				);
		
//		squad.setPath(p);
		
		//SETUP UI
		FunctionalInterface func;
		UIButton createTurret = new UIButton("button1.png", new Vector2D(640,0), new Vector2D(64,64), 0);
		func = ()->{player.selectedBuilding(1); return null;};
		createTurret.setOnClickMethod(func);
		uiManager.addUIObject(createTurret);
	}
	
	//Adds a building to the map
	public void addBuilding(Building toAdd, Vector2D location)
	{
		if(!map.place(location, toAdd.getID()))
			removeRenderable(toAdd);
	}
}

package com.model.scene;

import com.model.Map;
import com.model.Path;
import com.model.Squad;
import com.model.Unit;
import com.ui.UIButton;
import com.utility.Vector2D;
import com.visualisation.Renderable;

public class GameScene extends Scene {

	Map map;
	private Squad<Unit> squad;

	@Override
	public void onEnter() {
		// map is a collection of tiles, which are renderable, so no need to add
		// to render queue manually
		map = new Map("map_02.txt");

		squad = new Squad<>(Unit.class, 1, new Vector2D(500, 500));
		squad.setPath(map.getPath());		
		
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
		func = ()->{System.out.println("The button has been clicked."); return null;};
		createTurret.setOnClickMethod(func);
		uiManager.addUIObject(createTurret);
	}

}

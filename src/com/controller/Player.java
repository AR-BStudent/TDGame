package com.controller;

import java.awt.event.MouseEvent;

import com.model.Building;
import com.model.Model;
import com.model.SingleTurret;
import com.model.scene.GameScene;
import com.model.scene.Scene;
import com.utility.Vector2D;
import com.utility.event.MouseClickedListener;
import com.visualisation.View;

public class Player implements MouseClickedListener {
	
	public Player()
	{
		Controller.getInstance().addMouseClickedListener(this);
	}
	
	protected int buildingID;
	
	public void selectedBuilding(int id)
	{
		buildingID = id;
	}
	
	public void cancelBuilding()
	{
		buildingID = 0;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		Vector2D position = new Vector2D(event.getX(), event.getY());
		position.div(View.SCALE);
		position = new Vector2D((int)position.x, (int)position.y);
		System.out.println("Placing building at " + position.toString());
		
		Scene s = Model.getInstance().getCurrentScene();
		if(s instanceof GameScene)
		{
			GameScene gs = (GameScene)s;
			Building b;
			switch(buildingID)
			{
			case 1:
				b = new SingleTurret("towerDefense_tile249.png", position);
				break;
			default:
				System.out.println("Building with ID " + buildingID + " not yet defined in Player");
				return;
			}
			gs.addBuilding(b, position);
		}
	}
}

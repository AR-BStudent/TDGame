package com.model;

import com.visualisation.Renderable;

public abstract class Building extends Renderable {

	//TODO: Building should supply imagePath to contrcutor
	public Building() {
		super(null, Layer.UNIT);
	}
	
	
}

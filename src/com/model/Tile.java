package com.model;

import com.utility.Vector2D;
import com.visualisation.Renderable;

public class Tile extends Renderable {

	public Tile(String _imagePath, Layer z, Vector2D _location) {
		super(_imagePath, z);
		location = _location;
	}

}
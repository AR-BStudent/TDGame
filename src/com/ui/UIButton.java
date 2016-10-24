package com.ui;

import java.awt.event.MouseEvent;

import com.utility.Vector2D;

public class UIButton extends UIObject {

	public UIButton(String _imagePath, Vector2D location, Vector2D size, int UILayer) {
		super(_imagePath, location, size, UILayer);
	}
	
	@Override 
	public boolean mouseClicked(MouseEvent event)
	{
		action();
		return true;
	}

	FunctionalInterface onClick;
	
	public void setOnClickMethod(FunctionalInterface newInterface)
	{
		onClick = newInterface;
	}
	
	@Override
	public void action() {
		onClick.annotationType();
	}
}

package com.visualisation;

import java.awt.Graphics;
import javax.swing.JPanel;

public class View extends JPanel {
	private void doDrawing(Graphics g)
	{
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}

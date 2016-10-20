package com.visualisation;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JPanel {
	private void doDrawing(Graphics g)
	{
		Image image = null;
		try
		{
			image = ImageIO.read(new File("images/towerDefense_tile001.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(image));
		add(picLabel);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}

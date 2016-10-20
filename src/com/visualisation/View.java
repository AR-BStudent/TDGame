package com.visualisation;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Model;
import com.model.Tile;

public class View extends JPanel {
	
	private void doDrawing(Graphics g)
	{
		removeAll();
		repaint();
		Tile[][] tiles = Model.getInstance().getMap().getTileMap();
		for(Tile[] row : tiles)
		{
			for(Tile t : row)
			{
				Image image = t.getImage();
				JLabel imageLabel = new JLabel();
				imageLabel.setIcon(new ImageIcon(image));
				imageLabel.setBounds((int)t.position.x * 64, (int)t.position.y * 64, 64, 64);
				add(imageLabel);
			}
		}
		/*
		ArrayList<GameObject> gameObjects = Model.getInstance().getGameObjects();
		for(GameObject go : gameObjects)
		{
			JLabel image = go.getImage();
			add(image);
		}
		*/
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		doDrawing(g);
	}
}

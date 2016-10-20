package com.visualisation;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Model;
import com.model.Tile;

public class View extends JPanel {
	
	public static final int SCALE = 64;
	
	private void doDrawing(Graphics g)
	{
		removeAll();
		repaint();
		
		ArrayList<Renderable> renderables = Model.getInstance().getRenderables();
		for(Renderable r : renderables)
		{
			if(r.toString().contains("Tile"))
			{
				continue;
			}
			Image image = r.getImage();
			JLabel imageLabel = new JLabel();
			imageLabel.setIcon(new ImageIcon(image));
			imageLabel.setBounds((int)r.location.x, (int)r.location.y, 64, 64);
			add(imageLabel);
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		doDrawing(g);
	}
}

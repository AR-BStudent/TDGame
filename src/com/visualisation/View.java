package com.visualisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.model.Model;


public class View extends JFrame {
	
	public static final int SCALE = 64;
	private static View _instance;
	public static View getInstance() {
		if (_instance == null) {
			_instance = new View();
		}
		return _instance;
	}
	
	public static void main(String[] args)
	{
		Model.getInstance().start();
	}
	
	public View()
	{
		super("Tower Defense Game");
		
		setSize(662, 696);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new ViewPanel());
		setVisible(true);
	}
	
	public void update()
	{
		repaint();
	}
}

class ViewPanel extends JPanel {
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D spriteBatch = (Graphics2D)g;
		
		//Reset background
		spriteBatch.setColor(Color.BLACK);
		spriteBatch.fillRect(0, 0, getSize().width, getSize().height);
		
		PriorityQueue<Renderable> renderables = Model.getInstance().getRenderables();
		for(Renderable r : renderables)
		{
			AffineTransform transform = new AffineTransform();
			transform.rotate(r.getRotation(), r.location.x + (View.SCALE / 2), r.location.y + (View.SCALE / 2));;
			transform.translate(r.location.x, r.location.y);
			spriteBatch.drawImage(r.getImage(), transform, this);
		}
	}
}

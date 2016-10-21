package com.visualisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
		//addComponentListener(new WindowListener());
		setResizable(false);
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

class WindowListener implements ComponentListener
{

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		//TODO: Implement scaling based on window size.
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

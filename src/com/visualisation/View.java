package com.visualisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.model.Model;
import com.model.Path;
import com.utility.BezierPath;
import com.utility.Vector2D;

public class View extends JFrame {

	public static final int SCALE = 64;
	private static View _instance;

	public static View getInstance() {
		if (_instance == null) {
			_instance = new View();
		}
		return _instance;
	}

	ViewPanel vp;

	public ViewPanel getViewPanel() {
		return vp;
	}

	public static void main(String[] args) {
		Model.getInstance().start();
	}

	public View() {
		super("Tower Defense Game");

		setSize(710, 680);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		vp = new ViewPanel();
		add(vp);
		// addComponentListener(new WindowListener());
		setResizable(false);
		setVisible(true);
	}

	public void update() {
		repaint();
	}

	public void addMouseListenerToPanel(MouseListener ml) {
		vp.addMouseListener(ml);
	}

	public void addKeyListenerToPanel(KeyListener kl) {
		vp.addKeyListener(kl);
	}

	public void addPathForDebug(Path p) {
		vp.debugPath = p;
	}
}

class ViewPanel extends JPanel {

	Path debugPath;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D spriteBatch = (Graphics2D) g;

		// Reset background
		spriteBatch.setColor(Color.BLACK);
		spriteBatch.fillRect(0, 0, getSize().width, getSize().height);
		Model.getInstance().getCurrentScene().draw(spriteBatch);
		if (debugPath != null) {
			debugPath.debugDraw(spriteBatch);
		}
		// Bezier test
		ArrayList<Vector2D> cp = new ArrayList<>();
		cp.add(new Vector2D(1, 1));
		cp.add(new Vector2D(101, 1));
		cp.add(new Vector2D(101, 401));
		cp.add(new Vector2D(501, 501));
		cp.add(new Vector2D(1, 1));
		cp.add(new Vector2D(101, 1));
		cp.add(new Vector2D(101, 401));
		cp.add(new Vector2D(501, 501));
		cp.add(new Vector2D(1, 1));
		cp.add(new Vector2D(101, 1));
		cp.add(new Vector2D(101, 401));
		cp.add(new Vector2D(501, 501));
;

		BezierPath bp = new BezierPath(cp, 100);
		ArrayList<Vector2D> curve = bp.getPath();
		bp.drawCurve(spriteBatch, curve);
	}
}

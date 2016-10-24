package com.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.utility.event.KeyPressedListener;
import com.utility.event.KeyReleasedListener;
import com.utility.event.KeyTypedListener;
import com.utility.event.MouseClickedListener;
import com.utility.event.MouseEnteredListener;
import com.utility.event.MouseExitedListener;
import com.utility.event.MousePressedListener;
import com.utility.event.MouseReleasedListener;

public class Controller implements KeyListener, MouseListener {

	private static Controller _instance;
	public static Controller getInstance()
	{
		if(_instance == null)
		{
			_instance = new Controller();
		}
		return _instance;
	}
	
	private ArrayList<MouseClickedListener> mouseClickedListeners = new ArrayList();

	public void addMouseClickedListener(MouseClickedListener newListener) {
		mouseClickedListeners.add(newListener);
	}

	public void removeMouseClickedListener(MouseClickedListener oldListener) {
		mouseClickedListeners.add(oldListener);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		for (MouseClickedListener l : mouseClickedListeners) {
			l.mouseClicked(event);
		}
	}

	private ArrayList<MouseEnteredListener> mouseEnteredListeners = new ArrayList();

	public void addMouseEnteredListener(MouseEnteredListener newListener) {
		mouseEnteredListeners.add(newListener);
	}

	public void removeMouseEnteredListener(MouseEnteredListener oldListener) {
		mouseEnteredListeners.add(oldListener);
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		for (MouseEnteredListener l : mouseEnteredListeners) {
			l.mouseEntered(event);
		}
	}

	private ArrayList<MouseExitedListener> mouseExitedListeners = new ArrayList();

	public void addMouseExitedListener(MouseExitedListener newListener) {
		mouseExitedListeners.add(newListener);
	}

	public void removeMouseExitedListener(MouseExitedListener oldListener) {
		mouseExitedListeners.add(oldListener);
	}

	@Override
	public void mouseExited(MouseEvent event) {
		for (MouseExitedListener l : mouseExitedListeners) {
			l.mouseExited(event);
		}
	}

	private ArrayList<MousePressedListener> mousePressedListeners = new ArrayList();

	public void addMousePressedListener(MousePressedListener newListener) {
		mousePressedListeners.add(newListener);
	}

	public void removeMousePressedListener(MousePressedListener oldListener) {
		mousePressedListeners.add(oldListener);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		for (MousePressedListener l : mousePressedListeners) {
			l.mousePressed(event);
		}
	}

	private ArrayList<MouseReleasedListener> mouseReleasedListeners = new ArrayList();

	public void addMouseReleasedListener(MouseReleasedListener newListener) {
		mouseReleasedListeners.add(newListener);
	}

	public void removeMouseReleasedListener(MouseReleasedListener oldListener) {
		mouseReleasedListeners.add(oldListener);
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		for (MouseReleasedListener l : mouseReleasedListeners) {
			l.mouseReleased(event);
		}
	}

	private ArrayList<KeyPressedListener> keyPressedListeners = new ArrayList();

	public void addMouseReleasedListener(KeyPressedListener newListener) {
		keyPressedListeners.add(newListener);
	}

	public void removeMouseReleasedListener(KeyPressedListener oldListener) {
		keyPressedListeners.add(oldListener);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		for (KeyPressedListener l : keyPressedListeners) {
			l.keyPressed(event);
		}
	}

	private ArrayList<KeyReleasedListener> keyReleasedListeners = new ArrayList();

	public void addMouseReleasedListener(KeyReleasedListener newListener) {
		keyReleasedListeners.add(newListener);
	}

	public void removeMouseReleasedListener(KeyReleasedListener oldListener) {
		keyReleasedListeners.add(oldListener);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		for (KeyReleasedListener l : keyReleasedListeners) {
			l.keyReleased(event);
		}
	}

	private ArrayList<KeyTypedListener> keyTypedListeners = new ArrayList();

	public void addMouseReleasedListener(KeyTypedListener newListener) {
		keyTypedListeners.add(newListener);
	}

	public void removeMouseReleasedListener(KeyTypedListener oldListener) {
		keyTypedListeners.add(oldListener);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		for (KeyTypedListener l : keyTypedListeners) {
			l.keyTyped(event);
		}
	}

}

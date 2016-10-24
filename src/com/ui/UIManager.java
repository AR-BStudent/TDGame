package com.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.PriorityQueue;

import com.utility.Vector2D;
import com.visualisation.View;

public class UIManager implements MouseListener {
	PriorityQueue<UIObject> uiObjects;

	public UIManager() {
		uiObjects = new PriorityQueue<UIObject>();
		View.getInstance().addMouseListenerToPanel(this);
	}

	public void addUIObject(UIObject obj) {
		uiObjects.add(obj);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		System.out.println("Mouse clicked at [" + event.getX() + ", " + event.getY() + "]");
		for (UIObject uiObject : uiObjects) {
			if (uiObject.inBounds(new Vector2D(event.getX(), event.getY()))) {
				if (uiObject.mouseClicked(event)) {
					return;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		for (UIObject uiObject : uiObjects) {
			if (uiObject.inBounds(new Vector2D(event.getX(), event.getY()))) {
				if (uiObject.mouseEntered(event)) {
					return;
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent event) {
		for (UIObject uiObject : uiObjects) {
			if (uiObject.inBounds(new Vector2D(event.getX(), event.getY()))) {
				if (uiObject.mouseExited(event)) {
					return;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent event) {
		for (UIObject uiObject : uiObjects) {
			if (uiObject.inBounds(new Vector2D(event.getX(), event.getY()))) {
				if (uiObject.mousePressed(event)) {
					return;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		for (UIObject uiObject : uiObjects) {
			if (uiObject.inBounds(new Vector2D(event.getX(), event.getY()))) {
				if (uiObject.mouseReleased(event)) {
					return;
				}
			}
		}
	}
}
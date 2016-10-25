package com.ui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import com.utility.Vector2D;
import com.visualisation.Renderable;
import com.visualisation.View;

public abstract class UIObject extends Renderable {

	public UIObject(String _imagePath, Vector2D location, Vector2D size, int UILayer) {
		super(_imagePath, Layer.UI);
		this.location = location;
		this.size = size;
		this.UILayer = UILayer;
	}

	protected Vector2D size;
	// Higher number, lower priority
	protected int UILayer;

	public abstract void action();

	public final boolean inBounds(Vector2D position) {
		Vector2D relativepos = position.sub(location);
		if (relativepos.y < 0 || relativepos.y > size.y || relativepos.x < 0 || relativepos.x > size.x) {
			return false;
		}

		return true;
	}

	@Override
	public int compareTo(Renderable other) {
		if (other instanceof UIObject) {
			UIObject otherUI = (UIObject) other;
			if (UILayer < otherUI.UILayer) {
				return 1;
			} else if (UILayer == otherUI.UILayer) {
				return 0;
			}
			return -1;
		} else {
			return super.compareTo(other);
		}
	}

	public boolean mouseClicked(MouseEvent event) {
		return false;
	}

	public boolean mouseEntered(MouseEvent event) {
		return false;
	}

	public boolean mouseExited(MouseEvent event) {
		return false;
	}

	public boolean mousePressed(MouseEvent event) {
		return false;
	}

	public boolean mouseReleased(MouseEvent event) {
		return false;
	}
	
	@Override
	public void draw(Graphics2D canvas) 
	{
		AffineTransform transform = new AffineTransform();
		transform.rotate(getRotation(), location.x, location.y);
		transform.translate(location.x, location.y);
		canvas.drawImage(getImage(), transform, View.getInstance().getViewPanel());
	}
}

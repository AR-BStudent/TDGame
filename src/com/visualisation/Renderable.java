package com.visualisation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.model.Model;
import com.utility.Vector2D;

public abstract class Renderable implements Comparable<Renderable> {

	public Vector2D location = new Vector2D();

	protected Image image = null;
	private float rotation = 0;
	protected Layer zBuffer = Layer.GROUND;
	private String imagePath;

	public Renderable(String _imagePath, Layer z) {
		zBuffer = z;
		imagePath = _imagePath;
		setImage(imagePath);
		Model.getInstance().getCurrentScene().addRenderable(this);
	}

	public void setImagePath(String _imagePath) {
		imagePath = _imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public int compareTo(Renderable other) {
		return zBuffer.compareTo(other.getZBuffer());
	}

	public Layer getZBuffer() {
		return zBuffer;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float radians) {
		rotation = radians;
	}

	public void setImage(String imageName) {
		Image image = null;
		try {
			image = ImageIO.read(new File("images/" + imageName));
		} catch (IOException e) {
			System.out.println("Could not find file: images/" + imageName);
			e.printStackTrace();
		}

		this.image = image.getScaledInstance(View.SCALE, View.SCALE, Image.SCALE_SMOOTH);
	}

	public Image getImage() {
		if (image == null) {
			System.out.println("No picture assigned to renderable");
		}
		return image;
	}

	public void draw(Graphics2D canvas) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(getRotation(), location.x + (View.SCALE / 2), location.y + (View.SCALE / 2));
		transform.translate(location.x * View.SCALE, location.y * View.SCALE);
		canvas.drawImage(getImage(), transform, View.getInstance().vp);
	}

	public enum Layer {
		GROUND, UNIT, FX, UI
	}
}
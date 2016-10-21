package com.visualisation;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.model.Model;
import com.utility.Vector2D;

public abstract class Renderable implements Comparable<Renderable> {

	@Override
	public int compareTo(Renderable other) {
		int otherZ = other.getZBuffer();
		if (zBuffer == otherZ) {
			return 0;
		} else if (zBuffer < otherZ) {
			return -1;
		}
		return 1;
	}

	public Vector2D location = new Vector2D();

	protected Image image = null;
	protected int zBuffer = 0;
	private float rotation = 0;

	public Renderable(int z) {
		zBuffer = z;
		Model.getInstance().addRenderable(this);
	}

	public int getZBuffer() {
		return zBuffer;
	}
	
	//TODO: Store a transform, don't create one every time you need to render.
	public AffineTransform getTransform()
	{
		AffineTransform t = new AffineTransform();
		t.rotate(rotation);
		return t;
	}
	
	public float getRotation()
	{
		return rotation;
	}
	
	public void setRotation(float radians)
	{
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

	public abstract void update();

}

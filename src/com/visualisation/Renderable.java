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

public abstract class Renderable {

	public Vector2D location = new Vector2D();

	protected Image image = null;

	public Renderable() {
		Model.getInstance().addRenderable(this);
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

	public void rotate(float theta) {
		/*
		 * Graphics2D and BufferedImage allow transformations
		 * so I think we should change the rendering to use these rather than the base Image class
		 * Will need major rewrite so haven't changed anything yet
		 * 
		 * N.B. below is a sub method which shows how image would be rotated (if it was the correct type)
		 */
		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) bimage.getGraphics();
		AffineTransform old = g2d.getTransform();
		g2d.rotate(theta);
		g2d.setTransform(old);
	}

}

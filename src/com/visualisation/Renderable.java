package com.visualisation;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.model.Model;
import com.utility.Vector2D;

public abstract class Renderable {

	public Vector2D location = new Vector2D();
	
	protected Image image = null;
	
	public Renderable(){
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
	
}

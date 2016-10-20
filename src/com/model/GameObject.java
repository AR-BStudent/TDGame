package com.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.utility.Vector2D;
import com.visualisation.IRenderable;

public abstract class GameObject implements IRenderable{
	
	public Vector2D position = new Vector2D();
	
	//IRenderable implementation
	private Image image = null;
	
	public GameObject()
	{
		Model.getInstance().addObject(this);
	}
	
	public abstract void update(float deltaT);
	
	public void setImage(String imageName)
	{
		Image image = null;
		try
		{
			image = ImageIO.read(new File("images/" + imageName));
		}
		catch(IOException e)
		{
			System.out.println("Could not find file: images/" + imageName);
			e.printStackTrace();
		}
		
		this.image = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
	}
	
	public Image getImage()
	{
		if(image == null)
		{
			System.out.println("No picture assigned to renderable");
		}
		return image;
	}
}

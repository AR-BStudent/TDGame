package com.model.scene;

import java.awt.Graphics2D;
import java.util.PriorityQueue;

import com.visualisation.Renderable;

public abstract class Scene {
	protected PriorityQueue<Renderable> renderables = new PriorityQueue<>();
	
	public PriorityQueue<Renderable> getRenderables()
	{
		return renderables;
	}
	
	public abstract void update();
	public void draw(Graphics2D g)
	{
		for(Renderable r : renderables)
		{
			r.draw(g);
		}
	}
	public abstract void onEnter();
	public abstract void onExit();
	
	public void removeRenderable(Renderable r){
		renderables.remove(r);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((renderables == null) ? 0 : renderables.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (renderables == null) {
			if (other.renderables != null)
				return false;
		} else if (!renderables.equals(other.renderables))
			return false;
		return true;
	}
}

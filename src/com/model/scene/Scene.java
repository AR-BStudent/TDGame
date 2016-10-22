package com.model.scene;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.PriorityQueue;

import com.model.Updateable;
import com.visualisation.Renderable;

public abstract class Scene {

	protected PriorityQueue<Renderable> renderables = new PriorityQueue<>();
	protected ArrayList<Updateable> updatables = new ArrayList<>();

	public PriorityQueue<Renderable> getRenderables() {
		return renderables;
	}

	public void onEnter() {

	}

	public void update() {
		for (Updateable u : updatables) {
			u.update();
		}
	}

	public void draw(Graphics2D g) {
		for (Renderable r : renderables) {
			r.draw(g);
		}
	}

	public void onExit() {

	}

	public void addRenderable(Renderable r) {
		if (!renderables.contains(r)) {
			renderables.add(r);
		}
	}

	public void removeRenderable(Renderable r) {
		if (renderables.contains(r)) {
			renderables.remove(r);
		}
	}

	public void addUpdatable(Updateable u) {
		if (!updatables.contains(u)) {
			updatables.add(u);
		}
	}

	public void removedUpdatable(Updateable u) {
		if (updatables.contains(u)) {
			updatables.remove(u);
		}
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

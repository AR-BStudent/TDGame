package com.model;

public interface Updateable {

	public void update();

	public default void registerUpdates() {
		Model.getInstance().getCurrentScene().addUpdatable(this);
	}

	public default void stopUpdates() {
		Model.getInstance().getCurrentScene().removedUpdatable(this);
	}

}

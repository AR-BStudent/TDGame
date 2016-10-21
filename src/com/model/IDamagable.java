package com.model;

public interface IDamagable {

	float curHealth = 100;
	float maxHealth = 100;

	default float getHealthPercent() {
		return curHealth / maxHealth * 100;
	}

	public void takeDamage(float dmg);

	void onDie();

}

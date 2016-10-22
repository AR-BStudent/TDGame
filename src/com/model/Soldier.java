package com.model;

public class Soldier extends Unit implements IDamagable {

	float curHealth;
	float maxHealth = 100;

	public Soldier() {
		curHealth = maxHealth;
	}

	@Override
	public void takeDamage(float dmg) {
		curHealth -= dmg;
		if (dmg <= 0) {
			onDie();
		}
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		//todo remove unit from squad on death
	}
	
	

}

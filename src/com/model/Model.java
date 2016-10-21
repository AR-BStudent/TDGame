package com.model;

import java.util.HashMap;

import com.model.scene.GameScene;
import com.model.scene.Scene;
import com.visualisation.View;

public class Model extends Thread{

	private static Model _instance = null;

	public static Model getInstance() {
		if (_instance == null) {
			_instance = new Model();
		}
		return _instance;
	}

	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();
	private Scene currentScene = null;

	public Scene getCurrentScene()
	{
		return currentScene;
	}
	
	public boolean changeScene(String sceneName)
	{
		Scene newScene = scenes.get(sceneName);
		if(newScene == null)
		{
			System.out.println("Could not switch to new scene : scene " + sceneName + " does not exists");
			return false;
		}
		if(newScene == currentScene)
		{
			System.out.println("Reseting scene...Are you sure you should do this via changeScene()?");
		}
		currentScene.onExit();
		currentScene = newScene;
		newScene.onEnter();
		return true;
	}

	public void run() {
		//Add Scenes
		GameScene gs = new GameScene();
		currentScene = gs;
		gs.onEnter();
		scenes.put("GameScene", gs);
		
		//Run Game
		long lastTime = System.currentTimeMillis();
		boolean run = true;
		float updateRatePerSecond = 60f;
		float updateInterval = 1000f / updateRatePerSecond;

		while (run) {
			float dt = (float) (System.currentTimeMillis() - lastTime);

			if (dt > updateInterval) {	
				currentScene.update();
				View.getInstance().update();
				
				lastTime = System.currentTimeMillis();
			}
		}
	}
}

package com.model;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.utility.Vector2D;
import com.visualisation.View;

public class Model extends JFrame{
	
	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Map map;
	
	private static Model _instance = null;
	private static boolean creatingInstance = false;
	public static Model getInstance()
	{
		if(_instance == null)
		{
			_instance = new Model();
			_instance.init();
		}
		return _instance;
	}
	
	private void init()
	{	
		map = new Map();
		View view = new View();
		add(view);
		setSize(662,696);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public ArrayList<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public void addObject(GameObject go)
	{
		if(go == null)
		{
			System.out.println("Cannot add null object");
			return;
		}
		gameObjects.add(go);
	}
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                Model game = Model.getInstance();
                game.setVisible(true);
            }
        });
          
        long lastTime = System.currentTimeMillis();
        boolean run = true;
    	float updateRatePerSecond = 2f;
    	float updateInterval = 1000f/updateRatePerSecond;
    	
    	Unit unit = new Unit(new Vector2D(0,0));
    	unit.setImage("towerDefense_tile248.png");
    	unit.seek(new Vector2D(640, 640));
        while(run)
        {
        	float dt = (float)(System.currentTimeMillis() - lastTime);

        	if(dt > updateInterval)
        	{
        		System.out.println(System.currentTimeMillis());
        		for(GameObject go : gameObjects)
        		{
        			go.update((float)dt / 1000f);
        		}
        		lastTime = System.currentTimeMillis();
        	}
        }
    } 
}

package com.model;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.visualisation.View;

public class Model extends JFrame{
	
	private ArrayList<GameObject> gameObjects;
	private Map map;
	
	private static Model _instance;
	public static Model getInstance()
	{
		if(_instance == null)
		{
			_instance = new Model();
		}
		return _instance;
	}
	
	private Model()
	{
		initUI();
		map = new Map();
	}
	
	private void initUI()
	{
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
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                Model game = Model.getInstance();
                game.setVisible(true);
            }
        });                
    } 
}

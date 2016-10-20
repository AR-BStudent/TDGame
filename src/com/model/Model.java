package com.model;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.visualisation.View;

public class Model extends JFrame{
	
	private ArrayList<GameObject> gameObjects;
	private Map map;
	
	public Model()
	{
		initUI();
		map = new Map();
	}
	
	private void initUI()
	{
		View view = new View();
		add(view);
		setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                Model game = new Model();
                game.setVisible(true);
            }
        });                
    } 
}

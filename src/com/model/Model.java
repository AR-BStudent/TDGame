package com.model;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.visualisation.View;

public class Model extends JFrame{
	
	public Model()
	{
		initUI();
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

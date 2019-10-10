package nz.ac.vuw.ecs.swen225.a3.render;

import java.awt.Canvas;
import java.awt.Color;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;

public class LabelCanvas extends Canvas{

	Maze maze;
	
    public LabelCanvas(Maze maze) {
    	this.maze = maze;
    	this.setSize(50, 50);
    	
    	super.setBackground(new Color(237, 201, 175));
    }
}

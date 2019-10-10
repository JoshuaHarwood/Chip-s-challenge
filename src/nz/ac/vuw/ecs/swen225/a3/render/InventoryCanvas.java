package nz.ac.vuw.ecs.swen225.a3.render;

import java.awt.Canvas;
import java.util.List;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

/**
 * 
 * @author Joshua Harwood---300439084
 *
 */
public class InventoryCanvas extends Canvas {
	
	Maze maze;
	
    /**
     * constructor
     * @param maze - the maze containing the tiles array
     */
    public InventoryCanvas(Maze maze) {
    	this.maze = maze;
    }
    
    /**
     * draw the inventory on the canvas
     * @param w
     * @param h
     */
	public void draw(int w, int h) {
		List<TileType> keys = maze.getChap().getAllKeys();
	}

}

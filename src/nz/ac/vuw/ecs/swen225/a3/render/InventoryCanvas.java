package nz.ac.vuw.ecs.swen225.a3.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

/**
 * 
 * @author Joshua Harwood---300439084
 *
 */
public class InventoryCanvas extends Canvas {
	
	Maze maze;
	
	int tileSize = 64;
	int rows = 4;
	int cols = 3;
	
    /**
     * constructor
     * @param maze - the maze containing the tiles array
     */
    public InventoryCanvas(Maze maze) {
    	this.maze = maze;
    	this.setSize(tileSize*(cols), tileSize*(rows));
    	
    	super.setBackground(new Color(237, 201, 175));
    }
    
    /**
     * draw the inventory on the canvas
     */
	public void draw() {
		List<TileType> keys = maze.getChap().getAllKeys();
		
		
        //create a buffered image to reduce the flickering when drawing
        BufferedImage image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = image.createGraphics();
        imgG.setColor(this.getBackground());
        		
		int x = 0, y = 0;
			
		//draw the inventory
		for(TileType type : keys) {			
			imgG.drawImage(getTileImage(type), x*tileSize, y*tileSize, tileSize, tileSize, this); //draw the image			
			if(++x >= cols) { x = 0; y++; }			
		}
		
		//fill the rest of the inventory with sand
		while(y <= rows) {
			imgG.drawImage(getTileImage(TileType.Empty), x*tileSize, y*tileSize, tileSize, tileSize, this); //draw the image			
			if(++x >= cols) { x = 0; y++; }
		}
		
        this.getGraphics().drawImage(image, 0, 0, this);
        imgG.dispose();
		
	}
	
	/**
	 * Gets the image associated with this tile.
	 * @return the image
	 */
	public Image getTileImage(TileType type) {
		try {
			return ImageIO.read(getClass().getResource("../maze/icons/" + type.name() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}

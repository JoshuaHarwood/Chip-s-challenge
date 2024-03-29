package nz.ac.vuw.ecs.swen225.a3.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

/**
 * This is the stats container that will display the game stats such as: Level, Time Left and Coconuts left.
 * @author Joshua Harwood - 300439084
 *
 */
public class LabelCanvas extends Canvas{

	private static final long serialVersionUID = 1L;
	Maze maze;
	int size = 50;
	
    /**
     * Creates a new LabelCanvas.
     * @param maze The maze object of the current game
     */
    public LabelCanvas(Maze maze) { 
    	this.maze = maze;   	  
    	this.setSize(size*4, size*2); 
    	
    	super.setBackground(new Color(237, 201, 175));
    }
    
    /**
     * Draws this LabelCanvas.
     */
    public void draw() {
    	
        //create a buffered image to reduce the flickering when drawing
        BufferedImage image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = image.createGraphics();
        
        int x = 0, y = 0;
		//fill the rest of the inventory with sand
		while(y <= (this.getHeight()/size)) {
			imgG.drawImage(getTileImage(TileType.Empty), x*size, y*size, size, size, this); //draw the image			
			if(++x >= (this.getWidth()/size)) { x = 0; y++; }
		}
        
		imgG.setColor(Color.black);
		imgG.setFont(new Font(imgG.getFont().getFamily(), Font.BOLD, 16));
        imgG.drawString("Current Level: " + maze.level, 0, size/2);
        imgG.drawString("Time Left: " + maze.getTimeLeft(), 0, (size/2)*2);
        imgG.drawString("Coconuts left: " + maze.getTreasureLeft(), 0, (size/2)*3);
        
        this.getGraphics().drawImage(image, 0, 0, this);
        imgG.dispose();
    }
    
     
	/** 
	 * Gets the image associated with this tile.
	 * @param type The type of the image to get (e.g. TileType.Empty, TileType.Wall)
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

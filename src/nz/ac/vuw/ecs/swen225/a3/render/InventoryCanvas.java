package nz.ac.vuw.ecs.swen225.a3.render;

import java.awt.Canvas;
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
	
	int tileSize;
	
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
		this.setSize(w, h);
		
        //create a buffered image to reduce the flickering when drawing
        BufferedImage image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = image.createGraphics();
        imgG.setColor(this.getBackground());
        
		tileSize = 36;
		fixTitleSize(w, h);
		
		int x = 0, y = 0;
		
		
		for(TileType type : keys) {
			
		}
		
	}
	
	private void fixTitleSize(int w, int h) {
		if(w > 0 && h > 0 && tileSize > 0) {
			int tW = (w/tileSize);
			int tH = (h/tileSize);
			
			if((tW > 0) && (tH > 0) && (tW+tH <= maze.getChap().getAllKeys().size())) {
				return;
			} else {
				tileSize = tileSize/2;
				fixTitleSize(w, h);
			}
		}
	}
	
	/**
	 * Gets the image associated with this tile.
	 * @return the image
	 */
	public Image getTileImage(TileType tile) {
		try {
			return ImageIO.read(getClass().getResource("icons/" + tile.name() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}

package nz.ac.vuw.ecs.swen225.a3.render;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;

import java.awt.*;

/**
 * this class is a custom canvas, this is where the tiles will be displayed and the game will take place
 * @author Joshua Harwood
 */
public class BoardCanvas extends Canvas {

    private int tileSize = 32;
    private Tile[][] tiles;
    private int cols, rows;
    
    private Maze maze;

    /**
     * constructor
     * @param maze - the maze containing the tiles array
     */
    public BoardCanvas(Maze maze) {
    	this.maze = maze;
//    	this.tiles = maze.getBoard();
//        this.tilesW = this.tiles[0].length;
//        this.tilesH = this.tiles.length;

        this.cols = maze.getBoard()[0].length;
        this.rows = maze.getBoard().length;

        this.setSize(cols*tileSize, rows*tileSize);
    }

    /**
     * when called this will go through the board and draw each tile. and scale them to fit
     * @param g - the graphics object to be drawn on
     * @param w - the width of the plane behind (so that we can scale to fit that)
     * @param h - the height of the plane behind (so that we can scale to fit that)
     */
    public void draw(Graphics g, int w, int h) {

        tiles = maze.getBoard();
        cols = tiles[0].length;
        rows = tiles.length;
    	
        System.out.println("DRAWING     WIDTH: " + cols + "  HEIGHT: " + rows);

        int scaledSizeW = w / cols; //finding the scaled width
        int scaledSizeH = h / rows; //finding the scaled height

        tileSize = Math.min(scaledSizeH, scaledSizeW); //get the smallest of the 2 (so we dont draw off the edge)

        this.setSize(scaledSizeW * cols, scaledSizeH * rows); //set the size

        //go through each of the tiles and draw them
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
//
                Image tileImg = tiles[j][i].getImage(); // get the image
                int x = i * tileSize; //work the X and Y
                int y = j * tileSize;

                g.drawImage(tileImg, x, y, tileSize, tileSize, null); //draw the image
            }
        }
    }
}

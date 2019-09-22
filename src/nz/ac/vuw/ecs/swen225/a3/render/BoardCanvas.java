package nz.ac.vuw.ecs.swen225.a3.render;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class is a custom canvas, this is where the tiles will be displayed and the game will take place
 * @author Joshua Harwood
 */
public class BoardCanvas extends Canvas {

    private int tileSize = 32;
    private int minTileSize = 32;
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
     * @param w - the width of the plane behind (so that we can scale to fit that)
     * @param h - the height of the plane behind (so that we can scale to fit that)
     */
    public void draw(int w, int h) {

        tiles = maze.getBoard();
        cols = tiles[0].length;
        rows = tiles.length;
        Tile view[][];

        System.out.println("DRAWING     WIDTH: " + cols + "  HEIGHT: " + rows);

        int scaledSizeW = w / cols; //finding the scaled width
        int scaledSizeH = h / rows; //finding the scaled height

        tileSize = Math.min(scaledSizeH, scaledSizeW); //get the smallest of the 2 (so we dont draw off the edge)

        if(tileSize < minTileSize){
            tileSize = minTileSize;

            int viewW = this.getWidth() / minTileSize;
            int viewH = this.getHeight() / minTileSize;
            view = new Tile[viewH][viewW]; //the new view of the player, this will leave out some

            int chapX = maze.getChap().getX();
            int chapY = maze.getChap().getY();

            


        } else {
            view = tiles;
        }



        this.setSize(tileSize * cols, tileSize * rows); //set the size


        //create a buffered image to reduce the flickering when drawing
        BufferedImage image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D imgG = image.createGraphics();
        imgG.setColor(this.getBackground());

        //go through each of the tiles and draw them
        for (int i = 0; i < view[0].length; i++) {
            for (int j = 0; j < view.length; j++) {
//
                Image tileImg = view[j][i].getImage(); // get the image
                int x = i * tileSize; //work the X and Y
                int y = j * tileSize;

                imgG.drawImage(tileImg, x, y, tileSize, tileSize, this); //draw the image
            }
        }

        this.getGraphics().drawImage(image, 0, 0, this);
        imgG.dispose();
    }
}

package nz.ac.vuw.ecs.swen225.a3.render;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * this class is a custom canvas, this is where the tiles will be displayed and the game will take place
 * @author Joshua Harwood
 */
public class BoardCanvas extends Canvas {

    private int tileSize = 32;
    private int minTileSize = 64;
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

        int scaledSizeW = w / cols; //finding the scaled width
        int scaledSizeH = h / rows; //finding the scaled height

        tileSize = Math.min(scaledSizeH, scaledSizeW); //get the smallest of the 2 (so we dont draw off the edge)

        if(tileSize < minTileSize){
            tileSize = minTileSize;

            int viewCol = w / minTileSize;
            int viewRow = h / minTileSize;

            view = adjustView(viewCol, viewRow);

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

                if((view[j][i] != null)) {
                    Image tileImg = view[j][i].getImage(); // get the image
                    int x = i * tileSize; //work the X and Y
                    int y = j * tileSize;

                    imgG.drawImage(tileImg, x, y, tileSize, tileSize, this); //draw the image
                }
            }
        }

        this.getGraphics().drawImage(image, 0, 0, this);
        imgG.dispose();
    }

    /**
     * generate a view given the parameters
     * @param viewH - the height of the view (tiles high)
     * @param viewW - the width of the view (tiles wide)
     * @return - returns a view within toes parameters
     */
    private Tile[][] adjustView(int viewCol, int viewRow) {

        Tile[][] view = new Tile[viewRow][viewCol]; //the new view of the player, this will leave out some

            //int windowSize = Math.min(viewH, viewW);
            //System.out.println("    WINDOW SIZE" + windowSize);
            //getting the tiles above and on the chap
        int vX = 0, vY = 0; // position in the view

        int y = maze.getChap().getY() - viewRow/2; //we want to start getting
        int x = maze.getChap().getX() - viewCol/2;
        int extraY = 0;
        int extraX = 0;



        while(true){
            if(y >= 0 && y < tiles.length){ // if we have not gone out of the array
                for (; x <  tiles[0].length; x++){ //maze.getChap().getX() + viewW/2; x++) { //same for X
                    if(x >= 0 && vX < view[0].length){ //we haven't gone out of the array
                        System.out.println("vY: " + vY + " vX: " + vX + "     X: " + x + " Y: " + y);
                        view[vY][vX++] = tiles[y][x]; //update the view
                    }
                }
                x = 0;
                vY++;
                vX = 0;
            }
            if(++y >= tiles.length || vY >= view.length){ //if we have reached chap
                break; //break out
            }
        }
      return view;
    }
}

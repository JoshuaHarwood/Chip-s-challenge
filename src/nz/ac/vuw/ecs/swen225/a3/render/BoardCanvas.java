package nz.ac.vuw.ecs.swen225.a3.render;

import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * this class is a custom canvas, this is where the tiles will be displayed and the game will take place
 * @author Joshua Harwood----300439084
 */
public class BoardCanvas extends Canvas {

    private int viewWindow = 9; //must be an odd number as chap will be in the middle e.g. as 7 we go 3 out on each side totaling to 6, then the x and y that chap is on increases that to 7
    private Tile[][] tiles; //the tile array to be drawn
    private int cols, rows;
    int redrawCount = 0;
    
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

        this.setSize(cols*viewWindow, rows*viewWindow);
    }

    /**
     * when called this will go through the board and draw each tile. and scale them to fit
     * @param w - the width of the plane behind (so that we can scale to fit that)
     * @param h - the height of the plane behind (so that we can scale to fit that)
     * @throws IOException 
     */
    public void draw(int w, int h) {

        int tileSize;
        tiles = maze.getBoard();
        cols = tiles[0].length;
        rows = tiles.length;
        Tile view[][];

        int scaledSizeW = w / (viewWindow+1); //finding the scaled width
        int scaledSizeH = h / (viewWindow+1); //finding the scaled height

        tileSize = Math.min(scaledSizeH, scaledSizeW); //get the smallest of the 2 (so we dont draw off the edge)

        view = adjustView(viewWindow);

        this.setSize(tileSize * viewWindow, tileSize * viewWindow); //set the size

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
     * @param viewWindow - this is the size of the view window that the player will see
     * @return - returns a view within toes parameters
     */
    private Tile[][] adjustView(int viewWindow) {

        Tile[][] view = new Tile[viewWindow][viewWindow]; //the new view of the player, this will leave out some


        if(tiles.length > viewWindow && tiles[0].length > viewWindow) {
            int fromChap = (int)((viewWindow/2));
            int chapX = maze.getChap().getX(), chapY = maze.getChap().getY();

            int viewX = 0, viewY = 0;
            int tilesY = tiles.length;
            int tilesX = tiles[0].length;

            //** find the window dimensions
            int minX = 0, maxX = 0, minY = 0, maxY = 0;

            if((chapX - fromChap) < 0){ //if it over the left of the array
                minX = 0;
                maxX += fromChap - chapX;
            } else {
                minX += (chapX - fromChap);
            }

            if((chapX + fromChap) >= tilesX){ //if it is over the right of the array
                maxX = tilesX-1;
                minX += (tilesX - (chapX + fromChap) -1);
            } else {
                maxX += chapX + fromChap;
            }

            if((chapY - fromChap) < 0){ //if it over the top of the array
                minY = 0;
                maxY += fromChap - chapY;
            } else {
                minY += (chapY - fromChap);
            }

            if((chapY + fromChap) >= tilesY){ //if it is over the bottom of the array
                maxY = tilesY-1;
                minY += (tilesY - (chapY + fromChap) -1);
            } else {
                maxY += chapY + fromChap;
            }

            if(minX < 0 || maxX > tilesX || minY < 0 || maxY > tilesY){
                System.out.println("ERROR: \n   minX: " + minX + "    maxX: " + maxX+ "\n    minY: " + minY + "    maxY: " + maxY);
                return tiles;
            }
            //**

            int x = minX, y = minY;
            while(minY++ <= maxY){

                while (x <= maxX){
                    view[viewY][viewX++] = tiles[y][x++];
                }

                viewX = 0;
                x = minX;
                y++;
                viewY++;

            }
            return view;
        } else {
            return tiles;
        }

    }
}

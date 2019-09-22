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

        System.out.println("DRAWING     WIDTH: " + cols + "  HEIGHT: " + rows);

        int scaledSizeW = w / cols; //finding the scaled width
        int scaledSizeH = h / rows; //finding the scaled height

        tileSize = Math.min(scaledSizeH, scaledSizeW); //get the smallest of the 2 (so we dont draw off the edge)

        if(tileSize < minTileSize){
            tileSize = minTileSize;

            int viewW = w / minTileSize;
            int viewH = h / minTileSize;

            int tilesToLeft = maze.getChap().getX() -1;
            int tilesToRight = tiles[0].length - (maze.getChap().getX() + 1);
            int tilesToTop = maze.getChap().getY() -1;
            int tilesToBottom = tiles.length - maze.getChap().getY();

            view = adjustView(viewH, viewW, tilesToLeft, tilesToRight, tilesToTop, tilesToBottom);

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
     * @param tilesToLeft - tiles to the left of the player (on the board)
     * @param tilesToRight - tiles to the right of the player (on the board)
     * @param tilesToTop - tiles above the player (on the board)
     * @param tilesToBottom - tiles below the player (on the board)
     * @return - returns a view within toes parameters
     */
    private Tile[][] adjustView(int viewH, int viewW, int tilesToLeft, int tilesToRight, int tilesToTop, int tilesToBottom) {

        Tile[][] view = new Tile[viewH][viewW]; //the new view of the player, this will leave out some

        System.out.println("ADJUSTING VIEW:   H:" + viewH + "   W:" + viewW);
            //int windowSize = Math.min(viewH, viewW);
            //System.out.println("    WINDOW SIZE" + windowSize);
            //getting the tiles above and on the chap
        int vX = 0, vY = 0; // position in the view

        int y = maze.getChap().getY() - viewH/2; //we want to start getting
        int x = maze.getChap().getX() - viewW/2;
        int extraY = 0;
        int extraX = 0;

        if (viewH/2 > tilesToTop) {
            extraY = viewH/2 - tilesToTop;
            System.out.println("EXTRA Y: " + extraY);
            y = 0;
        }
        if (viewW/2 > tilesToLeft) {
            extraX = viewW/2 - tilesToLeft;
            System.out.println("EXTRA X: " + extraX);
            x = 0;
        }



        outer : while(true){
            if(y >= 0 && y < tiles.length){ // if we have not gone out of the array
                for (; x < maze.getChap().getX() + viewW/2; x++) { //same for X
                    if(x >= 0 && x < tiles[0].length){ //we haven't gone out of the array

                        view[vY][vX++] = tiles[y][x]; //update the view
                    }
                }
                x = (viewW/2 > tilesToLeft) ? 0 : maze.getChap().getX() - viewW/2;
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

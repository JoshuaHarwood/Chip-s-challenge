package nz.ac.vuw.ecs.swen225.a3.render.view;

import nz.ac.vuw.ecs.swen225.a3.maze.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;


public class BoardCanvas extends Canvas {

    private int tileSize = 64;
    private Tile[][] tiles;
    private int width, height;
    public BoardCanvas(int width, int height, Tile[][] tiles) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.setSize(width*tileSize, height*tileSize);
    }

    /**
     * when called this will go through the board and draw each tile
     * @param g - the graphics to be drawn on
     */
    public void drawBoard(Graphics g) {
        System.out.println("DRAWING     WIDTH: " + width + "  HEIGHT: " + height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
//
                Image tileImg = tiles[j][i].getImage();
                int x = i * tileSize;
                int y = j * tileSize;


                g.drawImage(tileImg, x, y, tileSize, tileSize, null);



//                Random r = new Random();
//
//                Color c = new Color(r.nextInt(254),r.nextInt(254),r.nextInt(254));
//
//                g.setColor(c);
//
//                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
////                Image image = new Image("src\\nz\\ac\\vuw\\ecs\\swen225\\a3\\maze\\icons\\key_cyan.png");
//
////                BufferedImage img = new BufferedImage(30,30,);
//
////                g.drawImage(j * tileSize, i * tileSize, tileSize, tileSize, image);

            }
        }

    }

}

package nz.ac.vuw.ecs.swen225.a3.render.view;

import nz.ac.vuw.ecs.swen225.a3.maze.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class BoardCanvas extends Canvas {

    private int tileSize = 32;
    private Tile[][] tiles;

    public BoardCanvas(int width, int height, Tile[][] tiles) {
        this.tiles = tiles;
        this.setSize(width, height);
    }

    public void drawBoard(Graphics g) {

        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                Image tileImg = tiles[j][i].getImage();
               // g.drawImage()

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

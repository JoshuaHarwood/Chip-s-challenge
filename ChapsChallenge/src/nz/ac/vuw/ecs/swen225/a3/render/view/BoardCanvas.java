package nz.ac.vuw.ecs.swen225.a3.render.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

public class BoardCanvas extends Canvas {

    private int tileSize = 40;

    public BoardCanvas(int width, int height) {
        this.setSize(width, height);
    }

    public void drawTestLine(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(new Random().nextInt(getWidth()),new Random().nextInt(getHeight()), getWidth(),getHeight());
    }

    public void drawBoard(Graphics g) {

        int rows = (int)(getWidth()-1/(float)tileSize);
        rows = 17;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {

                Random r = new Random();

                Color c = new Color(r.nextInt(254),r.nextInt(254),r.nextInt(254));

                g.setColor(c);

                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
//                Image image = new Image("src\\nz\\ac\\vuw\\ecs\\swen225\\a3\\maze\\icons\\key_cyan.png");

//                BufferedImage img = new BufferedImage(30,30,);

//                g.drawImage(j * tileSize, i * tileSize, tileSize, tileSize, image);

            }
        }

    }

}

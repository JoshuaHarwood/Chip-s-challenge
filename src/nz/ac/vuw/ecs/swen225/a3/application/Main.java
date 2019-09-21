package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.render.controller.MainFrameController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The Main class.
 */
public class Main {
    private JPanel leftPanel;
    private JTextArea testTextArea;
    private Maze maze;
    private MainFrameController mainFrameController;

    String map = "0809WWWWXWWWWWEEWLWEEWWEEEEEEEWWEEECEEEWW5EEEEEEWWWWWWW1WWWTEEEEEEWWWWWWWWWW";
    //map for board

    public Main() {

        maze = new Maze(map);

        mainFrameController = new MainFrameController(maze.getBoard());
        mainFrameController.showMainFrameWindow();

        mainFrameController.createBoard();

        mainFrameController.redrawBoard(maze.getBoard());


       leftPanel = mainFrameController.getLeftPanel();
       testTextArea = mainFrameController.getTestTextArea();
       initKeys();


        
    }

    private void initKeys(){

        AbstractAction w = new Waction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"),"WPressed");
        leftPanel.getActionMap().put("WPressed",w);

        AbstractAction a = new Aaction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"),"APressed");
        leftPanel.getActionMap().put("APressed",a);

        AbstractAction s = new Saction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"),"SPressed");
        leftPanel.getActionMap().put("SPressed",s);


        AbstractAction d = new Daction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"),"DPressed");
        leftPanel.getActionMap().put("DPressed",d);

    }

    public static void main(String[] args) {
        Main game = new Main();
    }


    private class Waction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("UP");
            mainFrameController.redrawBoard(maze.getBoard());

        }
    }

    private class Aaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("LEFT");


            mainFrameController.redrawBoard(maze.getBoard());

        }
    }

    private class Saction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("DOWN");


            mainFrameController.redrawBoard(maze.getBoard());

        }
    }

    private class Daction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("RIGHT");

            mainFrameController.redrawBoard(maze.getBoard());

        }
    }
}



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
    private Chap chap;

    String map = "0809WWWWXWWWWWEEWLWEEWWEEEEEEEWWEEECEEEWW5EEEEEEWWWWWWW1WWWTEEEEEEWWWWWWWWWW";


    public Main() {

        maze = new Maze(map);
        chap = maze.getChap();

        MainFrameController mainFrameController = new MainFrameController(maze.getBoard());
        mainFrameController.showMainFrameWindow();

        mainFrameController.createBoard();

        mainFrameController.redrawBoard();


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
            chap.move("UP");
            testTextArea.append("\n W has been pressed!");

        }
    }

    private class Aaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            chap.move("LEFT");
            testTextArea.append("\n A has been pressed!");
        }
    }

    private class Saction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            chap.move("DOWN");
            testTextArea.append("\n S has been pressed!");
        }
    }

    private class Daction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            chap.move("RIGHT");
            testTextArea.append("\n D has been pressed!");
        }
    }
}



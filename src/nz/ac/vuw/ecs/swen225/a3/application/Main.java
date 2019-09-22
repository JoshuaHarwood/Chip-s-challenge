package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;

import javax.swing.*;

import java.awt.event.ActionEvent;

/**
 * The Main class.
 */
public class Main {

    private Maze maze;
    private GUI gui;

    String map = "0809WWWWXWWWWWEEWLWEEWWEEEEEEEWWEEECEEEWW5EEEEEEWWWWWWW1WWWTEEEEEEWWWWWWWWWW";
    //map for board

    public Main() {



//        EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {

                    maze = new Maze(map);
					
					gui = new GUI(maze);
                    gui.drawBoard();

//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

       initKeys();

    }

    private void initKeys(){

        AbstractAction w = new Waction();
        gui.getLeftPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"),"WPressed");
        gui.getLeftPanel().getActionMap().put("WPressed",w);

        AbstractAction a = new Aaction();
        gui.getLeftPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"),"APressed");
        gui.getLeftPanel().getActionMap().put("APressed",a);

        AbstractAction s = new Saction();
        gui.getLeftPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"),"SPressed");
        gui.getLeftPanel().getActionMap().put("SPressed",s);


        AbstractAction d = new Daction();
        gui.getLeftPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"),"DPressed");
        gui.getLeftPanel().getActionMap().put("DPressed",d);

    }

    public static void main(String[] args) {
        Main game = new Main();
    }


    private class Waction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("UP");
            gui.drawBoard();
        }
    }

    private class Aaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("LEFT");
            gui.drawBoard();
        }
    }

    private class Saction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("DOWN");
            gui.drawBoard();
        }
    }

    private class Daction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            maze.moveChap("RIGHT");
            gui.drawBoard();
        }
    }
}



package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Trinary;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    	gui.canvas.requestFocus();
    	gui.canvas.addKeyListener(new KeyAdapter() {
    		@Override
            public void keyPressed(KeyEvent e) {
    			boolean levelComplete = false;
    			int action = e.getKeyCode();
            	switch(action) {
            		case KeyEvent.VK_W:
            		case KeyEvent.VK_UP:
            			if(maze.moveChap("UP") == Trinary.DONE)
            				levelComplete = true;
            			break;
            		case KeyEvent.VK_S:
            		case KeyEvent.VK_DOWN:
            			if(maze.moveChap("DOWN") == Trinary.DONE)
            				levelComplete = true;
            			break;
            		case KeyEvent.VK_A:
            		case KeyEvent.VK_LEFT:
            			if(maze.moveChap("LEFT") == Trinary.DONE)
            				levelComplete = true;
            			break;
            		case KeyEvent.VK_D:
            		case KeyEvent.VK_RIGHT:
            			if(maze.moveChap("RIGHT") == Trinary.DONE)
            				levelComplete = true;
            			break;
            	}
            	if(levelComplete) {
            		Object[] options = {"OK"};
            		JOptionPane.showOptionDialog(gui.getFrame(), "LEVEL COMPLETE!\n Now exiting.", "Level Complete", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);//level complete
            		System.exit(0);
            	}
            	gui.drawBoard();
    		}
    	});
    }

    public static void main(String[] args) {
        /*Main game = */new Main();
    }

}



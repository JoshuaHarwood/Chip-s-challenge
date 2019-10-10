package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Trinary;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The Main class.
 */
public class Main {

    private Maze maze;
    private GUI gui;
    private ArrayList<Integer> keysDown = new ArrayList<Integer>();

    String level1 = "1614" + 
    		"WWWWXWWWWWWWWW" + 
    		"WTEWLWEE1EEEEW" + 
    		"WEEEEEEEWEEEEW" + 
    		"WEEECEEEWTEE6W" + 
    		"W5EEEEE5WW3WWW" + 
    		"WWWWWW1WWEEETW" + 
    		"WTEEEEEEWEEEEW" + 
    		"WWWWWW2WWEWWEW" + 
    		"WTEEEEEEWEETEW" + 
    		"W7EEEEEEWE8EEW" + 
    		"WTEEEWW4WEEEEW" + 
    		"WWWWWWEEWWWWWW" + 
    		"WEETEEEEEETEEW" + 
    		"WTEEEETEEEEEEW" + 
    		"WETEEEEEETEEEW" + 
    		"WWWWWWWWWWWWWW";
    //map for board

    public Main() {



//        EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {

                    maze = new Maze(level1, 60);
					
					gui = new GUI(maze);
					maze.addGUI(gui);
                    gui.drawBoard();
                    
                    Thread t1 = new Thread(maze);
                    t1.start();

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
    			keysDown.add(action);
            	switch(action) {
            		//moving the character
            		case KeyEvent.VK_W:
            		case KeyEvent.VK_UP:
            			if(maze.moveChap("UP") == Trinary.DONE)
            				levelComplete = true;
            			break;
            		case KeyEvent.VK_S:
                		if(keysDown.contains(KeyEvent.VK_CONTROL)) 
                			//exit the game, saves the game state, game will
                			//resume next time the application will be started
                			break;
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
            		//pausing and resuming the game
            		case KeyEvent.VK_SPACE:
            			//TODO pause the game bringing up the game paused dialog
            			break;
            		case KeyEvent.VK_ESCAPE:
            			//TODO resume the game closing the game paused dialog
            			break;
            		//performing commands with ctrl
            		case KeyEvent.VK_X:
            			if(keysDown.contains(KeyEvent.VK_CONTROL))
            				//TODO exit the game, the current game state will be lost, the next time
            				//the game is started it will resume from the last unfinished level
            				System.exit(0);
            			break;
            		case KeyEvent.VK_R:
            			if(keysDown.contains(KeyEvent.VK_CONTROL))
            				//TODO loads a saved game
            			break;
            		case KeyEvent.VK_P:
            			if(keysDown.contains(KeyEvent.VK_CONTROL))
            				//TODO starts a new game at the last unfinished level
            			break;
            		case KeyEvent.VK_1:
            			if(keysDown.contains(KeyEvent.VK_CONTROL))
            				//starts a new game at level 1
            				new Main();
            			break;
            	}
            	if(levelComplete) {
            		//TODO load next level, save information that that level was completed
            		Object[] options = {"OK"};
            		JOptionPane.showOptionDialog(gui.getFrame(), "LEVEL COMPLETE!\n Now exiting.", "Level Complete", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);//level complete
            		System.exit(0);
            	}
            	gui.drawBoard();
    		}
    		public void keyReleased(KeyEvent e) {
    			keysDown.remove(keysDown.indexOf(e.getKeyCode()));
    		}
    	});
    }

    public static void main(String[] args) {
        /*Main game = */new Main();
    }

}



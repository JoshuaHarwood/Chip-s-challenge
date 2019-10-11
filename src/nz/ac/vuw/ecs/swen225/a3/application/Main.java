package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Trinary;
import nz.ac.vuw.ecs.swen225.a3.persistence.Persistence;

import javax.json.JsonObject;
import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The Main class.
 * @author Henry Stoupe - 300432963 & Joshua Hindley - 300438963
 */
public class Main {

	private static Maze maze;
	private static GUI gui;
	private static int level;

	//TODO need to save the game before exiting no matter what.
	//TODO need to load the game 

	/**
	 * Gets the current level's Maze object
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	//TODO when paused, timeLeft should not decrease

	private ArrayList<Integer> keysDown = new ArrayList<Integer>();

	static String level1 = "1414" +
			"WWWWWWWWWWWXWW" +
			"WTEEEEEEIWWLWW" +
			"WEEECEEEEEET7W" +
			"W5EEEEEEEEEEEW" +
			"WWWWWWWW1W2WWW" +
			"WTEEEEEEEWEEEW" +
			"W6EEEEETEWTEEW" +
			"WWWWWWWWWWETEW" +
			"WEEETTEEE3EEEW" +
			"WEEEEETEEWEEEW" +
			"WEETEETEEWEEEW" +
			"WEEETETEEWTETW" +
			"WETEETEEEWTTTW" +
			"WWWWWWWWWWWWWW";

	static String level2 = "1614" +
			"WWWWXWWWWWWWWW" +
			"WTEWLWEE1EEEEW" +
			"WEEEEEEEWEEEEW" +
			"WEEECEEEWTEE6W" +
			"W5EEIEE5WW3WWW" +
			"WWWWWW1WWEEETW" +
			"WTEEEEEEWEEEEW" +
			"WWWWWW2WWEWWEW" +
			"WTEEEEEEWEETEW" +
			"W7EEEYEEWE8EEW" +
			"WTEEEWW4WEEEEW" +
			"WWWWWWEEWWWWWW" +
			"WEETEEEEEETEEW" +
			"WTEEEETEEYEEEW" +
			"WETEEEEEETEEEW" +
			"WWWWWWWWWWWWWW" +
			"Y=LDLUURRD";

	static String level3 = "2530" +
			"WWWWWWWWWWWXWWWWWWWWWWWWWWWWWW" +
			"WITEEE5WEEWLWEEEWEEYEEEEEYEEEW" +
			"WEEECEEWEEEEEEEEEEEEEYEEEEEEEW" +
			"WETEEEEWEEEEEEEEWEEEEEEYEEEEEW" +
			"WWWWEWWWWWWWWWWWWWWWWWWWWWEWWW" +
			"WEEEEEEETEEWETYEEEEEEEEEEEEETW" +
			"WEETEEETEEEWEEEETEEEEEEEEEEEEW" +
			"WEEEEEEEEEE4EEEEEEETEYEEEEEEEW" +
			"WEETEEETEEEWEEEETEEEEEEEEEEEEW" +
			"WEEEEEEEEE6WEEEEEEEEYEEEEEEETW" +
			"WWWWW1WWWWWWWWWWWWWWWWWWWWWWWW" +
			"WETEEEEEEEETEEWEETEEEEEEEYEE7W" +
			"WEEETEEEEEEEEEWEEEEEEEEEEEEEEW" +
			"WEETEEEETEEEEEWEEEETEEEEEEEEYW" +
			"WEEEEEEEEEEEEE2EYEEEEEEEEETEEW" +
			"WEEETEEEETEEEEWEEEEEEEETEEEEEW" +
			"WEEEEETEEEEEEEWEETEEEEEEEEEEEW" +
			"WWWWWWWWWWWWWWWWWWWWWW3WWWWWWW" +
			"W8EEEETEEETEEEWEEEEEYEEEEEEEEW" +
			"WEEEETEEEETEEEWEEEEEEEEEEEYEEW" +
			"WEEEEEEEEEETEEWEEEEEYEEEEEEEEW" +
			"WEETEEEEEEEEEE1YEEEEEEEEEEEEEW" +
			"WEEEEEEEEEEEEEWEEEEEEEEEEEEEEW" +
			"WEEEEEEEEEEEEEWEEEETEEEEETE5EW" +
			"WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW" +
			"Y=DDUU" +
			"Y=DDUU" +
			"Y=DUUD" +
			"Y=UUDD" +
			"Y=DDDDRRRUUUULLL" +
			"Y=DDRRRUUUULLLDD" +
			"Y=UUUULLDDDDRR" +
			"Y=DDUU" +
			"Y=LLRR" +
			"Y=ULRDDLRU" + 
			"Y=DDDLUUUR"+
			"Y=DDLLRRUU" +
			"Y=DDRRLLUU" +
			"Y=UDRRLLDU";
	//map for board

	/**
	 * Creates a new Main object for the program to use.
	 * @param l The level number
	 */
	public Main(int l) {
		updateMaze(l);
		init(maze);
		initKeys();
	}

	/**
	 * Updates the maze.
	 * @param l the level to update the maze to.
	 */
	private static void updateMaze(int l) {
		level = l;

		if(level == 1) 
			maze = new Maze(level1, 60, level);
		else if(level == 2)
			maze = new Maze(level2, 120, level);
		else if(level == 3)
			maze = new Maze(level3, 180, level);
		else {
			Object[] options = {"OK"};
			JOptionPane.showOptionDialog(gui.getFrame(), "Well done, you have completed the game!\nThanks for playing.\n The program will now exit.", "You Win!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			level = 1;
			System.exit(0);
		}
	}

	/**
	 * Initializes the GUI and starts the thread for enemies
	 * @param m The current maze
	 */
	public static void init(Maze m) {
		gui = new GUI(maze);
		maze.addGUI(gui);
		gui.drawBoard();
		startThread();
	}

	/**
	 * Initializes a keyListener and changes the focus.
	 */
	private void initKeys(){
		gui.getBoardCanvas().requestFocus();
		gui.getBoardCanvas().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				boolean levelComplete = false;
				int action = e.getKeyCode();
				keysDown.add(action);
				switch(action) {
				//moving the character
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					if(!maze.isPaused())
						if(maze.moveChap("UP") == Trinary.DONE)
							levelComplete = true;
					break;
				case KeyEvent.VK_S:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//exit the game, saves the game state, game will
						//resume next time the application will be started
						maze.pause();
						String name = JOptionPane.showInputDialog("Please enter a name for the save:");
						Persistence.save(maze, name);
						System.exit(0);
					}
					else {
						if(!maze.isPaused())
							if (maze.moveChap("DOWN") == Trinary.DONE)
								levelComplete = true;
					}
					break;
				case KeyEvent.VK_DOWN:
					if(!maze.isPaused())
						if(maze.moveChap("DOWN") == Trinary.DONE)
							levelComplete = true;
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					if(!maze.isPaused())
						if(maze.moveChap("LEFT") == Trinary.DONE)
							levelComplete = true;
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					if(!maze.isPaused())
						if(maze.moveChap("RIGHT") == Trinary.DONE)
							levelComplete = true;
					break;
					//pausing and resuming the game
				case KeyEvent.VK_SPACE:
					maze.pause();
					break;
				case KeyEvent.VK_ESCAPE:
					maze.resume();
					break;
					//performing commands with ctrl
				case KeyEvent.VK_X:
					if(keysDown.contains(KeyEvent.VK_CONTROL))
						System.exit(0);
					//TODO resume the game from the last unfinished level the next time it loads
					break;
				case KeyEvent.VK_R:
					if(keysDown.contains(KeyEvent.VK_CONTROL))
						//TODO loads a saved game
						//TODO get JSON from save file
						//Persistence.load([???]);
						break;
				case KeyEvent.VK_P:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//starts a new game at the last unfinished (current) level
						maze.cleanUpOldMaze();
						gui.hideGUI();
						new Main(level);
					}
					break;
				case KeyEvent.VK_1:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//starts a new game at level 1
						maze.cleanUpOldMaze();
						gui.hideGUI();
						new Main(1);
					}
					break;
				}
				if(levelComplete) {
					//loads the next level
					maze.cleanUpOldMaze();
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(gui.getFrame(), "LEVEL COMPLETE!", "Level Complete", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);//level complete
					gui.hideGUI();
					new Main(level+1);
				}
			}
			public void keyReleased(KeyEvent e) {
				if(keysDown.indexOf(e.getKeyCode()) != -1)
					keysDown.remove(keysDown.indexOf(e.getKeyCode()));
				if(!maze.isPaused()) {
					if(maze.getTimeLeft() <= 0 || !maze.isCurrent()) {
						String message = "You ran out of time.";
						if(maze.getTimeLeft() > 0)
							message = "You were eaten by a crab.";
						//OUTTA TIME
						Object[] options = {"RESTART LEVEL", "QUIT"};
						int option = JOptionPane.showOptionDialog(gui.getFrame(), message, "Game Over", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);//level complete
						if(option == 0) {
							//restart level
							maze.cleanUpOldMaze();
							gui.hideGUI();
							new Main(level);
						} else
							System.exit(0);
					}
					if(maze.getTimeLeft() > 0 && maze.isCurrent())
						gui.drawBoard();
				}
			}
		});
	}


	/**
	 * Starts a new thread in the maze for the enemies' movement.
	 */
	private static void startThread() {
		Thread t1 = new Thread(maze);
		t1.start();
	}

	/**
	 * The method to start the program.
	 * @param args The arguments (none for this program)
	 */
	public static void main(String[] args) {
		new Main(1); 
		//TODO load a saved game (or previous level)
	}
}

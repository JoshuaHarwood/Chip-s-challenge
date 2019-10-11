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
 * @author //TODO UNKNOWN & Joshua Hindley - 300438963
 */
public class Main {

	private static Maze maze;
	private static GUI gui;
	private static int level;
	//TODO use this level variable

	/**
	 * Gets the current level's Maze object
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	//TODO when paused, timeLeft should not decrease

	private ArrayList<Integer> keysDown = new ArrayList<Integer>();

	String level1 = "1414" +
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

	String level2 = "1614" +
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

	String level3 = "2530" +
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
	 */
	public Main() {
		//TODO add parameter
		maze = new Maze(level3, 180, 3);


		init(maze);

		initKeys();

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
		//		initKeys();
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
					if(maze.moveChap("UP") == Trinary.DONE)
						levelComplete = true;
					break;
				case KeyEvent.VK_S:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//exit the game, saves the game state, game will
						//resume next time the application will be started
						String name = JOptionPane.showInputDialog("Please enter a name for the save:");
						Persistence.save(maze, name);
					}
					else {
						if (maze.moveChap("DOWN") == Trinary.DONE)
							levelComplete = true;
					}
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
						System.exit(0);
					//TODO resume the game from the last unfinished level the next time it loads
					break;
				case KeyEvent.VK_R:
					if(keysDown.contains(KeyEvent.VK_CONTROL))
						//TODO loads a saved game
						//Persistence.load([???]);
						break;
				case KeyEvent.VK_P:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//TODO starts a new game at the last unfinished level
						maze.cleanUpOldMaze();
						gui.hideGUI();
						new Main();//currentLevel
					}
					break;
				case KeyEvent.VK_1:
					if(keysDown.contains(KeyEvent.VK_CONTROL)) {
						//starts a new game at level 1
						maze.cleanUpOldMaze();
						gui.hideGUI();
						new Main();//1
					}
					break;
				}
				if(levelComplete) {
					//TODO load next level, save information that that level was completed
					maze.cleanUpOldMaze();
					Object[] options = {"OK"};
					JOptionPane.showOptionDialog(gui.getFrame(), "LEVEL COMPLETE!\n Now exiting.", "Level Complete", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);//level complete

					System.exit(0);
				}
			}
			public void keyReleased(KeyEvent e) {
				if(keysDown.indexOf(e.getKeyCode()) != -1)
					keysDown.remove(keysDown.indexOf(e.getKeyCode()));
				if(maze.getTimeLeft() <= 0 || !maze.getMazeIsCurrent()) {
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
						new Main();
					} else
						System.exit(0);
				}
				gui.drawBoard();
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
		//		Main game = new Main();
		new Main();
	}
}

package nz.ac.vuw.ecs.swen225.a3.maze;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import nz.ac.vuw.ecs.swen225.a3.application.GUI;

/**
 * @author Joshua Hindley - 300438963
 */
public class Maze implements Runnable {
	private Tile[][] board; //board[y][x]
	private GUI gui;
	private Tile behindChap;
	private Chap chap;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int treasureLeft = 0;
	private long timeStarted;
	private int secondsToCompleteLevel;
	private boolean mazeIsCurrent = true;

	//TODO add checks for invalid characters/boards

	public void run(){
		while(mazeIsCurrent) {
			try {
				Thread.sleep(500);
				if(!takeEnemyTurn())
					break;
				gui.drawBoard();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a maze.
	 * @param board this level's board as a string
	 * @param timeToComplete 
	 */
	public Maze(String board, int timeToComplete) {
		generateBoard(board);

		timeStarted = System.currentTimeMillis();
		secondsToCompleteLevel = timeToComplete;
	}

	/**
	 * Creates a new maze with a width and height.
	 * This is the constructor used when loading from JSON.
	 * @param width The width of the maze
	 * @param height The height of the maze
	 */
	public Maze(int width, int height) {
		board = new Tile[height][width];
	}
	
	/**
	 * Takes the enemy's turn
	 * @return a boolean. True if the game is still playing
	 * 		False if time ran out or ch
	 */
	public boolean takeEnemyTurn() {
		Tile newBehind;
		char nextMove;
		int x, y, newX, newY;
		for(Enemy enemy : enemies) {
			x = enemy.getX();
			y = enemy.getY();
			newX = x;
			newY = y;
			nextMove = enemy.getNextMove();
			if(nextMove == 'L')
				newX--;
			else if(nextMove == 'R')
				newX++;
			else if(nextMove == 'U')
				newY--;
			else if(nextMove == 'D')
				newY++;

			newBehind = board[newY][newX];
			if(newBehind instanceof Chap) {
				return false;
			}
			
			if(getTimeLeft() < 0) {
				Robot robot;
				try {
					robot = new Robot();
					robot.keyPress(KeyEvent.VK_ADD);
					robot.keyRelease(KeyEvent.VK_ADD);
				} catch (AWTException e) {
					e.printStackTrace();
				}	
				return false;
			}

			board[newY][newX] = enemy;
			board[y][x] = enemy.getTileBehindEnemy();
			enemy.setTileBehindEnemy(newBehind);
			enemy.setX(newX);
			enemy.setY(newY);
		}
		return true;
	}

	/**
	 * Generates the board for this level.
	 * @param board this level's board as a string
	 */
	public void generateBoard(String board) {
		//create a map of char to TileType
		HashMap<Character, TileType> lettersToTiles = new HashMap<Character, TileType>() {
			private static final long serialVersionUID = 1L;

			{
				put('C', TileType.Chap);
				put('W', TileType.Wall);
				put('T', TileType.Treasure);
				put('X', TileType.Exit);
				put('L', TileType.ExitLock);
				put('I', TileType.Info);
				put('1', TileType.Door1);
				put('2', TileType.Door2);
				put('3', TileType.Door3);
				put('4', TileType.Door4);
				put('5', TileType.Key1);
				put('6', TileType.Key2);
				put('7', TileType.Key3);
				put('8', TileType.Key4);
				put('E', TileType.Empty);
				put('Y', TileType.Enemy);
			}
		};
		//get the height and width
		char[] mapAsChar = board.toCharArray();
		int height = Integer.parseInt((String.valueOf(mapAsChar[0])).concat(String.valueOf(mapAsChar[1])));
		int width = Integer.parseInt((String.valueOf(mapAsChar[2])).concat(String.valueOf(mapAsChar[3])));

		this.board = new Tile[height][width];
		int x = 0, y = 0;
		//fill the board
		for(int i = 4; i < mapAsChar.length; i++) {
			//for Chap
			if(lettersToTiles.get(mapAsChar[i]) == TileType.Chap) {
				chap = new Chap(x, y);
				this.board[y][x] = chap;
				behindChap = new Tile(TileType.Empty, x, y);
				//for enemy
			} else if(lettersToTiles.get(mapAsChar[i]) == TileType.Enemy) {
				enemies.add(new Enemy(x, y));
				this.board[y][x] = enemies.get(enemies.size() -1);
				enemies.get(enemies.size() -1).setTileBehindEnemy(new Tile(TileType.Empty, x, y));;
			} else {
				this.board[y][x] = new Tile(lettersToTiles.get(mapAsChar[i]), x, y);
				if(this.board[y][x].type == TileType.Treasure)
					treasureLeft++;
			}
			x++;
			if(x == width) {
				x = 0;
				y++;
			}
			if(y == height) {
				int enemyNo = 0;
				ArrayList<Character> enemyMoves = new ArrayList<Character>();
				for(int index = i++; index < mapAsChar.length; index++) {
					if(mapAsChar[index] == 'Y') {
						index += 2; //skip the equals
						while(index < mapAsChar.length && mapAsChar[index] != 'Y') {
							enemyMoves.add(mapAsChar[index]);
							index++;
						}
						//add enemy moves to enemy
						enemies.get(enemyNo).setMoves(enemyMoves);
						enemyNo++;
						enemyMoves = new ArrayList<Character>();
					}
				}
				break;
			}
		}
	}

	/**
	 * Tries to move Chap to a tile on the board given a direction.
	 * @param dir The direction to move Chap ("UP", "DOWN", "LEFT", "RIGHT")
	 * @return if the move is valid and therefore if Chap was moved
	 */
	public Trinary moveChap(String dir) {

		int x = chap.getX();
		int y = chap.getY();

		y = (dir.equalsIgnoreCase("UP")) ? y - 1 : (dir.equalsIgnoreCase("DOWN")) ? y + 1 : y;
		x = (dir.equalsIgnoreCase("LEFT")) ? x - 1 : (dir.equalsIgnoreCase("RIGHT")) ? x + 1 : x;

		//no move is being made
		if(x == chap.getX() && y == chap.getY())
			return Trinary.FALSE;

		//Chap cannot move to the specified tile
		if(!board[y][x].chapCanMoveHere(chap.getAllKeys(), treasureLeft == 0))
			return Trinary.FALSE;

		if(board[y][x] instanceof Enemy) {
			//gameover
			System.exit(0);
		}

		if(board[y][x].type == TileType.Treasure)
			treasureLeft--;
		else if(board[y][x].type == TileType.Key1)
			chap.addKey(TileType.Key1);
		else if(board[y][x].type == TileType.Key2)
			chap.addKey(TileType.Key2);
		else if(board[y][x].type == TileType.Key3)
			chap.addKey(TileType.Key3);
		else if(board[y][x].type == TileType.Key4)
			chap.addKey(TileType.Key4);
		else if(board[y][x].type == TileType.Door1)
			chap.removeKey(TileType.Key1);
		else if(board[y][x].type == TileType.Door2)
			chap.removeKey(TileType.Key2);
		else if(board[y][x].type == TileType.Door3)
			chap.removeKey(TileType.Key3);
		else if(board[y][x].type == TileType.Door4)
			chap.removeKey(TileType.Key4);
		else if(board[y][x].type == TileType.Info)
			helpAlert();

		//remove Chap from the board
		board[chap.getY()][chap.getX()] = behindChap;

		if(board[y][x].type == TileType.Exit)
			return Trinary.DONE;

		//update the tile behind Chap
		else if(board[y][x].type == TileType.Info || board[y][x].type == TileType.ExitUnlock)
			behindChap = board[y][x];
		else if(board[y][x].type == TileType.ExitLock)
			behindChap = new Tile(TileType.ExitUnlock, x, y);
		else
			behindChap = new Tile(TileType.Empty, x, y);

		//updates Chap's position
		board[y][x] = chap;
		chap.setX(x);
		chap.setY(y);

		//TODO Call mainframeController.redraw() here ?

		return Trinary.TRUE;
	}

	/**
	 * Gets this game's board.
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * Gets the Chap tile.
	 * @return Chap
	 */
	public Chap getChap(){
		return chap;
	}

	/**
	 * Gets the number of treasures left to collect.
	 * @return the treasure left to collect
	 */
	public int getTreasureLeft() {
		return treasureLeft;
	}

	/**
	 * TODO make sure this actually works
	 * Updates the variables of this Maze object.
	 * To be called when a level is loaded from a JSON file.
	 * @param timeLeft
	 * 			The time left (in seconds) for a player to complete the level
	 */
	public void updateVariables(int timeLeft) {
		//updates Chap and treasureLeft
		treasureLeft = 0;
		for(Tile[] t: board)
			for(Tile tile : t)
				if(tile instanceof Chap)
					chap = (Chap) tile;
				else if(tile.type == TileType.Treasure)
					treasureLeft++;
				else if(tile instanceof Enemy)
					enemies.add((Enemy)tile);

		//updates time left
		timeStarted = System.currentTimeMillis();
		secondsToCompleteLevel = timeLeft;
	}

	@Override
	public String toString() {
		String boardText = "";

		for (int i = 0; i < board.length ; i++) {
			for (int j = 0; j < board[i].length; j++) {
				boardText += board[i][j].toString();
			}
		}

		return boardText;
	}

	/**
	 * Sets the GUI object. Will be used to redraw the board when an enemy moves
	 * @param g The GUI object
	 */
	public void addGUI(GUI g) {
		gui = g;
	}

	/**
	 * Should be called when a level is completed or failed.
	 * Quits the thread for enemies.
	 */
	public void cleanUpOldMaze() {
		mazeIsCurrent = false;
	}

	/**
	 * Gets the width of the board.
	 * @return the width
	 */
	public int getX(){
		return board[0].length;
	}

	/**
	 * Gets the height of the board
	 * @return the height
	 */
	public int getY(){
		return board.length;
	}

	/**
	 * Sets a tile on the board, given an x and y.
	 * @param x The x-position of the tile
	 * @param y The y-position of the time
	 * @param tile The type of tile to set
	 */
	public void setTile(int x, int y, TileType tile){
	    Tile t = new Tile(tile,x,y);
	    board[y][x] = t;
    }

    /**
     * Sets this maze's chap object
     * @param c The chap object
     */
    public void setChap(Chap c){
	    this.chap = c;
    }
    
    public void setBehindChap(Tile t){
	    behindChap = t;
    }

	/**
	 * Gets the number of seconds that the user has left to complete the level.
	 * @return the number of seconds the user has left
	 */
	public int getTimeLeft() {
		return (int) (secondsToCompleteLevel - (System.currentTimeMillis() - timeStarted) / 1000);
	}
	
	
	public int getLevel() {
		return 1; //TODO: will need to return the level
	}
	
	/**
	 * this is the method to pause the game
	 */
	public void pause() {
		//TODO: implement pausing 
	}
	
    public boolean getMazeIsCurrent() {
        return mazeIsCurrent;
    }

    public void setMazeIsCurrent(boolean mazeIsCurrent) {
        this.mazeIsCurrent = mazeIsCurrent;
    }
    
    /**
     * the method will create a pop-up with help about the level
     */
    public void helpAlert() {
    	String goal = "The goal here is to collect all the coconuts to fill in the hole in order to leave this Island.\n";
    	
		int level = this.getLevel();
		if(level == 1) {
			goal += "To do this you must collect the different coloured Axes to cut down the corresponding coloured tree\n" +
					"and to avoid the dangerous crabs!\n";
		} else {
			goal += "\n";
		}
		
		String help = goal +  "good luck!\n\nMovement: Use the W, A, S, D to move Chap\n";
		
		JOptionPane.showMessageDialog(null, help, "Help", 3);
    }
}

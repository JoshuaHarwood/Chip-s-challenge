package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.HashMap;

/**
 * @author Joshua Hindley - 300438963
 */
public class Maze {
	private Tile[][] board; //board[y][x]
	private Tile behindChap;
	private Chap chap;
	private int treasureLeft = 0;
	private long timeStarted;
	private int secondsToCompleteLevel;

	//TODO add checks for invalid characters/boards

	/**
	 * Creates a maze.
	 * @param board this level's board as a string
	 */
	public Maze(String board, int timeToComplete) {
		generateBoard(board);
		
		timeStarted = System.currentTimeMillis();
		secondsToCompleteLevel = timeToComplete;
	}
	
	public Maze(int width, int height) {
		board = new Tile[height][width];
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
		
		//updates time left
		timeStarted = System.currentTimeMillis();
		secondsToCompleteLevel = timeLeft;
	}

	/**
	 * Converts the board to a string of letters
	 * @return
	 */
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
	 * Gets the number of seconds that the user has left to complete the level.
	 * @return the number of seconds the user has left
	 */
	public int getTimeLeft() {
		return (int) (secondsToCompleteLevel - (System.currentTimeMillis() - timeStarted) / 1000);
	}

}

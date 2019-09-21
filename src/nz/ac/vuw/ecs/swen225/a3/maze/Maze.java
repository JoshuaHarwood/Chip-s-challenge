package nz.ac.vuw.ecs.swen225.a3.maze;

import nz.ac.vuw.ecs.swen225.a3.render.controller.MainFrameController;

import java.util.HashMap;

/**
 * @author Joshua Hindley
 */
public class Maze {
	private Tile[][] board; //board[y][x]
	private Tile behindChap;
	private Chap chap;
	private int treasureLeft = 0;

	//TODO add checks for invalid characters/boards
	//TODO add a timer

	/**
	 * Creates a maze.
	 * @param board this level's board as a string
	 */
	public Maze(String board) {
		generateBoard(board);
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
	 * Tries to move Chap to a tile on the board. given a direction
	 * @param dir - the direction;   UP, DOWN, LEFT, RIGHT
	 * @return - if moved
	 */
	public boolean moveChap(String dir) {

		int x = chap.getX();
		int y = chap.getY();
		
		y = (dir.equalsIgnoreCase("UP")) ? y - 1 : (dir.equalsIgnoreCase("DOWN")) ? y + 1 : y;
		x = (dir.equalsIgnoreCase("LEFT")) ? x - 1 : (dir.equalsIgnoreCase("RIGHT")) ? x + 1 : x;
		
		//Chap cannot move to the specified tile
		if(!board[y][x].chapCanMoveHere(chap.getAllKeys(), treasureLeft == 0))
			return false;
		
		//remove Chap from the board
		board[chap.getY()][chap.getX()] = behindChap;

		
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


		board[chap.getY()][chap.getX()] = behindChap;

		if(board[y][x].type == TileType.Exit)
		{}	//TODO ENDGAME		

		//update the tile behind Chap
		else if(board[y][x].type == TileType.Info)
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


		return true;
	}

	/**
	 * Gets this game's board
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}

	public Chap getChap(){
		return chap;
	}
	
	/**
	 * Gets the number of treasures left to collect
	 * @return the treasure left to collect
	 */
	public int getTreasureLeft() {
		return treasureLeft;
	}

}

package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.HashMap;

public class Maze {
	private Tile[][] board; //board[y][x]
	private Tile behindChap;
	private Chap chap;
	private int treasureLeft = 0;

	//TODO add checks for invalid characters/boards

	/**
	 * Creates a maze.
	 * @param map this level's map as a string
	 */
	public Maze(String map) {
		generateBoard(map);
	}

	/**
	 * Generates the board for this level.
	 * @param map this level's map as a string
	 */
	public void generateBoard(String map) {
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
		char[] mapAsChar = map.toCharArray();
		int height = Integer.parseInt((String.valueOf(mapAsChar[0])).concat(String.valueOf(mapAsChar[1])));
		int width = Integer.parseInt((String.valueOf(mapAsChar[2])).concat(String.valueOf(mapAsChar[3])));
		board = new Tile[height][width];
		int x = 0, y = 0;
		//fill the board
		for(int i = 4; i < mapAsChar.length; i++) {
			//for Chap
			if(lettersToTiles.get(mapAsChar[i]) == TileType.Chap) {
				chap = new Chap(x, y);
				board[y][x] = chap;
			} else {
				board[y][x] = new Tile(lettersToTiles.get(mapAsChar[i]), x, y);
				if(board[y][x].type == TileType.Treasure)
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
	 * Tries to move Chap to a tile on the board.
	 * @param x The x-coordinate of the tile to move Chap to.
	 * @param y The y-coordinate of the tile to move Chap to.
	 * @return If Chap has been moved or not
	 */
	public boolean moveChap(int x, int y) {
		//the tile is too far away from Chap
		if(Math.abs(chap.getX() - x) + Math.abs(chap.getY() - y) != 1)
			return false;
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

		if(board[y][x].type == TileType.Exit)
		{}	//TODO ENDGAME
		//update the tile behind Chap
		else if(board[y][x].type == TileType.Info || board[y][x].type == TileType.ExitLock) //TODO change exitlock icon to full hole if treasure left == 0
			behindChap = board[y][x];
		else
			behindChap = new Tile(TileType.Empty, x, y);
		//updates Chap's position
		board[y][x] = chap;
		chap.setX(x);
		chap.setY(y);

		return true;
	}



}

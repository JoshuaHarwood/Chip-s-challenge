package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.HashMap;

public class Maze {
	private TileType[][] board; //board[y][x]
	
	//TODO add checks for invalid characters/boards
	
	//create maze from string
	public Maze(String map) {
		generateBoard(map);
	}
	
	public void generateBoard(String map) {
		//create a map of char to TileType
		HashMap<Character, TileType> lettersToTiles = new HashMap<Character, TileType>() {
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
		board = new TileType[height][width];
		int x = 0, y = 0;
		//fill the board
		for(int i = 4; i < mapAsChar.length; i++) {
			board[y][x] = lettersToTiles.get(mapAsChar[i]);
			x++;
			if(x == width) {
				x = 0;
				y++;
			}
		}
	}

	
	
}

package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.application.*;

import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.*;

import javax.json.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Liam Hide - 300451675
 */
public class Persistence {


	static HashMap<Character, TileType> lettersToTiles = new HashMap<Character, TileType>() {
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




	/**
	 * Saves the current state of the game in a Json format.
	 *
	 * @param maze The maze that represents the current state of the game
	 * @param name The name of this save game
	 */
	public static void save(Maze maze, String name) {
		maze.cleanUpOldMaze();

		//level/maze information
		//Need to store: Level number, current score, x and y
		int mazeX = maze.getWidth();
		int mazeY = maze.getHeight();
		int timeLeft = maze.getTimeLeft(); //To save
		int treasureLeft = maze.getTreasureLeft(); //To save
		int level = maze.getLevel(); //To save



		//get Chaps information
		//Need to store: x,y and inventory
		Chap c = maze.getChap();

		int chapX = c.getX();
		int chapY = c.getY();


		List<TileType> keys = c.getAllKeys();


		JsonArrayBuilder inv = convertInventory(keys);
		JsonArray invArr = inv.build();
		System.out.println("Successfully saved chap");

		//Next, get the 'Enemies'
		List<Enemy> enemiesToSave  = maze.getEnemies();
		JsonArrayBuilder enemy = convertEnemies(enemiesToSave);
		JsonArray enemyArr = enemy.build();
		System.out.println("Successfully saved enemies");


		//Next, get information regarding all tiles
		Tile[][] board = maze.getBoard();
		List<Tile> tiles = new ArrayList<>();

		//Convert the 2d array of tiles into a list of the tiles
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Tile t = board[i][j];
				tiles.add(t);
			}
		}

		JsonArrayBuilder tile = convertTiles(tiles);
		JsonArray tileArr = tile.build();
		System.out.println("Successfully saved tiles");


		//Finally, Construct the JSon file
		JsonObject json = Json.createObjectBuilder()
				//Level details will go here
				.add("Maze", Json.createObjectBuilder()
						.add("x", mazeX)
						.add("y", mazeY)
						.add("timeLeft", timeLeft)
						.add("treasureLeft", treasureLeft)
						.add("level", level))
				.add("Enemies", enemyArr)
				.add("Chap", Json.createObjectBuilder()
						.add("x", chapX)
						.add("y", chapY)
						.add("Inventory", invArr))
				.add("Tiles", tileArr)
				.build();

		System.out.println("Successfully saved to Json");


		writeJSONToFile(json, name);

		System.out.println("Successfully created file");
	}


	/**
	 * Loads a game in from a JsonObject file.
	 *
	 * @param json The object file
	 * @param currentMaze The maze object the represents the current game
	 */
	static public void loadGame(JsonObject json, Maze currentMaze) {

		JsonObject MAZE = json.getJsonObject("Maze");
		JsonObject CHAP = json.getJsonObject("Chap");
		JsonArray CHAP_INV = CHAP.getJsonArray("Inventory");
		JsonArray TILES = json.getJsonArray("Tiles");
		JsonArray ENEMIES = json.getJsonArray("Enemies");


		//=====MAZE=====//

		//Maze Dimensions
		Point mazeDimensions = loadMazeDimensions(MAZE);
		int timeLeft = MAZE.getInt("timeLeft");
		int treasureLeft = MAZE.getInt("treasureLeft");
		int level = MAZE.getInt("level");


		//=====CHAP=====//

		//Chap's position
		Point chapPosition = loadChapPosition(CHAP);

		//Keys from chap's inventory
		List<TileType> keys = loadKeys(CHAP_INV);


		//=====TILES=====//
		List<Tile> tiles = loadTilesInfo(TILES);



		//=====ENEMIES=====//
		ArrayList<Enemy> enemies = loadEnemiesInfo(ENEMIES);



		//=====CREATING NEW MAZE=====//
		currentMaze.cleanUpOldMaze();

		createNewMaze(timeLeft, treasureLeft, level, mazeDimensions, chapPosition, keys, tiles, enemies);

	}





	private static void createNewMaze(int timeLeft, int treasureLeft, int levelNo, Point mazeDimensions, Point chapPosition, List<TileType> keys, List<Tile> tiles, ArrayList<Enemy> enemies) {


		StringBuilder level = new StringBuilder();

		level.append(mazeDimensions.x);
		level.append(mazeDimensions.y);

		for (Tile tile : tiles) {
			level.append(tile.type);
		}

		System.out.println(level.toString());


		Maze newMaze = new Maze(level.toString(), 60, levelNo); // <- load level # here

		new Main(newMaze, levelNo, true);

		newMaze.setTimeLeft(timeLeft);
		newMaze.setTreasureLeft(treasureLeft);

	}




	private static ArrayList<Enemy> loadEnemiesInfo(JsonArray ENEMIES){

		ArrayList<Enemy> enemiesl = new ArrayList<>();

		for(int i = 0; i < ENEMIES.size(); i++){
			ArrayList<Character> movesl = new ArrayList<>();

			JsonObject enemyObject = ENEMIES.getJsonObject(i);


			int x = enemyObject.getInt("x");
			int y = enemyObject.getInt("y");

			JsonArray MOVES = enemyObject.getJsonArray("moves");

			for(int j = 0; j < MOVES.size(); j++){
				JsonObject move = ENEMIES.getJsonObject(i);
				Character c = move.getString("move").charAt(1);
				movesl.add(c);
			}


			Enemy e = new Enemy(x,y);
			e.setMoves(movesl);
			enemiesl.add(e);
		}


		return enemiesl;

	}


	private static Point loadChapPosition(JsonObject CHAP) {
		int chapX = CHAP.getInt("x");
		int chapY = CHAP.getInt("y");

		return new Point(chapX, chapY);
	}


	private static Point loadMazeDimensions(JsonObject MAZE) {
		int mazeX = MAZE.getInt("x");
		int mazeY = MAZE.getInt("y");

		return new Point(mazeX, mazeY);
	}


	private static List<Tile> loadTilesInfo(JsonArray TILES) {

		List<Tile> tiles = new ArrayList<>();

		//Go through all tiles in the json file and create new Tiles for each
		for (int i = 0; i < TILES.size(); i++) {

			JsonObject boardJsonObject = TILES.getJsonObject(i);

			int x = boardJsonObject.getInt("x");
			int y = boardJsonObject.getInt("y");



			char letter = boardJsonObject.getString("type").toCharArray()[0];
			TileType type = lettersToTiles.get(letter);

			Tile tile = new Tile(type, x, y);
			tiles.add(tile);

		}

		return tiles;

	}


	private static List<TileType> loadKeys(JsonArray chapInv) {

		List<TileType> keys = new ArrayList<>();

		if (chapInv != null) {
			for (int i = 0; i < chapInv.size(); i++) {
				JsonObject c = chapInv.getJsonObject(i);
				String key = c.getString("key");
				char k = key.charAt(0);
				keys.add(lettersToTiles.get(k));
			}
		}

		return keys;

	}







	//Handles write of a json object into a file
	static void writeJSONToFile(JsonObject json, String saveName) {

		try {

			OutputStream os = new FileOutputStream(saveName + ".json");
			JsonWriter writer = Json.createWriter(os);

			writer.writeObject(json);
			writer.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}







	//Helper methods to convert lists to Arrays, may need some fixing
	private static JsonArrayBuilder convertInventory(List<TileType> list) {

		JsonArrayBuilder invBuilder = Json.createArrayBuilder();

		for (TileType t : list) {
			invBuilder.add(
					Json.createObjectBuilder()
					.add("key", t.toString())

					);


		}
		return invBuilder;

	}

	private static JsonArrayBuilder convertTiles(List<Tile> list) {

		JsonArrayBuilder tilesBuilder = Json.createArrayBuilder();

		for (Tile t : list) {

			tilesBuilder.add(
					Json.createObjectBuilder()
					.add("x", t.getX())
					.add("y", t.getY())
					.add("type", t.type.toString())
					);

		}
		return tilesBuilder;
	}

	private static JsonArrayBuilder convertEnemies(List<Enemy> list){

		JsonArrayBuilder enemiesBuilder = Json.createArrayBuilder();

		for(Enemy e : list){

			enemiesBuilder.add(
					Json.createObjectBuilder()
					.add("x", e.getX())
					.add("y", e.getY())
					.add("nextMove", e.getNextMove())
					.add("moves", convertEnemyMoves(e.getMoves()))
					);

		}
		return enemiesBuilder;
	}

	public static JsonArray convertEnemyMoves(ArrayList<Character> list){

		JsonArrayBuilder movesBuilder = Json.createArrayBuilder();


		for(Character c : list){

			movesBuilder.add(
					Json.createObjectBuilder()
					.add("move",c)
					);
		}
		return movesBuilder.build();
	}


}

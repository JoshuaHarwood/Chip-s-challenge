package nz.ac.vuw.ecs.swen225.a3.persistence;

import nz.ac.vuw.ecs.swen225.a3.application.*;

import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;
import nz.ac.vuw.ecs.swen225.a3.application.GUI;
import nz.ac.vuw.ecs.swen225.a3.application.Main;
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
     * @return the JsonObject the game was saved as
     */
    public static void save(Maze maze, String name) {
        maze.cleanUpOldMaze();

        //TODO - We are not currently tracking level and score etc, this will have to be stored in the Json so we can properly change levels etc
        //Get level/maze information
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


        //Next, get information regarding 'special' tiles

        Tile[][] board = maze.getBoard();
        List<Tile> specialTiles = new ArrayList<>();

        //Convert the 2d array of tiles into a list of the "Special tiles"
        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board[i].length - 2; j++) {

                Tile t = board[i][j];

                if (t.type != TileType.Empty && t.type != TileType.Chap) {
                    specialTiles.add(t);
                }
            }
        }

        JsonArrayBuilder tile = convertTiles(specialTiles);
        JsonArray tileArr = tile.build();
        System.out.println("Successfully saved tiles");


        //Finally, Construct the JSon file

        JsonObject json = Json.createObjectBuilder()
                //Level details will go here
                .add("Maze", Json.createObjectBuilder()
                        .add("x", mazeX)
                        .add("y", mazeY))
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
     * @return The maze object the represents the game
     */
    static public void loadGame(JsonObject json, Maze currentMaze) {

        //TODO:
        // - TIME REMAINING
        // - TREASURE REMAINING


        JsonObject MAZE = json.getJsonObject("Maze");
        JsonObject CHAP = json.getJsonObject("Chap");
        JsonArray CHAP_INV = CHAP.getJsonArray("Inventory");
        JsonArray TILES = json.getJsonArray("Tiles");
        JsonArray enemies = json.getJsonArray("Enemies");


        //=====MAZE=====//

        //Maze Dimensions
        Point mazeDimensions = loadMazeDimensions(MAZE);


        //=====CHAP=====//

        //Chap's position
        Point chapPosition = loadChapPosition(CHAP);

        //Keys from chap's inventory
        List<TileType> keys = loadKeys(CHAP_INV);


        //=====TILES=====//

        List<Tile> tiles = loadTilesInfo(TILES);



        //=====ENEMIES=====//

        ArrayList<Enemy> enemies1 = new ArrayList<>();

        for(int i = 0; i < enemies.size(); i++){
            JsonObject enemyObject = enemies.getJsonObject(i);

            int x = enemyObject.getInt("x");
            int y = enemyObject.getInt("y");

            Enemy e = new Enemy(x,y);
            enemies1.add(e);
        }

        System.out.println("Read enemies");


        //=====CREATING NEW MAZE=====//
        currentMaze.cleanUpOldMaze();
        createNewMaze(mazeDimensions, chapPosition, keys, tiles, enemies1);

    }





    private static void createNewMaze(Point mazeDimensions, Point chapPosition, List<TileType> keys, List<Tile> tiles, ArrayList<Enemy> enemies) {


        Maze newMaze = new Maze(mazeDimensions.x, mazeDimensions.y, 1);


        StringBuilder level = new StringBuilder();

        //ToDo check two numbers? (09 vs 9)
        level.append(mazeDimensions.x);
        level.append(mazeDimensions.y);

        for (Tile tile : tiles) {
            level.append(tile.type);
        }

        newMaze.generateBoard(level.toString());



        //Go through tiles and set each tile
//        for (int i = 0; i < tiles.size(); i++) {
//            Tile tile = tiles.get(i);
//            newMaze.setTile(tile.getX(), tile.getY(), tile.type);
//        }


//        for (int y = 0; y < mazeDimensions.y - 2; y++) {
//            for (int x = 0; x < mazeDimensions.x - 2; x++) {
//                newMaze.setTile(x, y, TileType.Empty);
//            }
//        }


//        //Populate the maze
//        for (Tile t : tiles) {
//            int x = t.getX();
//            int y = t.getY();
//            TileType ty = t.type;
//
//            newMaze.setTile(x, y, ty);
//        }


        //Setup new maze - This is just 'filling in' the stuff that needs to be done, is likely inefficient
        newMaze.setEnemies(enemies);
        newMaze.setChapPosition(chapPosition);

        for(TileType t : keys){
            newMaze.getChap().addKey(t);
        }



        Main.init(newMaze);




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

                keys.add(lettersToTiles.get(key));
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
            );

        }
        return enemiesBuilder;

    }


}

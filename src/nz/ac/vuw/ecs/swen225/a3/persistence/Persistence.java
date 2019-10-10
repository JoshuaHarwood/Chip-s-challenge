package nz.ac.vuw.ecs.swen225.a3.persistence;
import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import javax.json.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liam Hide-Tobin - //TODO add ID number
 */
public class Persistence {



    //Handles conversion of a maze object into a Json type file
    /**
     * Saves the current state of the game in a Json format.
     * @param maze The maze that represents the current state of the game
     * @return the JsonObject the game was saved as
     */
    public static JsonObject save(Maze maze){
        maze.cleanUpOldMaze();

    //TODO - We are not currently tracking level and score etc, this will have to be stored in the Json so we can properly change levels etc
    //Get level/maze information
        //Need to store: Level number, current score, x and y
        int mazeX = maze.getX();
        int mazeY = maze.getY();




    //get Chaps information
        //Need to store: x,y and inventory
        Chap c = maze.getChap();

        int chapX = c.getX();
        int chapY = c.getY();


        List<TileType> keys = c.getAllKeys();


        JsonArrayBuilder inv = convertInventory(keys);
        JsonArray invArr = inv.build();
        System.out.println("Successfully saved chap");



    //Next, get information regarding 'special' tiles
        //Need to store: x,y and type (and status?? - maybe)


        Tile[][] board = maze.getBoard();
        List<Tile> specialTiles = new ArrayList<>();

        //Convert the 2d array of tiles into a list of the "Special tiles"
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++ ){

                Tile t = board[i][j];

                if(t.type != TileType.Empty && t.type != TileType.Chap){
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
                .add("Maze:",Json.createObjectBuilder()
                        .add("x:",mazeX)
                        .add("y:",mazeY))
                .add("Chap:",Json.createObjectBuilder()
                        .add("x:",chapX)
                        .add("y:",chapY)
                        .add("Inventory:", invArr))
                .add("Tiles:", tileArr)
                .build();

        System.out.println("Successfully saved to Json");



        //Todo - Remove this after completion
        writeToFile(json,"test save");

        return json;

    }



    //Handles loading of a Json type file into a maze object
    /**
     * Loads a game in from a JsonObject file.
     * @param json The object file
     * @return The maze object the represents the game
     */
    static public Maze load(JsonObject json){

        //First get game state information
            //Todo - We don't yet store this
        JsonObject mazeD = json.getJsonObject("Maze:");
        int mazeX = mazeD.getInt("x:");
        int mazeY = mazeD.getInt("y:");


        //Second get chaps information
        JsonObject chapD = json.getJsonObject("Chap:");
        int chapX = chapD.getInt("x:");
        int chapY = chapD.getInt("y:");

        JsonArray chapInv = json.getJsonArray("Inventory:");

        //Get keys in chaps inventory, then, create a list of "Tiletype" objects
        List<TileType> keys = new ArrayList<>();

//Todo - Still need to create the Tiletype objects and add to list + test this
        if(chapInv != null) {
            for (int i = 0; i < chapInv.size(); i++) {
                JsonObject c = chapInv.getJsonObject(i);

                String key = c.getString("key:");

            }
        }

        System.out.println("Read chap");

        //Third, get grid information

        List<Tile> tiles = new ArrayList<>();

        JsonArray board = json.getJsonArray("Tiles:");

        for(int i = 0; i < board.size(); i++){
            JsonObject c = board.getJsonObject(i);

            int x = c.getInt("x:");
            int y = c.getInt("y:");
            TileType type = TileType.valueOf(c.getString("type:"));

            Tile t = new Tile(type,x,y);
            tiles.add(t);

        }

        System.out.println("Read tiles");

        //Create a grid of 'empty tiles'
        //Todo - these need to be +2 for some reason
            Maze newMaze = new Maze(mazeX+2,mazeY+2);

            for(int y = 0; y < mazeY; y++){
                for(int x = 0; x < mazeX; x++){
                    newMaze.setTile(x,y, TileType.Empty );
                }
            }


        //Populate the maze
        for(Tile t : tiles){
            int x = t.getX();
            int y = t.getY();
            TileType ty = t.type;

            newMaze.setTile(x,y,ty);
        }

        newMaze.setTile(chapX,chapY,TileType.Chap);
        Chap newChap = new Chap(chapX,chapY);
        newMaze.setChap(newChap);

                //Todo - This should be replaced with the saved timeleft
        //newMaze.updateVariables(0);

        return newMaze;

    }

    //Handles write of a json object into a file
    static void writeToFile(JsonObject json, String saveName) {

        try{

            FileWriter fileWriter = new FileWriter(saveName+".json");
            JsonWriter writer = Json.createWriter(fileWriter);
            writer.write(json);
            System.out.println("Successfully wrote to file");

        }catch(Exception e){
            System.out.println(e);
        }


    }

//Helper methods to convert lists to Arrays, may need some fixing

   private static JsonArrayBuilder convertInventory(List<TileType> list){

        JsonArrayBuilder invBuilder = Json.createArrayBuilder();

        for(TileType t : list){
            invBuilder.add(
                    Json.createObjectBuilder()
                            .add("key:", t.toString())

            );


        }
        return invBuilder;

    }

    private static JsonArrayBuilder convertTiles(List<Tile> list){

        JsonArrayBuilder tilesBuilder = Json.createArrayBuilder();

        for(Tile t : list){

            tilesBuilder.add(
                    Json.createObjectBuilder()
                            .add("x:",t.getX())
                            .add("y:", t.getY())
                            .add("type:", t.type.toString())
            );

        }
        return tilesBuilder;
    }



}

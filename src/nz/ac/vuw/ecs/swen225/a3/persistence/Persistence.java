package nz.ac.vuw.ecs.swen225.a3.persistence;
import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import javax.json.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistence {




    //Handles conversion of a maze object into a Json type file
    public static void save(Maze maze){



    //Todo - We are not currently tracking level and score etc, this will have to be stored in the Json so we can properly change levels etc
    //Get level/maze information
        //Need to store: Level number, current score





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

                .add("Chap:",Json.createObjectBuilder()
                        .add("x: ",chapX)
                        .add("y: ",chapY)
                        .add("Inventory: ", invArr))
                .add("Tiles: ", tileArr)
                .build();

        System.out.println("Successfully saved to Json");

        writeToFile(json);


        //Todo - Remove this after completion
        load(json);

    }



    //Handles loading of a Json type file into a maze object
    static Maze load(JsonObject json){

        //First get game state information
            //Todo - We don't yet store this


        //Second get chaps information
        JsonObject chapD = json.getJsonObject("Chap:");
        int chapX = chapD.getInt("x: ");
        int chapY = chapD.getInt("y: ");

        JsonArray chapInv = json.getJsonArray("Inventory: ");

        //Get keys in chaps inventory, then, create a list of "Tiletype" objects
        List<TileType> keys = new ArrayList<>();


        if(chapInv != null) {
            for (int i = 0; i < chapInv.size(); i++) {
                JsonObject c = chapInv.getJsonObject(i);

                //Todo - Still need to create the Tiletype objects and add to list + test this
                String key = c.getString("key");

            }
        }
        System.out.println("Read chap");

        //Third, get grid information

        List<Tile> tiles = new ArrayList<>();

        JsonArray board = json.getJsonArray("Tiles: ");

        for(int i = 0; i < board.size(); i++){
            JsonObject c = board.getJsonObject(i);

            int x = c.getInt("x");
            int y = c.getInt("y");
            TileType type = TileType.valueOf(c.getString("type"));

            Tile t = new Tile(type,x,y);
            tiles.add(t);

        }
        System.out.println("Read tiles");

        //Create a grid of 'empty tiles'
            //Todo - The amount of 'E's here is random tbh
       String boardString = "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";

        //Populate the maze
        //Todo - Populate maze


        return null;

    }

    //Handles write of a json object into a file
    static void writeToFile(JsonObject json) {

        try{

            FileWriter fileWriter = new FileWriter("Savegame.json");
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
                            .add("key: ", t.toString())

            );


        }
        return invBuilder;

    }

    private static JsonArrayBuilder convertTiles(List<Tile> list){



        //JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder tilesBuilder = Json.createArrayBuilder();

        for(Tile t : list){

            tilesBuilder.add(
                    Json.createObjectBuilder()
                            .add("x",t.getX())
                            .add("y", t.getY())
                            .add("type", t.type.toString())
            );

        }
        return tilesBuilder;
    }



}

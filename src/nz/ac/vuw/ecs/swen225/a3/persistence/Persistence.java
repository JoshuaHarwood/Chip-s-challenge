package nz.ac.vuw.ecs.swen225.a3.persistence;
import jdk.nashorn.internal.parser.JSONParser;
import nz.ac.vuw.ecs.swen225.a3.maze.Chap;
import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import javax.json.*;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
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

        JsonArray inv = convertInventory(keys);





    //Next, get information regarding 'special' tiles
        //Need to store: x,y and type (and status?? - maybe)



        Tile[][] board = maze.getBoard();
        List<Tile> specialTiles = new ArrayList<>();

        //Convert the 2d array of tiles into a list of the "Special tiles"
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++ ){

                Tile t = board[i][j];

                if(t.type != TileType.Empty && t.type != TileType.Chap){
                    //System.out.println("Added tile: "+ t.getX()+ " "+t.getY() +" "+t.type);
                    specialTiles.add(t);
                }
            }
        }

       // JsonArray tileArr = convertTiles(specialTiles); //

        JsonArray tileArr = Json.createArrayBuilder().build();





    //Finally, Construct the JSon file

        JsonObject json = Json.createObjectBuilder()
                //Level details will go here

                .add("Chap:",Json.createObjectBuilder()
                        .add("x: ",chapX)
                        .add("y: ",chapY)
                        .add("Inventory: ", inv))
                .add("Tiles: ", tileArr)
                .build();

        System.out.println("Successfully saved to Json");

    }

    //Handles conversion of a Json type file into a maze object
    static void load(JsonObject json){



    }

    //Handles write of a json object into a file
    static void writeToFile(JsonObject json) {

    }

//Helper methods to convert lists to Arrays, may need some fixing

    private static JsonArray convertInventory(List<TileType> list){

        JsonArray keyArray = Json.createArrayBuilder().build();


        for(TileType key : list){

            keyArray.add(Json.createObjectBuilder()
                .add("key: ", key.toString())
                .build());
        }

        System.out.println("Successfully converted inventory");
        return keyArray;

    }

    /*private static JsonArray convertTiles(List<Tile> list){

        JsonArray tileArray = Json.createArrayBuilder().build();
                tileArray.add(Json.createObjectBuilder()
                        .add("test","test")
                        .build());




        for(Tile tile : list){

                    //tileArray.add(Json.createObjectBuilder()
                    //.add("Type: ", "type") //tile.type.toString()
                    //.add("x: ", "x") //tile.getX()
                    //.add("y", "y") //tile.getY())
                    //.build());
        }

        System.out.println("Successfully converted Tiles");
        return tileArray;

    }*/



}

package nz.ac.vuw.ecs.swen225.a3.maze;



import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameCharacter extends Tile {

    //Chap's inventory (the 'keys' he has)
    private ArrayList<TileType> inventory = new ArrayList<TileType>();

    /**
     * Creates a new Chap.
     * @param x Chap's x-position
     * @param y Chap's y-position
     */
    public GameCharacter(TileType type, int x, int y) {
        super(type, x, y);
    }

    /**
     * Adds a key to Chap's inventory.
     * @param key The type of key to add
     */
    public void addKey(TileType key) {
        inventory.add(key);
        Collections.sort(inventory);
    }

    /**
     * Removes a key from Chap's inventory.
     * @param key The type of key to remove
     */
    public void removeKey(TileType key) {
        inventory.remove(inventory.indexOf(key));
        Collections.sort(inventory);
    }

    /**
     * Returns an unmodifiable version of Chap's current inventory.
     * @return Chap's inventory
     */
    public List<TileType> getAllKeys() {
        return Collections.unmodifiableList(inventory);
    }
}
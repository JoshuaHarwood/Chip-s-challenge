package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Hindley
 */
public class Chap extends GameCharacter {

	//Chap's inventory (the 'keys' he has)
    private ArrayList<TileType> inventory = new ArrayList<TileType>();
	
	public Chap(int x, int y) {
		super(TileType.Chap, x, y);
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

package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Hindley - 300438963
 */
public class Chap extends GameCharacter {

	/**
	 * Chap's inventory (i.e. the 'keys' he has).
	 */
    private ArrayList<TileType> inventory = new ArrayList<TileType>();
	
	/**
	 * Creates a new Chap object by calling the super constructor.
	 * @param x Chap's x-position on the board
	 * @param y Chap's y-position on the board
	 */
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

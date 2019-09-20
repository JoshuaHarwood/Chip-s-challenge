package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chap extends Tile{
	
	private ArrayList<TileType> inventory = new ArrayList<TileType>();
	
	public Chap(TileType t, int x, int y) {
		super(t, x, y);
	}

	public void addKey(TileType key) {
		inventory.add(key);
		Collections.sort(inventory);
	}
	
	public void removeKey(TileType key) {
		inventory.remove(inventory.indexOf(key));
		Collections.sort(inventory);
	}
	
	public List<TileType> getAllKeys() {
		return Collections.unmodifiableList(inventory);
	}
	
}

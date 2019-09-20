package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.List;

public class Tile {
	
	public final TileType type;
	private int x, y;
	
	/**
	 * Creates a new tile
	 * @param t	The type of tile
	 * @param x The x position of the tile
	 * @param y The y position of the tile
	 */
	public Tile(TileType t, int x, int y) {
		this.type = t;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	

	public boolean chapCanMoveHere(List<TileType> keys, boolean gotAllTreasure) {
		if(type == TileType.Empty || type == TileType.Treasure
				|| type == TileType.Info || type == TileType.Exit
				|| type == TileType.Key1 || type == TileType.Key2
				|| type == TileType.Key3 || type == TileType.Key4)
			return true;
		if(type == TileType.Door1 && keys.contains(TileType.Key1)) 
			return true;
		if(type == TileType.Door2 && keys.contains(TileType.Key2)) 
			return true;
		if(type == TileType.Door3 && keys.contains(TileType.Key3)) 
			return true;
		if(type == TileType.Door4 && keys.contains(TileType.Key4)) 
			return true;
		if(type == TileType.ExitLock && gotAllTreasure)
			return true;
		
		return false;		
	}
}

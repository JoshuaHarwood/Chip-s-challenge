package nz.ac.vuw.ecs.swen225.a3.maze;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Tile {

	public final TileType type;
	private int x, y;

	/**
	 * Creates a new tile.
	 * @param t	The type of tile
	 * @param x The x position of the tile
	 * @param y The y position of the tile
	 */
	public Tile(TileType t, int x, int y) {
		this.type = t;
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns this tile's x position.
	 * @return the x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns this tile's y position.
	 * @return the y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets this tile's x position.
	 * @return the x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets this tile's y position.
	 * @return the x position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Checks if Chap can move to this tile.
	 * @param keys The keys in Chap's inventory
	 * @param gotAllTreasure Whether Chap has collected all the treasure
	 * @return whether Chap can move to this tile or not
	 */
	public boolean chapCanMoveHere(List<TileType> keys, boolean gotAllTreasure) {
		//Chap can move to the tile
		if(type == TileType.Empty || type == TileType.Treasure
				|| type == TileType.Info || type == TileType.Exit
				|| type == TileType.Key1 || type == TileType.Key2
				|| type == TileType.Key3 || type == TileType.Key4 
				|| type == TileType.ExitUnlock)
			return true;
		//Chap can move to the tile if he has the right key
		if(type == TileType.Door1 && keys.contains(TileType.Key1)) 
			return true;
		if(type == TileType.Door2 && keys.contains(TileType.Key2)) 
			return true;
		if(type == TileType.Door3 && keys.contains(TileType.Key3)) 
			return true;
		if(type == TileType.Door4 && keys.contains(TileType.Key4)) 
			return true;
		//Chap can move to the tile if he has collected all the treasure
		if(type == TileType.ExitLock && gotAllTreasure)
			return true;

		return false;		
	}

	/**
	 * Gets the image associated with this tile.
	 * @return the image
	 */
	public Image getImage() {

		try {
			return ImageIO.read(getClass().getResource("/icons/" + type.name() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package nz.ac.vuw.ecs.swen225.a3.maze;

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
}

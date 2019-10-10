package nz.ac.vuw.ecs.swen225.a3.maze;

/**
 * the enemies class (these are the mobs that will move around and attack the player)
 * @author Joshua Harwood.
 */
public class Enemies extends GameCharacter {

	public Enemies(int x, int y) {
		super(TileType.Enemy, x, y);
	}
}

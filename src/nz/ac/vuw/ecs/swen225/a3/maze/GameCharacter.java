package nz.ac.vuw.ecs.swen225.a3.maze;

import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

/**
 * 
 * @author Joshua Harwood---300439084
 *
 */
public class GameCharacter extends Tile {

    /**
     * Creates a new Game Character.
     * @param type The type of tile this is (i.e. TileType.Chap or TileType.Enemy)
     * @param x Chap's x-position
     * @param y Chap's y-position
     */
    public GameCharacter(TileType type, int x, int y) {
        super(type, x, y);
    }

}

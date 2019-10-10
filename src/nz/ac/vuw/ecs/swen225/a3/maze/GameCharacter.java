package nz.ac.vuw.ecs.swen225.a3.maze;



import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameCharacter extends Tile {

    /**
     * Creates a new Game Character.
     * @param x Chap's x-position
     * @param y Chap's y-position
     */
    public GameCharacter(TileType type, int x, int y) {
        super(type, x, y);
    }

}

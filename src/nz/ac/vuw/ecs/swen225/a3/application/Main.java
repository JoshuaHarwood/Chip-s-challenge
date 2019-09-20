package nz.ac.vuw.ecs.swen225.a3.application;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.render.controller.MainFrameController;

/**
 * The Main class.
 */
public class Main {

    String map = "0202" +
            "EE" +
            "EE";


    public Main() {

        Maze maze = new Maze(map);

        MainFrameController mainFrameController = new MainFrameController(maze.getBoard());
        mainFrameController.showMainFrameWindow();

        mainFrameController.createBoard();
    }

    public static void main(String[] args) {
        Main game = new Main();
    }

}

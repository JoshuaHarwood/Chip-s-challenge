package tests;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.Trinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests for the main game.
 */
class GameTests {



    /**
     * Ensures the game board is generated correctly
     * TODO Change once input method for board changed to JSON
     */
    @Test
    void boardGenTest() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW"
        );

    }

    // ------------------------------------------ MOVEMENT TESTS ------------------------------------------------

    /**
     * Test a legal move up
     */
    @Test
    void moveCharUpLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        maze.moveChap("UP");


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW"
        );

    }

    /**
     * Test a legal move left
     */
    @Test
    void moveCharLeftLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        maze.moveChap("LEFT");


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEECEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );

    }


    /**
     * Test a legal move down
     */
    @Test
    void moveCharDownLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        maze.moveChap("DOWN");


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EECEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );

    }

    /**
     * Test a legal move right
     */
    @Test
    void moveCharTestLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        maze.moveChap("RIGHT");


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEECEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal move up
     */
    @Test
    void moveCharUpIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WCEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WCEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }


    /**
     * Test an illegal move down
     */
    @Test
    void moveCharDownIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5ECEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5ECEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal move left
     */
    @Test
    void moveCharLeftIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WCEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        Assertions.assertEquals(maze.moveChap("LEFT"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WCEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal move right
     */
    @Test
    void moveCharRightIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEECW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW");

        Assertions.assertEquals(maze.moveChap("RIGHT"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEECW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }
}

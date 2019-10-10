package nz.ac.vuw.ecs.swen225.a3.tests;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.maze.TileType;
import nz.ac.vuw.ecs.swen225.a3.maze.Trinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the main game.
 */
public class GameTests {



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
                "WWWWWWWWW",30);

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
        Assertions.assertEquals(maze.getTimeLeft(),30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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
                "WWWWWWWWW",30);

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

    // ------------------------------- TILE INTERACTIONS ------------------------------------------
    /**
     * Test a legal door1 unlock
     */
    @Test
    void unlockDoor1Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        maze.getChap().addKey(TileType.Key1);
        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.TRUE) ;


        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWWCWW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test a legal door2 unlock
     */
    @Test
    void unlockDoor2Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW2WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        maze.getChap().addKey(TileType.Key2);
        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.TRUE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWWCWW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test a legal door3 unlock
     */
    @Test
    void unlockDoor3Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW3WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        maze.getChap().addKey(TileType.Key3);
        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.TRUE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWWCWW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test a legal door4 unlock
     */
    @Test
    void unlockDoor4Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW4WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        maze.getChap().addKey(TileType.Key4);
        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.TRUE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWWCWW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal door1 unlock
     */
    @Test
    void unlockDoor1Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEECEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal door2 unlock
     */
    @Test
    void unlockDoor2Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW2WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEECEW" +
                        "WWWWWW2WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal door3 unlock
     */
    @Test
    void unlockDoor3Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW3WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEECEW" +
                        "WWWWWW3WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test an illegal door4 unlock
     */
    @Test
    void unlockDoor4Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW4WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("DOWN"), Trinary.FALSE) ;


        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEEEEW" +
                        "W5EEEECEW" +
                        "WWWWWW4WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test picking up key 1
     */
    @Test
    void pickupKey1() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE5EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
        Assertions.assertTrue(maze.getChap().getAllKeys().contains(TileType.Key1));
        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEECEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test picking up key 2
     */
    @Test
    void pickupKey2() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE6EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
        Assertions.assertTrue(maze.getChap().getAllKeys().contains(TileType.Key2));
        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEECEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test picking up key 3
     */
    @Test
    void pickupKey3() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE7EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
        Assertions.assertTrue(maze.getChap().getAllKeys().contains(TileType.Key3));
        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEECEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test picking up key 4
     */
    @Test
    void pickupKey4() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
        Assertions.assertTrue(maze.getChap().getAllKeys().contains(TileType.Key4));
        Assertions.assertEquals(maze.toString(),
                "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEEEEEEW" +
                        "WEEEEECEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

    /**
     * Test exiting with all treasure
     */
    @Test
    void exitLockWithTreasure() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
    }

    /**
     * Test exiting with treasure left
     */
    @Test
    void exitLockWithoutTreasure() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.FALSE) ;
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
     * Test exiting
     */
    @Test
    void exit() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWCWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.DONE) ;
    }


    /**
     * Test that the timer running out correctly ends the game
     */
    @Test
    void outOfTime() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWCWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEEEEEEW" +
                "WWWWWWWWW",0);
        maze.takeEnemyTurn();
        Assertions.assertFalse(maze.getMazeIsCurrent());
    }

    /**
     * Test exiting with treasure left
     */
    @Test
    void EnemyKill() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEYEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.FALSE) ;
        Assertions.assertEquals(maze.toString(),
                 "WWWWXWWWW" +
                        "WEEWLWEEW" +
                        "WEEECEEEW" +
                        "WEEEYEEEW" +
                        "W5EEEEEEW" +
                        "WWWWWW1WW" +
                        "WTEEEEEEW" +
                        "WWWWWWWWW"
        );
    }

}

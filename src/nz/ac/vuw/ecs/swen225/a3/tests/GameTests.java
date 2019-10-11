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
    public void boardGenTest() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
        Assertions.assertEquals(maze.getTimeLeft(),30,1);

    }

    // ------------------------------------------ MOVEMENT TESTS ------------------------------------------------

    /**
     * Test a legal move up
     */
    @Test
    public void moveCharUpLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharLeftLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharDownLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharRightLegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEECEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharUpIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WCEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharDownIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5ECEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharLeftIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WCEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void moveCharRightIlegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEECW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor1Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor2Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW2WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor3Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW3WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor4Legal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW4WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor1Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor2Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW2WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor3Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW3WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void unlockDoor4Illegal() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEEEEW" +
                "W5EEEECEW" +
                "WWWWWW4WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void pickupKey1() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE5EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void pickupKey2() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE6EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void pickupKey3() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE7EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void pickupKey4() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEECEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void exitLockWithTreasure() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEEEEEEW" +
                "WWWWWWWWW",30,1);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.TRUE) ;
    }

    /**
     * Test exiting with treasure left
     */
    @Test
    public void exitLockWithoutTreasure() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEEEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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
    public void exit() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWCWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEEEEEEW" +
                "WWWWWWWWW",30,1);

        Assertions.assertEquals(maze.moveChap("UP"), Trinary.DONE) ;
    }


    /**
     * Test that the timer running out correctly ends the game
     */
    @Test
    public void outOfTime() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWCWEEW" +
                "WEEEEEEEW" +
                "WEEEEE8EW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WEEYEEEEW" +
                "WWWWWWWWW" +
                "Y=LR",0,1);
        maze.takeEnemyTurn();
        Assertions.assertFalse(maze.getMazeIsCurrent());
    }

    /**
     * Test exiting with treasure left
     */
    @Test
    public void EnemyKill() {
        Maze maze = new Maze("0809" +
                "WWWWXWWWW" +
                "WEEWLWEEW" +
                "WEEECEEEW" +
                "WEEEYEEEW" +
                "W5EEEEEEW" +
                "WWWWWW1WW" +
                "WTEEEEEEW" +
                "WWWWWWWWW",30,1);

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

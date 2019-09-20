# SWEN225 Group Project

What we need by Monday:
The repository has commits by all team members. 
There are test cases to check game logic
There is a working stable playable prototype that allows game play 

The prototype may have one or many of the following limitations: 
*  the advanced components are missing
*  only level one is implemented
*  the game timeout functionality is missing


```
Requirements:
*   The Maze
        - responsible for maintaining game state
            - made up of the maze itself and the current location of current items
        - responsible for implementing the game logic
            - controls what events may or may not happen (e.g. can Chap go through this door)
        - has a timeout, with a restart option
 
*   Application
        - provides a GUI through which the player can see the maze and interact with it through keystrokes
            - keystrokes include: CTRL-X, CTRL-S, CTRL-R, CTRL-P, CTRL-1, SPACE, ESC, and ARROW KEYS
        - displays the time left to play, the current level and the number of treasures that are yet to be collected
        - has buttons and menu items to pause and exit the game, to save the game state and to resume a saved game, and to display a help page with the game rules
        - NOT responsible for drawing the maze
        - includes the main class
         
*   Rendering
        - responsible for providing a simple 2-dimensional view of the maze, updated after each more in order to display the current game play
        - the renderer needs to update the maze view faster for later levels because of the other entities
        - ensures that the movement of the game board looks smooth
        - for larger boards, only part of the board should be displayed
         
*   Persistence
        - saves the game state: the time left, position of Chap (and other actors), the treasures he holds, etc.
        - game state should be saved using JSON format
        - use testing to ensure the JSON format used is valid
     
*   Advanced: Record and Replay Games
        - records the gameplay and saves it as a JSON
        - allows for the recorded game to be loaded
        - user can change speed of the replay: step-by-step, auto-replay, set replay speed
        - should have JUnit tests that executes recorded games and checks the validity of the game state
     
*   Advanced: Levels as Plugins
        - levels are selft contained plugins
        - each level is defined by a file 'level-*.zip' in a 'levels/' folder
        - the level-*.zip files contain all resources and classes required with no dependency between classes

*   Quality Assurance
        - Code coverage of JUnit tests. Aim for >75%
        - SpotBugs. Ensure no bugs shown with SpotBugs
        - Javadoc. Javadoc violations will be measured using the Eclipse JavaDoc analysis
```

package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;

/**
 * the enemies class (these are the mobs that will move around and attack the player)
 * @author Joshua Harwood.
 */
public class Enemies extends GameCharacter {

	ArrayList<Character> moves;
	int currentMove = 0;
	
	public Enemies(ArrayList<Character> moves, int x, int y) {
		super(TileType.Enemy, x, y);
		this.moves = moves;
	}
	
	public Character getNextMove() {
		
		if(moves.size() == 0) { return null; } //there are no moves
		
		if(currentMove >= moves.size()) {
			currentMove = 0;
		}
		return moves.get(currentMove++);
	}
	
	
}

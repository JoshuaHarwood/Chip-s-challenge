package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;

/**
 * the enemies class (these are the mobs that will move around and attack the player)
 * @author Joshua Harwood----300439084.
 * 			with assistance from Joshua Hindley - 300438963
 */
public class Enemy extends GameCharacter {

	private ArrayList<Character> moves;
	private char currentMove = ' ';
	private Tile behindEnemy = null;
	
	public Enemy(int x, int y) {
		super(TileType.Enemy, x, y);
	}
	
	public Character getNextMove() {
		
		if(moves.size() == 0) { return ' '; } //there are no moves
		
		currentMove = moves.remove(0);
		moves.add(currentMove);
		return currentMove;
	}
	
	public void setTileBehindEnemy(Tile t) {
		behindEnemy = t;
	}
	
	public Tile getTileBehindEnemy() {
		return behindEnemy;
	}
	
	public void setMoves(ArrayList<Character> moves) {
		this.moves = new ArrayList<Character>();
		for(Character move: moves) {
			this.moves.add(move);
		}
	}
}

package nz.ac.vuw.ecs.swen225.a3.maze;

import java.util.ArrayList;

/**
 * the enemies class (these are the mobs that will move around and attack the player)
 * @author Joshua Harwood----300439084.
 * 			with additions from Joshua Hindley - 300438963
 */
public class Enemy extends GameCharacter {

	private ArrayList<Character> moves;
	private char currentMove = ' ';
	private Tile behindEnemy = null;
	
	/**
	 * Creates a new Enemy object by calling the super constructor.
	 * @param x The enemy's x-position on the board
	 * @param y The enemy's y-position on the board
	 */
	public Enemy(int x, int y) {
		super(TileType.Enemy, x, y);
	}
	
	/**
	 * Gets this enemy's next move as a character.
	 * @return The next move ('U', 'D', 'L', or 'R')
	 */
	public Character getNextMove() {	
		if(moves == null)
			return ' ';
		currentMove = moves.remove(0);
		moves.add(currentMove);
		return currentMove;
	}
	
	/**
	 * Sets the tile behind this enemy.
	 * @param t The tile to set
	 */
	public void setTileBehindEnemy(Tile t) {
		behindEnemy = t;
	}
	
	/**
	 * Gets the tile behind this enemy.
	 * @return The tile
	 */
	public Tile getTileBehindEnemy() {
		return behindEnemy;
	}
	
	/**
	 * Sets this enemy's moves. The moves should be characters ('L', 'R', 'U', or 'D') and
	 * performing all the moves in sequence should bring the enemy back to its starting location.
	 * @param moves An arrayList of the moves that will be performed by this enemy
	 */
	public void setMoves(ArrayList<Character> moves) {
		this.moves = new ArrayList<Character>();
		for(Character move: moves) {
			this.moves.add(move);
		}
	}

	public ArrayList<Character> getMoves(){
		return moves;
	}
}
package nz.ac.vuw.ecs.swen225.a3.maze;

/**
 * An enum that holds a tile type.
 * @author Joshua Hindley - 300438963
 */
public enum TileType {
	/**
	 * An empty value indicating a cell with no content.
	 */
	Empty 		{public String toString() {return "E";}},
	/**
	 * Chap, our player-controlled protagonist.
	 */
	Chap 		{public String toString() {return "C";}},
	/**
	 * An enemy, kills Chap if it comes into contact.
	 */
	Enemy 		{public String toString() {return "Y";}},
	/**
	 * A wall. Blocks Chap from passing.
	 */
	Wall 		{public String toString() {return "W";}},
	/**
	 * A locked door. Can only be unlocked by Key1.
	 */
	Door1 		{public String toString() {return "1";}},
	/**
	 * A locked door. Can only be unlocked by Key2.
	 */
	Door2 		{public String toString() {return "2";}},
	/**
	 * A locked door. Can only be unlocked by Key3.
	 */
	Door3 		{public String toString() {return "3";}},
	/**
	 * A locked door. Can only be unlocked by Key4.
	 */
	Door4 		{public String toString() {return "4";}},
	/**
	 * A key. Can only be used to unlock Door1.
	 */
	Key1 		{public String toString() {return "5";}},
	/**
	 * A key. Can only be used to unlock Door2.
	 */
	Key2 		{public String toString() {return "6";}},
	/**
	 * A key. Can only be used to unlock Door3.
	 */
	Key3 		{public String toString() {return "7";}},
	/**
	 * A key. Can only be used to unlock Door4.
	 */
	Key4 		{public String toString() {return "8";}},
	/**
	 * Treasure. Chap must collect all the treasure to complete the level.
	 */
	Treasure	{public String toString() {return "T";}},
	/**
	 * The exit. The level will end when Chap moves to this cell.
	 */
	Exit 		{public String toString() {return "X";}},
	/**
	 * The exit lock. This prevents Chap from reaching the exit until all the treasure is collected.
	 */
	ExitLock 	{public String toString() {return "L";}},
	/**
	 * The exit lock, unlocked. When Chap collects all of the treasure, the exit lock becomes unlocked.
	 */
	ExitUnlock 	{public String toString() {return "U";}},
	/**
	 * The info cell. If Chap stands on this cell a pop-up will tell him how to play.
	 */
	Info 		{public String toString() {return "I";}}
}

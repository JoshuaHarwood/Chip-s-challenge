package nz.ac.vuw.ecs.swen225.a3.maze;

/**
 * An enum that holds a tile type.
 * @author Joshua Hindley - 300438963
 */
public enum TileType {
	Empty 		{public String toString() {return "E";}},
	Chap 		{public String toString() {return "C";}},
	Enemy 		{public String toString() {return "Y";}},
	Wall 		{public String toString() {return "W";}},
	Door1 		{public String toString() {return "1";}},
	Door2 		{public String toString() {return "2";}},
	Door3 		{public String toString() {return "3";}},
	Door4 		{public String toString() {return "4";}},
	Key1 		{public String toString() {return "5";}},
	Key2 		{public String toString() {return "6";}},
	Key3 		{public String toString() {return "7";}},
	Key4 		{public String toString() {return "8";}},
	Treasure	{public String toString() {return "T";}},
	Exit 		{public String toString() {return "X";}},
	ExitLock 	{public String toString() {return "L";}},
	ExitUnlock 	{public String toString() {return "U";}},
	Info 		{public String toString() {return "I";}}
}

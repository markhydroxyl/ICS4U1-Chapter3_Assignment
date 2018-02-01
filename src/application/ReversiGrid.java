package application;

public abstract class ReversiGrid {
	
	//This variable stores all tiles on a grid and returns if they are occupied with a piece or not 
	public static boolean tileOccupied[][] = new boolean[8][8];
	
	
	public int gridSpace = 0;
	// grid space returns your space on the grid
		// 1 2 3 4 5 6 7 8
		// 9 10 11 12 13 14 15 16
		// 17 18 19 20 21 22 23 24
		// 25 26 27 28 29 30 31 32
		// 33 34 35 36 37 38 39 40
		// 41 42 43 44 45 46 47 48
		// 49 50 51 52 53 54 55 56
		// 57 58 59 60 61 62 63 64

	//Holds a turn count for the game. Used to determine whose turn it is
	public static int turnCount = 0;
	
	public abstract byte getState();

	
	//abstract method to draw the board
	public abstract void newGame();


}
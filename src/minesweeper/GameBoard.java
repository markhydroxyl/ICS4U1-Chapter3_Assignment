package minesweeper;

import minesweeper.gameInteraction.*;
import minesweeper.gameInteraction.scoreMethods.*;
import minesweeper.inputHandlers.*;
import minesweeper.tiles.*;

public class GameBoard {
	private int boardWidth;
	private int boardHeight;
	private int mineCount;
	private InputHandler inHandler;
	private Display display;
	private Tile[][] tileArray;
	private Timer time;
	private Score score;
	
	public GameBoard(int aWidth, int aHeight, int aMineCount) {
		//Create a gameboard of width aWidth, height aHeight, and aMineCount number of mines
	}
	
	public GameBoard(int aWidth, int aHeight, double aMineDensity) {
		//Create a gameboard of width aWidth, height aHeight, and mine density aMineDensity
		//aMineDensity cannot be greater than 1!
	}
	
	public void display() {
		this.display.display();
	}
	
	public void generate() {
		//Generate a grid of Tiles
	}
}

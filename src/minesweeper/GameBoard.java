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
		this.boardWidth = aWidth;
		this.boardHeight = aHeight;
		this.mineCount = aMineCount;
	}
	
	public GameBoard(int aWidth, int aHeight, double aMineDensity) {
		//Create a gameboard of width aWidth, height aHeight, and mine density aMineDensity
		//aMineDensity cannot be greater than 1!
		this.boardHeight = aHeight;
		this.boardWidth = aWidth;
		this.mineCount = (int) aMineDensity*aWidth*aHeight;
	}
	
	public void display() {
		this.display.display(tileArray);
	}
	
	public void generate() {
		//Generate a grid of Tiles
		tileArray = new Tile[boardWidth][boardHeight];
		boolean mineSet = false;
		for (int i=0; i<Math.min(mineCount, boardWidth*boardHeight); i++) {
			while(!mineSet) {
				int nextX = (int)(Math.random()*boardWidth);
				int nextY = (int)(Math.random()*boardHeight);
				if (tileArray[nextX][nextY].getClass() != MineTile.class)
					tileArray[nextX][nextY] = new MineTile(new int[]{nextX, nextY});
			}
			mineSet = false;
		}
		
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				int count = 0;
				int nextX=0, nextY=0;
				for (int x=-1; x<=1; x++) {
					for (int y=-1; y<=1; y++) {
						nextX = boardWidth+x;
						nextY = boardWidth+y;
						if ((x!=0&&y!=0)&&tileArray[nextX][nextY].getClass() == MineTile.class) {
							count++;
						}
					}
				}
				if (count != 0) {
					tileArray[i][j] = new NumberTile(new int[]{i, j}, count);
				} else {
					tileArray[i][j] = new BlankTile(new int[]{i, j});
				}
			}
		}
	}
}

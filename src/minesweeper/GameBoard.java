package minesweeper;

import java.util.ArrayList;

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
		this.score = new ClassicalScore();
	}
	
	public GameBoard(int aWidth, int aHeight, double aMineDensity) {
		//Create a gameboard of width aWidth, height aHeight, and mine density aMineDensity
		//aMineDensity cannot be greater than 1!
		this.boardHeight = aHeight;
		this.boardWidth = aWidth;
		this.mineCount = (int) aMineDensity*aWidth*aHeight;
		this.score = new ClassicalScore();
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
				if (tileArray[nextX][nextY] instanceof MineTile)
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
						if ((x!=0&&y!=0)&&tileArray[nextX][nextY] instanceof MineTile) {
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
	
	public void onFlag(int[] coor) {
		Tile toFlag = tileArray[coor[0]][coor[1]];
		if(!toFlag.isRevealed()) {
			toFlag.setFlag();
			score.modifyScore(toFlag.isFlagged()?-2:-3);
		}
	}
	
	public boolean[] onReveal(int[] coor) {
		Tile toReveal = tileArray[coor[0]][coor[1]];
		if(!toReveal.isFlagged()&&!toReveal.isRevealed()) {
			int result = toReveal.onReveal();
			if (result == -1) {
				score.modifyScore(-1);
				revealAll();
				return new boolean[] {true, false};
			} else if (result == 0) {
				score.modifyScore(0);
				return checkWin();
			} else if (result == 1) {
				int counter = 1;
				ArrayList<Tile> toBeChecked = new ArrayList<Tile>();
				toBeChecked.add(toReveal);
				while(!toBeChecked.isEmpty()) {
					Tile checkingTile = toBeChecked.remove(0);
					for(int i=-1; i<=1; i++) {
						for(int j=-1; j<=1; j++) {
							if (!(i==0&&j==0)) {
								int nextX = checkingTile.getCoor()[0]+i;
								int nextY = checkingTile.getCoor()[1]+j;
								if (nextX>=0&&nextX<boardWidth&&nextY>=0&&nextY<boardHeight) {
									Tile nextTile = tileArray[nextX][nextY];
									if (nextTile instanceof NumberTile) {
										nextTile.setReveal();
										counter++;
									} else if (nextTile instanceof BlankTile) {
										nextTile.setReveal();
										toBeChecked.add(nextTile);
									}
								}
							}
						}
					}
					
					score.modifyScore(counter);
				}
				
				return checkWin();
			}
		}
		return new boolean[] {false, false};
	}
	
	public void revealAll() {
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (!tileArray[i][j].isRevealed()&&!tileArray[i][j].isFlagged()) {
					tileArray[i][j].setReveal();
				} else if (tileArray[i][j].isFlagged()&&tileArray[i][j] instanceof MineTile) {
					//Logic for showing that it was incorrectly flagged
				}
			}
		}
	}
	
	public boolean[] checkWin() {
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (tileArray[i][j] instanceof MineTile && !tileArray[i][j].isRevealed()) {
					return new boolean[] {false, false};
				}
			}
		}
		
		return new boolean[] {true, true};
	}
}

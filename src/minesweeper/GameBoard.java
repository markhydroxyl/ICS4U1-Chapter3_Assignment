package minesweeper;

import java.util.ArrayList;

import minesweeper.gameInteraction.*;
import minesweeper.gameInteraction.scoreMethods.*;
import minesweeper.inputHandlers.*;
import minesweeper.tiles.*;
import util.BoardPosition;

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
		this.mineCount = Math.min(aMineCount, aWidth*aHeight);
		this.score = new ClassicalScore();
	}
	
	public GameBoard(int aWidth, int aHeight, double aMineDensity) {
		//Create a gameboard of width aWidth, height aHeight, and mine density aMineDensity
		//aMineDensity cannot be greater than 1!
		this.boardHeight = aHeight;
		this.boardWidth = aWidth;
		this.mineCount = Math.min(aWidth*aHeight, (int) aMineDensity*aWidth*aHeight);
		this.score = new ClassicalScore();
	}
	
	public void display() {
		this.display.display(tileArray);
	}
	
	private void addMines() {
		boolean mineSet = false;
		for (int i=0; i<mineCount; i++) {
			while(!mineSet) {
				int nextX = (int)(Math.random()*boardWidth);
				int nextY = (int)(Math.random()*boardHeight);
				if (!(getTile(nextX, nextY) instanceof MineTile)) {
					setTile(nextX, nextY, new MineTile());
					mineSet = true;
				}
			}
			mineSet = false;
		}
	}
	
	public void generate() {
		//Generate a grid of Tiles
		tileArray = new Tile[boardWidth][boardHeight];
		addMines();
		
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				int count = countAdjMines(new BoardPosition(i, j));
				if (count != 0) {
					setTile(i, j, new NumberTile(count));
				} else {
					setTile(i, j, new BlankTile());
				}
			}
		}
	}
	
	private int countAdjMines(BoardPosition coor) {
		int count = 0;
		int nextX=0, nextY=0;
		for (int x=-1; x<=1; x++) {
			for (int y=-1; y<=1; y++) {
				nextX = coor.getX()+x;
				nextY = coor.getY()+y;
				if ((x!=0&&y!=0)&&getTile(nextX, nextY) instanceof MineTile) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	public void onFlag(BoardPosition coor) {
		Tile toFlag = getTile(coor);
		if(!toFlag.isRevealed()) {
			toFlag.setFlag();
			score.modifyScore(toFlag.isFlagged()?-2:-3);
		}
	}
	
	public boolean[] onReveal(BoardPosition coor) {
		Tile toReveal = getTile(coor);
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
				score.modifyScore(countAndRevealAdjBlanks(coor));
				return checkWin();
			}
		}
		return new boolean[] {false, false};
	}
	
	private int countAndRevealAdjBlanks(BoardPosition coor) {
		int counter = 1;
		ArrayList<BoardPosition> posToBeChecked = new ArrayList<BoardPosition>();
		posToBeChecked.add(coor);
		while(!posToBeChecked.isEmpty()) {
			BoardPosition checkingCoor = posToBeChecked.remove(0);
			for(int x=-1; x<=1; x++) {
				for(int y=-1; y<=1; y++) {
					if(!(x==0&&y==0)) {
						int nextX = checkingCoor.getX()+x;
						int nextY = checkingCoor.getY()+y;
						if (nextX>=0&&nextX<boardWidth&&nextY>=0&&nextY<boardHeight) {
							Tile nextTile=getTile(nextX, nextY);
							if (!nextTile.isRevealed()) {
								counter++;
								nextTile.setReveal();
								if (nextTile instanceof BlankTile) {
									posToBeChecked.add(new BoardPosition(nextX, nextY));
								}
							}
						}
					}
				}
			}
		}
		return counter;
	}
	
	private void revealAll() {
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (!getTile(i, j).isRevealed()&&!getTile(i, j).isFlagged()) {
					getTile(i, j).setReveal();
				} else if (getTile(i, j).isFlagged()&&getTile(i, j) instanceof MineTile) {
					//Logic for showing that it was incorrectly flagged
				}
			}
		}
	}
	
	public boolean[] checkWin() {
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (getTile(i, j) instanceof MineTile && !getTile(i, j).isRevealed()) {
					return new boolean[] {false, false};
				}
			}
		}
		
		return new boolean[] {true, true};
	}
	
	private Tile getTile(int x, int y) {
		return getTile(new BoardPosition(x, y));
	}
	
	private Tile getTile(BoardPosition coor) {
		return tileArray[coor.getX()][coor.getY()];
	}
	
	private void setTile(BoardPosition coor, Tile aTile) {
		tileArray[coor.getX()][coor.getY()] = aTile;
	}
	
	private void setTile(int x, int y, Tile aTile) {
		setTile(new BoardPosition(x, y), aTile);
	}
}

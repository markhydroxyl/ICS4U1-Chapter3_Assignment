package minesweeper;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.Pane;
import minesweeper.gameInteraction.*;
import minesweeper.gameInteraction.scoreMethods.*;
import minesweeper.inputHandlers.*;
import minesweeper.tiles.*;
import util.BoardPosition;

public class GameBoard {
	private int boardWidth;
	private int boardHeight;
	private int mineCount;
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
		this.display = new Display();
		this.tileArray = new Tile[boardWidth][boardHeight];
		this.time = new Timer();
//		System.out.println("boardWidth: "+boardWidth+"\nboardHeight: "+boardHeight+"\nmineCount: "+mineCount+"\ntileArray.length: "+tileArray.length+"\ntileArray[0].length: "+tileArray[0].length);
	}
	
	public GameBoard(int aWidth, int aHeight, double aMineDensity) {
		//Create a gameboard of width aWidth, height aHeight, and mine density aMineDensity
		//aMineDensity cannot be greater than 1!
		this.boardHeight = aHeight;
		this.boardWidth = aWidth;
		this.mineCount = Math.min(aWidth*aHeight, (int) (aMineDensity*aWidth*aHeight));
		System.out.println("Mine count: "+mineCount);
		this.score = new ClassicalScore();
		this.display = new Display();
		this.time = new Timer();
		this.tileArray = new Tile[boardWidth][boardHeight];
	}
	
	public void render(Pane aRoot) {
		//Render the window
		this.display.display(tileArray, aRoot);
	}
	
	public void displayOver(Pane aRoot, boolean aWin) {
		//Render game over text
		this.display.onGameOver(aRoot, aWin);
	}
	
	//Unused time counter
	public void timeIncrease() {
		time.incrementTime();
	}

	public int getTime() {
		return time.getTime();
	}
	
	//Generate the mines by randomly selecting x and y co-ordinates, checking for the presence of mines, and replacing as necessary 
	private void addMines() {
		boolean mineSet = false;
		for (int i=0; i<mineCount; i++) {
			Random seedGenerator = new Random();
			while(!mineSet) {
				int nextX = seedGenerator.nextInt(10);
				int nextY = seedGenerator.nextInt(10);
				if (!(getTile(nextX, nextY) instanceof MineTile)) {
//					System.out.println("Setting a mine at "+ nextX+" "+nextY);
					setTile(nextX, nextY, new MineTile());
					mineSet = true;
				}
			}
			mineSet = false;
		}
	}
	
	//Creates an array of tiles of the approrpriate type
	public void generate() {
		//Generate a grid of Tiles
		addMines();
		//display();
		
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (!(getTile(i, j) instanceof MineTile)) {
					int count = countAdjMines(new BoardPosition(i, j));
					if (count != 0) {
						setTile(i, j, new NumberTile(count));
					} else {
						setTile(i, j, new BlankTile());
					}
				}
				
			}
		}
	}
	
	//Count the adjacent mines for number tiles
	private int countAdjMines(BoardPosition coor) {
		int count = 0;
		int nextX=0, nextY=0;
		for (int x=-1; x<=1; x++) {
			for (int y=-1; y<=1; y++) {
				nextX = coor.getX()+x;
				nextY = coor.getY()+y;
				if ((nextX>=0&&nextX<boardWidth&&nextY>=0&&nextY<boardHeight)&&!(x==0&&y==0)&&getTile(nextX, nextY) instanceof MineTile) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	//On right-click
	public void onFlag(BoardPosition coor) {
		Tile toFlag = getTile(coor);
		if(!toFlag.isRevealed()) {
			toFlag.setFlag();
			score.modifyScore(toFlag.isFlagged()?-2:-3);
		}
	}
	
	//On left-click
	public boolean[] onReveal(BoardPosition coor) {
		Tile toReveal = getTile(coor);
		if(!toReveal.isFlagged()&&!toReveal.isRevealed()) {
			int result = toReveal.onReveal();
			if (result == -1) {
				score.modifyScore(-1);
				System.out.println("Hit mine");
			} else if (result == 0) {
				score.modifyScore(0);
				System.out.println("Hit number");
			} else if (result == 1) {
				int count = countAndRevealAdjBlanks(coor);
				score.modifyScore(count);
				System.out.println("Hit blank, revealed "+count);
			}
		}
		return checkWin();
	}
	
	//When a blank tile is clicked, search all adjacent tiles and reveal as appropriate
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
								nextTile.setRevealTrue();
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
	
	//On game over, reveal all the tiles
	private void revealAll() {
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				getTile(i, j).setRevealTrue();
			}
		}
	}
	
	//Check for game over and win conditions
	public boolean[] checkWin() {
		boolean gameOver = false;
		boolean gameWon = true;
		for (int i=0; i<boardWidth; i++) {
			for (int j=0; j<boardHeight; j++) {
				if (getTile(i, j) instanceof MineTile && getTile(i, j).isRevealed()) {
					revealAll();
					gameOver = true;
					gameWon = false;
					break;
				}
				if (!(getTile(i, j) instanceof MineTile) && !getTile(i, j).isRevealed()) {
					gameWon = false;
				}
			}
			if (gameOver)
				break;
		}
		return new boolean[] {gameOver, gameWon};
	}
	
	public Tile[][] getTileArray() {
		return tileArray;
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

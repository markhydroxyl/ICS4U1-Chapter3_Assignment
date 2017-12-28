package minesweeper;

import util.Game;
import util.MinesweeperConstants;

public class MinesweeperGame extends Game {
	private boolean gameWin;
	private boolean gameOver;
	public GameBoard gameBoard;
	
	public MinesweeperGame() {}
	
	public void newGame() {
		gameBoard = new GameBoard(MinesweeperConstants.DEFAULT_NUM_ROWS, MinesweeperConstants.DEFAULT_NUM_COLS, MinesweeperConstants.DEFAULT_MINE_DENSITY);
	}
	
	public void newGame(int aWidth, int aHeight, int aMineCount) {
		gameBoard = new GameBoard(aWidth, aHeight, aMineCount);
		gameBoard.generate();
	}
	
	public void newGame(int aWidth, int aHeight, double aMineDensity) {
		gameBoard = new GameBoard(aWidth, aHeight, aMineDensity);
		gameBoard.generate();
	}
	
	public byte getState() {
		boolean[] a = gameBoard.checkWin();
		gameOver = a[0];
		gameWin = a[1];
		if (gameWin) {
			return 1;
		} else if (gameOver&&!gameWin) {
			return -1;
		}
		return 0;
	}
}

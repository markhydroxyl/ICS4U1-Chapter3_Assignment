package minesweeper;

public class MinesweeperGame {
	private boolean gameWin;
	private boolean gameOver;
	public GameBoard gameBoard;
	
	public MinesweeperGame() {}
	
	public void newGame(int aWidth, int aHeight, int aMineCount) {
		gameBoard = new GameBoard(aWidth, aHeight, aMineCount);
		gameBoard.generate();
	}
	
	public void newGame(int aWidth, int aHeight, double aMineDensity) {
		gameBoard = new GameBoard(aWidth, aHeight, aMineDensity);
		gameBoard.generate();
	}
	
	public String getState() {
		boolean[] a = gameBoard.checkWin();
		gameOver = a[0];
		gameWin = a[1];
		if (gameWin) {
			return "win";
		} else if (gameOver&&!gameWin) {
			return "lose";
		}
		return null;
	}
}

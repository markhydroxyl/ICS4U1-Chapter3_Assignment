package minesweeper;

public class MinesweeperGame {
	private boolean gameWin;
	private boolean gameOver;
	private GameBoard gameBoard;
	
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
		if (gameWin) {
			return "win";
		} else if (gameOver&&!gameWin) {
			return "lose";
		}
		return null;
	}
}

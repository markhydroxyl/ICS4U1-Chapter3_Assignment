package minesweeper.gameInteraction;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import minesweeper.tiles.*;
import util.MinesweeperConstants;

public class Display {
	private boolean alreadyDisplayed = false;
	private Canvas tileCanvas = new Canvas(MinesweeperConstants.EASY_NUM_ROWS*MinesweeperConstants.TILE_WIDTH, MinesweeperConstants.EASY_NUM_COLS*MinesweeperConstants.TILE_HEIGHT);
	
	public Display() {}
	
	//Display method for typical rendering
	public void display(Tile[][] aArray, Pane aRoot) {
		//Show display on screen
		tileCanvas.setTranslateX(MinesweeperConstants.X_OFFSET);
		tileCanvas.setTranslateY(MinesweeperConstants.Y_OFFSET);
		for (int row=0; row<aArray.length; row++) {
			for (int col=0; col<aArray[0].length; col++) {
				tileCanvas = aArray[row][col].displayTile(tileCanvas, row, col);
			}
		}
		
		if (!alreadyDisplayed) {
			aRoot.getChildren().add(tileCanvas);
			alreadyDisplayed = true;
			drawLines(aRoot, aArray.length, aArray[0].length);
		}
		
		//Print game to console for reference
//		printToConsole(aArray);
	}
	
	//Display method for game over text
	public void onGameOver(Pane aRoot, boolean win) {
		Canvas gameOverText = new Canvas(50, 50);
		gameOverText.setTranslateX(20);
		gameOverText.setTranslateY(20);
		GraphicsContext textGC = gameOverText.getGraphicsContext2D();
		textGC.setStroke(Color.BLACK);
		textGC.setLineWidth(0.5);
		if(win) {
			textGC.strokeText("You win!", 0, 10);
		} else {
			textGC.strokeText("You lost", 0, 10);
		}
		aRoot.getChildren().add(gameOverText);
	}
	
	//Draw the grid lines
	private void drawLines(Pane aRoot, int numRows, int numCols) {
		
		for (int i=0; i<=numCols; i++) {
			Canvas vertCanvas = new Canvas(2, numCols*MinesweeperConstants.TILE_HEIGHT);
			GraphicsContext vertGC = vertCanvas.getGraphicsContext2D();
			vertGC.setLineWidth(2);
			vertGC.strokeLine(0, 0, 0, numCols*MinesweeperConstants.TILE_HEIGHT);
			vertCanvas.setTranslateX(i*MinesweeperConstants.TILE_WIDTH+MinesweeperConstants.X_OFFSET);
			vertCanvas.setTranslateY(MinesweeperConstants.Y_OFFSET);
			aRoot.getChildren().add(vertCanvas);
		}
		
		for (int i=0; i<=numRows; i++) {
			Canvas horCanvas = new Canvas(numRows*MinesweeperConstants.TILE_WIDTH, 2);
			GraphicsContext horGC = horCanvas.getGraphicsContext2D();
			horGC.setLineWidth(2);
			horGC.strokeLine(0, 0, numRows*MinesweeperConstants.TILE_WIDTH, 0);
			horCanvas.setTranslateX(MinesweeperConstants.X_OFFSET);
			horCanvas.setTranslateY(i*MinesweeperConstants.TILE_WIDTH+MinesweeperConstants.Y_OFFSET);
			aRoot.getChildren().add(horCanvas);
		}
	}
	
	//Unused console print statement
	private void printToConsole(Tile[][] aArray) {
		System.out.println();
		
		System.out.print("  ");
		for (int i=0; i<aArray[0].length; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for (int i=0; i<aArray.length; i++) {
			for (int j=-1; j<aArray[0].length; j++) {
				if (j==-1) {
					System.out.print(i+" ");
				} else {
					if (aArray[i][j]==null) {
						System.out.print("? ");
					} else {
						if(aArray[i][j].isFlagged()) {
							System.out.print("F ");
						} else if (!aArray[i][j].isRevealed()) {
							System.out.print("x ");
						} else if (aArray[i][j] instanceof MineTile) {
							System.out.print("M ");
						} else if (aArray[i][j] instanceof BlankTile) {
							System.out.print("  ");
						} else if (aArray[i][j] instanceof NumberTile) {
							int numMines=((NumberTile) aArray[i][j]).getNumAdjMines();
							System.out.print(numMines + " ");
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import util.MinesweeperConstants;

public class NumberTile extends Tile {
	private int numAdjMines;
	
	public NumberTile() {
		this.numAdjMines = 0;
	}
	
	public NumberTile(int aNum){
		this.numAdjMines = aNum;
	}

	@Override
	public int onReveal() {
		//Reveal yourself
		// Tell game to modify score appropriately
		this.revealed = true;
		return 0;
	}

	@Override
	public void onHighlight() {
		//Highlight the tile
	}
	
	public int getNumAdjMines() {
		return numAdjMines;
	}
	
	@Override
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot) {
		super.displayTile(aCanvas, aRow, aCol, aRoot);
		GraphicsContext tileGC = aCanvas.getGraphicsContext2D();
		tileGC.setTextAlign(TextAlignment.CENTER);
		tileGC.setLineWidth(0.5);
		if (isRevealed()) {
			tileGC.setFill(Color.LIGHTGREY);
			tileGC.setStroke(Color.BLACK);
		} else if (isFlagged()) {
			tileGC.setFill(Color.INDIANRED);
			tileGC.setStroke(Color.INDIANRED);
		} else {
			tileGC.setFill(Color.GREY);
			tileGC.setStroke(Color.GREY);
		}
		tileGC.fillRect(0, 0, tileWidth, tileHeight);
		tileGC.strokeText(""+numAdjMines, tileWidth/2, tileHeight/2+tileGC.getFont().getSize()/2);
		aCanvas.setTranslateX(aRow*tileWidth+MinesweeperConstants.X_OFFSET);
		aCanvas.setTranslateY(aCol*tileHeight+MinesweeperConstants.Y_OFFSET);
		aRoot.getChildren().add(aCanvas);
	}
}

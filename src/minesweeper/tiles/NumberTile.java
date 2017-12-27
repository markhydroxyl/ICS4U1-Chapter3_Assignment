package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot, int xOffset, int yOffset) {
		super.displayTile(aCanvas, aRow, aCol, aRoot, xOffset, yOffset);
		GraphicsContext tileGC = aCanvas.getGraphicsContext2D();
		if (isRevealed()) {
			tileGC.setFill(Color.LIGHTGREY);
		} else if (isFlagged()) {
			tileGC.setFill(Color.INDIANRED);
		} else {
			tileGC.setFill(Color.GREY);
		}
		tileGC.fillRect(0, 0, tileWidth, tileHeight);
		tileGC.fillText(""+numAdjMines, 0, 0);
		aCanvas.setTranslateX(aRow*tileWidth+xOffset);
		aCanvas.setTranslateY(aCol*tileHeight+yOffset);
		aRoot.getChildren().add(aCanvas);
	}
}

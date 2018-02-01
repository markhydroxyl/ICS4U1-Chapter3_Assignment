package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

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
	public Canvas displayTile(Canvas aCanvas, int aRow, int aCol) {
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
		tileGC.fillRect(aRow*tileWidth, aCol*tileHeight, tileWidth, tileHeight);
		tileGC.strokeText(""+numAdjMines, aRow*tileWidth+tileWidth/2, aCol*tileHeight+tileHeight/2+tileGC.getFont().getSize()/2);
		return aCanvas;
	}
}

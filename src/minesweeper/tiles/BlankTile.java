package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlankTile extends Tile {
	
	public BlankTile() {
	}

	@Override
	public int onReveal() {
		//Search for all other adjacent blank tiles and reveal
		// Tell game to modify score appropriately
		this.setRevealTrue();
		return 1;
	}
	
	@Override
	public void onHighlight() {
		//Highlight the tile
	}
	
	public Canvas displayTile(Canvas aCanvas, int aRow, int aCol) {
		GraphicsContext tileGC = aCanvas.getGraphicsContext2D();
		if (isRevealed()) {
			tileGC.setFill(Color.LIGHTGREY);
		} else if (isFlagged()) {
			tileGC.setFill(Color.INDIANRED);
		} else {
			tileGC.setFill(Color.GREY);
		}
		tileGC.fillRect(aRow*tileWidth, aCol*tileHeight, tileWidth, tileHeight);
		return aCanvas;
	}
}

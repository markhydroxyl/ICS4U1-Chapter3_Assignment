package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.MinesweeperConstants;

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
	
	@Override
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot) {
		super.displayTile(aCanvas, aRow, aCol, aRoot);
		GraphicsContext tileGC = aCanvas.getGraphicsContext2D();
		if (isRevealed()) {
			tileGC.setFill(Color.LIGHTGREY);
		} else if (isFlagged()) {
			tileGC.setFill(Color.INDIANRED);
		} else {
			tileGC.setFill(Color.GREY);
		}
		tileGC.fillRect(0, 0, tileWidth, tileHeight);
		aCanvas.setTranslateX(aRow*tileWidth+MinesweeperConstants.X_OFFSET);
		aCanvas.setTranslateY(aCol*tileHeight+MinesweeperConstants.Y_OFFSET);
		aRoot.getChildren().add(aCanvas);
	}
}

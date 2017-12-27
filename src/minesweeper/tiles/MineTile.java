package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MineTile extends Tile {
	public MineTile() {
	}

	@Override
	public int onReveal() {
		// Tell MinesweeperGame that the game has been lost, reveal all tiles to player
		// Tell game to modify score appropriately
		this.setRevealTrue();
		return -1;
	}
	
	@Override
	public void onHighlight() {
		//Highlight the tile
	}

	@Override
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot, int xOffset, int yOffset) {
		super.displayTile(aCanvas, aRow, aCol, aRoot, xOffset, yOffset);
		GraphicsContext tileGC = aCanvas.getGraphicsContext2D();
		if (isRevealed()) {
			tileGC.setFill(Color.BLACK);
		} else if (isFlagged()) {
			tileGC.setFill(Color.INDIANRED);
		} else {
			tileGC.setFill(Color.GREY);
		}
		tileGC.fillRect(0, 0, tileWidth, tileHeight);
		aCanvas.setTranslateX(aRow*tileWidth+xOffset);
		aCanvas.setTranslateY(aCol*tileHeight+yOffset);
		aRoot.getChildren().add(aCanvas);
	}
}

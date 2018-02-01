package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import util.MinesweeperConstants;

public abstract class Tile {
	protected boolean revealed;
	protected boolean flagged;
	protected final int tileWidth = MinesweeperConstants.TILE_WIDTH;
	protected final int tileHeight = MinesweeperConstants.TILE_HEIGHT;
	
	public Tile() {
		this.revealed = false;
		this.flagged = false;
	}
	
	public abstract int onReveal();
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setRevealTrue() {
		revealed = true;
	}
	
	public void setFlag() {
		flagged = !flagged;
	}
	
	public int getWidth() {
		return tileWidth;
	}
	
	public int getHeight() {
		return tileHeight;
	}
	
	public abstract void onHighlight();
	
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot) {
		aCanvas.setWidth(tileWidth);
		aCanvas.setHeight(tileHeight);
	}
}

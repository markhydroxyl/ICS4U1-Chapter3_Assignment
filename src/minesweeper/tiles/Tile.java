package minesweeper.tiles;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public abstract class Tile {
	protected boolean revealed;
	protected boolean flagged;
	protected final int tileWidth = 40;
	protected final int tileHeight = 40;
	
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
	
	public void displayTile(Canvas aCanvas, int aRow, int aCol, Pane aRoot, int xOffset, int yOffset) {
		aCanvas.setWidth(tileWidth);
		aCanvas.setHeight(tileHeight);
	}
}

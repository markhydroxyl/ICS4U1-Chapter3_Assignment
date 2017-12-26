package minesweeper.tiles;

import util.Coordinates;

public abstract class Tile {
	//protected int[] coor;
	protected Coordinates coor;
	protected boolean revealed;
	protected boolean flagged;
	
	public Tile(Coordinates aCoor) {
		this.coor = aCoor;
	}
	
	public abstract int onReveal();
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public Coordinates getCoor() {
		return coor;
	}
	
	public void setReveal() {
		revealed = !revealed;
	}
	
	public void setFlag() {
		flagged = !flagged;
	}
	
	public abstract void onHighlight();
}

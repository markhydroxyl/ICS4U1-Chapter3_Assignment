package minesweeper.tiles;

public abstract class Tile {
	protected int[] coor;
	protected boolean revealed;
	protected boolean flagged;
	
	public Tile(int[] aCoor) {
		this.coor = aCoor;
	}
	
	public abstract void onReveal();
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public int[] getCoor() {
		return coor;
	}
	
	public abstract void onHighlight();
}

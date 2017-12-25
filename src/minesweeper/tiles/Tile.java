package minesweeper.tiles;

public abstract class Tile {
	public Tile(int[] aCoor) {
	}
	
	public abstract void onReveal();
	
	public abstract boolean isRevealed();
	
	public abstract boolean isFlagged();
	
	public abstract int[] getCoor();
}

package minesweeper.tiles;

public abstract class Tile {
	protected boolean revealed;
	protected boolean flagged;
	
	public Tile() {
	}
	
	public abstract int onReveal();
	
	public boolean isRevealed() {
		return revealed;
	}
	
	public boolean isFlagged() {
		return flagged;
	}
	
	public void setReveal() {
		revealed = !revealed;
	}
	
	public void setFlag() {
		flagged = !flagged;
	}
	
	public abstract void onHighlight();
}

package minesweeper.tiles;

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
	public boolean isRevealed() {
		return super.isRevealed();
	}

	@Override
	public boolean isFlagged() {
		return super.isFlagged();
	}

	@Override
	public void onHighlight() {
		//Highlight the tile
	}
	
	public int getNumAdjMines() {
		return numAdjMines;
	}
}

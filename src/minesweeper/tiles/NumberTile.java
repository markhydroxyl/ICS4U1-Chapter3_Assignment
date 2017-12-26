package minesweeper.tiles;

public class NumberTile extends Tile {
	private int numAdjMines;
	
	public NumberTile(int[] aCoor) {
		super(aCoor);
		this.numAdjMines = 0;
	}
	
	public NumberTile(int[] aCoor, int aNum){
		super(aCoor);
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
	public int[] getCoor() {
		return super.getCoor();
	}

	@Override
	public void onHighlight() {
		//Highlight the tile
	}
	
	public int getNumAdjMines() {
		return numAdjMines;
	}
}

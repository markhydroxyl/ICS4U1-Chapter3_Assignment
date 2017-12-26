package minesweeper.tiles;

import util.Coordinates;

public class NumberTile extends Tile {
	private int numAdjMines;
	
	public NumberTile(Coordinates aCoor) {
		super(aCoor);
		this.numAdjMines = 0;
	}
	
	public NumberTile(Coordinates aCoor, int aNum){
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
	public Coordinates getCoor() {
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

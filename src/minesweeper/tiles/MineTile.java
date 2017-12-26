package minesweeper.tiles;

import util.Coordinates;

public class MineTile extends Tile {
	public MineTile(Coordinates aCoor) {
		super(aCoor);
	}

	@Override
	public int onReveal() {
		// TODO Auto-generated method stub
		// Tell MinesweeperGame that the game has been lost, reveal all tiles to player
		// Tell game to modify score appropriately
		return -1;
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
}

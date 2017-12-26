package minesweeper.tiles;

public class BlankTile extends Tile {
	
	public BlankTile(int[] aCoor) {
		super(aCoor);
	}

	@Override
	public int onReveal() {
		//Search for all other adjacent blank tiles and reveal
		// Tell game to modify score appropriately
		return 1;
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
}

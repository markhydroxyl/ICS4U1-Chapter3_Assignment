package minesweeper.tiles;

public class MineTile extends Tile {
	public MineTile(int[] aCoor) {
		super(aCoor);
	}

	@Override
	public void onReveal() {
		// TODO Auto-generated method stub
		// Tell MinesweeperGame that the game has been lost, reveal all tiles to player
		// Tell game to modify socre appropriately
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

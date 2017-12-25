package minesweeper.tiles;

public class NumberTile extends Tile {
	private int[] coor;
	private boolean revealed;
	private boolean flagged;

	public NumberTile(int[] aCoor) {
		super(aCoor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReveal() {
		// TODO Auto-generated method stub
		//Reveal yourself
		// Tell game to modify socre appropriately
	}

	@Override
	public boolean isRevealed() {
		return revealed;
	}

	@Override
	public boolean isFlagged() {
		return flagged;
	}
	
	@Override
	public int[] getCoor() {
		return coor;
	}

	@Override
	public void onHighlight() {
		//Highlight the tile
	}
}

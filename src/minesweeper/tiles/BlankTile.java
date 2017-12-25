package minesweeper.tiles;

import java.util.ArrayList;

public class BlankTile extends Tile {
	private int[] coor;
	private boolean revealed;
	private boolean flagged;
	public BlankTile(int[] aCoor) {
		super(aCoor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onReveal() {
		// TODO Auto-generated method stub
		//Search for all other adjacent blank tiles and reveal
		// Tell game to modify socre appropriately
	}
	
	public void search() {
		//BFS algorithm to find all adjacent blank tiles
		ArrayList<Tile> toBeSearched = new ArrayList<Tile>();
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
}

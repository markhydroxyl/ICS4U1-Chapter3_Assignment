package minesweeper.tiles;

import java.util.ArrayList;

public class BlankTile extends Tile {
	
	public BlankTile(int[] aCoor) {
		super(aCoor);
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

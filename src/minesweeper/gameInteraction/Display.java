package minesweeper.gameInteraction;

import minesweeper.tiles.*;

public class Display {
	public Display() {
		
	}
	
	public void display(Tile[][] aArray) {
		//Show display on screen
		
		//TODO turn this into UI
		for (int i=0; i<aArray.length; i++) {
			for (int j=0; j<aArray[0].length; j++) {
				if(aArray[i][j].isFlagged()) {
					System.out.print("F ");
				} else if (!aArray[i][j].isRevealed()) {
					System.out.print("x ");
				} else if (aArray[i][j] instanceof MineTile) {
					System.out.print("M ");
				} else if (aArray[i][j] instanceof BlankTile) {
					System.out.print("  ");
				} else if (aArray[i][j] instanceof NumberTile) {
					int numMines=((NumberTile) aArray[i][j]).getNumAdjMines() ;
					System.out.print(numMines + " ");
				}
			}
		}
	}
}

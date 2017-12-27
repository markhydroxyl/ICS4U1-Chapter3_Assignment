package minesweeper.gameInteraction;

import minesweeper.tiles.*;

public class Display {
	public Display() {
	}
	
	public void display(Tile[][] aArray) {
		//Show display on screen
		
		//TODO turn this into UI
		System.out.println();
		
		System.out.print("  ");
		for (int i=0; i<aArray[0].length; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		
		for (int i=0; i<aArray.length; i++) {
			for (int j=-1; j<aArray[0].length; j++) {
				if (j==-1) {
					System.out.print(i+" ");
				} else {
					if (aArray[i][j]==null) {
						System.out.print("? ");
					} else {
						if(aArray[i][j].isFlagged()) {
							System.out.print("F ");
						} else if (!aArray[i][j].isRevealed()) {
							System.out.print("x ");
						} else if (aArray[i][j] instanceof MineTile) {
							System.out.print("M ");
						} else if (aArray[i][j] instanceof BlankTile) {
							System.out.print("  ");
						} else if (aArray[i][j] instanceof NumberTile) {
							int numMines=((NumberTile) aArray[i][j]).getNumAdjMines();
							System.out.print(numMines + " ");
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

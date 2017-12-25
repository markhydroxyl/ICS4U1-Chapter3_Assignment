package minesweeper.gameInteraction.scoreMethods;

public class ClassicalScore extends Score {
	private int curScore;
	
	public ClassicalScore(int initScore) {
		super(initScore);
		curScore = initScore;
	}
	
	public ClassicalScore() {
		curScore = 0;
	}

	@Override
	public int getScore() {
		return curScore;
	}

	@Override
	public void modifyScore(int eventType) {
		// If blank tile is revealed, +1 for each blank tile
		// If number tile is revealed, +1
		// If mine tile is revealed, -1
		if (eventType > 0) {
			//Blank tile
			curScore+=eventType;
		} else if (eventType==0) {
			//Number tile
			curScore++;
		} else if (eventType==-1) {
			//Mine
			curScore--;
		}
	}

	@Override
	public void onWin(int time, int aWidth, int aHeight) {
		//On win, add the difference between five times the number of tiles and the time elapsed in seconds
		curScore+=aWidth*aHeight*5-time;
	}
}

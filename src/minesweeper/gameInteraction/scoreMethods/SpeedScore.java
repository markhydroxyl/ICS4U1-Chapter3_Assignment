package minesweeper.gameInteraction.scoreMethods;

public class SpeedScore extends Score {
	private int curScore;

	public SpeedScore(int initScore) {
		super(initScore);
		curScore = initScore;
	}
	
	public SpeedScore() {
		curScore = 0;
	}
	
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
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
		//On win, add the difference between two times the number of tiles and the time elapsed in seconds
		curScore+=aWidth*aHeight*2-time;
	}

}

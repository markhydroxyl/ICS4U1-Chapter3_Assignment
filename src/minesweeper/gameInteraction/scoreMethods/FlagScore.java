package minesweeper.gameInteraction.scoreMethods;

public class FlagScore extends Score {
	private int curScore;
	
	public FlagScore(int initScore) {
		super(initScore);
		curScore = initScore;
	}
	
	public FlagScore() {
		curScore = 0;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return curScore;
	}

	@Override
	public void modifyScore(int eventType) {
		// TODO Auto-generated method stub
		// If blank tile is revealed, +1 for each blank tile
		// If number tile is revealed, +1
		// If mine tile is revealed, -1
		// If flag is planted, +2
		// If flag is removed, -2
		
		if (eventType > 0) {
			curScore+=eventType;
		} else if (eventType==0) {
			curScore++;
		} else if (eventType == -1) {
			//Mine
			curScore--;
		} else if (eventType == -2) {
			//Plant flag
			curScore+=2;
		} else if (eventType == -3) {
			//Remove flag
			curScore-=2;
		}
	}
	
	@Override
	public void onWin(int time, int aWidth, int aHeight) {
		//On win, add the difference between six times the number of tiles and the time elapsed in seconds
		curScore+=aWidth*aHeight*6-time;
	}
}

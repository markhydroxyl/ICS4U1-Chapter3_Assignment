package minesweeper.gameInteraction.scoreMethods;

public abstract class Score {
	public Score(int initScore) {
	}
	
	public Score() {
		
	}
	
	public abstract int getScore();
	
	public abstract void modifyScore(int eventType);
	
	public abstract void onWin(int time, int aWidth, int aHeight);
}

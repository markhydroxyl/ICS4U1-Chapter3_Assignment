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
		// TODO Auto-generated method stub
		// If blank tile is revealed, +1 for each blank tile
		// If number tile is revealed, +1
		// If mine tile is revealed, -1
		// If game finished, turn time into seconds, add width*height*2-time
	}

}

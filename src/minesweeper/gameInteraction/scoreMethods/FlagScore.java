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
		// If game finished, turn time into seconds, add width*height*6-time
	}
}

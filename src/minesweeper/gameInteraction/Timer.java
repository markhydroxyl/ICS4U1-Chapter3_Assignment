package minesweeper.gameInteraction;

public class Timer {
	private int time;
	
	public Timer(int aTime) {
		this.time = aTime;
	}
	
	public Timer() {
		this.time = 0;
	}
	
	public void incrementTime() {
		time++;
	}
	
	public int getTime() {
		return time;
	}
}

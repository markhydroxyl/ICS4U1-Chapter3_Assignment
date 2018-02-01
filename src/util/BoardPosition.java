package util;

public class BoardPosition {
	private int x;
	private int y;
	
	public BoardPosition(int x1, int y1) {
		this.x = x1;
		this.y = y1;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x1) {
		this.x = x1;
	}
	
	public void setY(int y1) {
		this.y = y1;
	}
}

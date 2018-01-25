package application;

public class ReversiPiece extends ReversiBoard {
	public String colour;
	final public int radius = 10;
	public int x;
	public int y;

	public ReversiPiece() {

	}

	@Override
	public void getCoords(int gridSpace) {
		//Sets up the x and y coordinates based on their gridspace. 
		this.x = 20 * (gridSpace % 8);
		this.y = 20 * (gridSpace / 8);
	}
}

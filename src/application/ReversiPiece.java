package application;

public class ReversiPiece extends ReversiBoard {
	public String colour;
	final public int radius = 40;
	public int x;
	public int y;

	public ReversiPiece(int gridSpace) {
		getCoords(gridSpace);
	}

	@Override
	public void getCoords(int gridSpace) {
		//Sets up a pieces x and y coordinates based on the pieces grid space. 
		this.x = 80 * (gridSpace % 8);
		this.y = 80 * (gridSpace / 8);
	}
}

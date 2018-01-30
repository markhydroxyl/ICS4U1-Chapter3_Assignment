package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReversiPiece extends ReversiBoard {
	public String colour = "g";
	public int buttonX;
	public int buttonY;
	public Button button;
	public int array1;
	public int array2;

	public ReversiPiece(int gridspace) {
		this.gridSpace = gridspace;
	}

	public void setCoords() {
		// Initializes a pieces x and y coordinates based on the pieces grid
		// space.
		this.buttonX = 80 * ((this.gridSpace - 1) % 8);
		this.buttonY = 80 * ((this.gridSpace - 1) / 8);
	}

	public void initializeButton(ReversiPiece piece, ReversiPiece[][] pieceArray, int arrayX, int arrayY) {
		this.button = new Button();
		this.array1 = arrayX;
		this.array2 = arrayY;
		this.setCoords();
		this.button.setLayoutX(this.buttonX);
		this.button.setLayoutY(this.buttonY);
		this.button.setText(String.valueOf(array1 + "" + array2));
		this.button.setMinWidth(80);
		this.button.setMinHeight(80);
		this.setColor("g");
		// int x = this.gridSpace;
		// int y = gridSpace - 1;
		int x = (this.gridSpace - 1) / 8;
		int y = (this.gridSpace - 1) % 8;
		this.button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String a;
				if (ReversiPiece.turnCount % 2 == 0) {
					a = "w";
				} else {
					a = "b";
				}

				if (ReversiPiece.tileOccupied[x][y] == false && validMove(piece, pieceArray, a)) {
					ReversiPiece.tileOccupied[x][y] = true;
					piece.setColor(a);
					System.out.println("INVALID");

					ReversiPiece.turnCount++;
				} else {
					System.out.println("INVALID");
				}
			}
		});
	}

	public void setColor(String a) {
		if (a.equals("g")) {
			this.button.setStyle("-fx-base: #228B22;");
			this.colour = "g";
		} else if (a.equals("b")) {
			this.button.setStyle("-fx-base: #000000;");
			this.colour = "b";
		} else if (a.equals("w")) {
			this.button.setStyle("-fx-base: #ffffff;");
			this.colour = "w";

		}
	}

	public boolean validMove(ReversiPiece piece, ReversiPiece[][] pieceArray, String tileColour) {
		boolean x = false;
		String a;
		int r = piece.array1;
		int l = piece.array2;

		if (piece.array1 != 0 && piece.array1 != 7 && piece.array2 != 0 && piece.array2 != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						x = true;
						 System.out.print(piece.array1 + i);
						 System.out.println(piece.array2 + t);
					}
				}
			}
		}
		return x;
	}

}
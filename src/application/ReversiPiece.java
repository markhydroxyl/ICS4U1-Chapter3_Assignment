package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReversiPiece extends ReversiBoard {
	public String colour = "green";
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
		this.button.setText(String.valueOf(gridSpace));
		this.button.setMinWidth(80);
		this.button.setMinHeight(80);
		this.setColor(this.colour);
		// int x = this.gridSpace;
		// int y = gridSpace - 1;
		int x = (this.gridSpace - 1) / 8;
		int y = (this.gridSpace - 1) % 8;
		this.button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				if (ReversiPiece.tileOccupied[x][y] == false) {
					ReversiPiece.tileOccupied[x][y] = true;
					if (ReversiPiece.turnCount % 2 == 0) {
						setColor("white");
					} else if (ReversiPiece.turnCount % 2 == 1) {
						setColor("black");
					}
					// System.out.print(piece.array1);
					// System.out.println(piece.array2);

					// System.out.println(gridSpace + " " +
					// ReversiPiece.turnCount);
					ReversiPiece.turnCount++;
				}
				if (validMove(piece, pieceArray)) {
					System.out.println("VALID");
				} else {
					System.out.println("INVALID");
				}
			}
		});
	}

	public void setColor(String a) {
		if (a.equals("green")) {
			this.button.setStyle("-fx-base: #228B22;");
			this.colour = "green";
		} else if (a.equals("black")) {
			this.button.setStyle("-fx-base: #000000;");
			this.colour = "black";
		} else if (a.equals("white")) {
			this.button.setStyle("-fx-base: #ffffff;");
			this.colour = "white";

		}
	}

	public boolean validMove(ReversiPiece piece, ReversiPiece[][] pieceArray) {
		boolean x = false;
		String a;

		if (piece.array1 != 0 && piece.array1 != 7 && piece.array2 != 0 && piece.array2 != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					if (i != 0 && t != 0) {
						a = pieceArray[piece.array1 + i][piece.array1 + t].colour;
						// System.out.println(a + " " + pieceArray[piece.array1
						// +
						// i][piece.array1 + t].gridSpace);
						// System.out.print(piece.array1 + i);
						// System.out.println(piece.array2 + t);
						if (!(a.equals(piece.colour)) && !(a.equals("green"))) {
							x = true;
							System.out.print(piece.array1 + i);
							System.out.println(piece.array2 + t);
							System.out.println(a);
						}
					}
				}
			}
		}
		return x;
	}

}
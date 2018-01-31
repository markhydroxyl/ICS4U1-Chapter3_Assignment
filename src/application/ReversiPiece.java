package application;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReversiPiece extends ReversiBoard {
	// Instance variables for each ReversiPiece

	// Color of the button/piece
	public String color = "g";

	// X and Y coordinates of the button on the stage
	public int buttonX;
	public int buttonY;

	// The button its self
	public Button button;

	// the array indexes of the reversiPiece array
	public int array1;
	public int array2;

	// Constructor used to set up ReversiPiece
	public ReversiPiece(int gridspace, int i, int t) {
		this.gridSpace = gridspace;

		// assigns the array indexes to variables for later use.
		this.array1 = i;
		this.array2 = t;
	}

	public void setCoords() {
		// Initializes a pieces x and y coordinates of a button based on grid
		// space.
		this.buttonX = 80 * ((this.gridSpace - 1) % 8);
		this.buttonY = 80 * ((this.gridSpace - 1) / 8);
	}

	// Initializes the button with its onClickEvent() and its coordinates.
	public void initializeButton(ReversiPiece piece, ReversiPiece[][] pieceArray) {
		// creates button
		this.button = new Button();

		// sets up the buttons coordinates and color
		this.setCoords();
		this.button.setLayoutX(this.buttonX);
		this.button.setLayoutY(this.buttonY);
		this.button.setMinWidth(80);
		this.button.setMinHeight(80);
		this.setColor("g");

		// Set up of onClickEvent for the button
		this.button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Indicates if the button should be white or black based on
				// turnCount
				String a;
				if (ReversiPiece.turnCount % 2 == 0) {
					a = "w";
				} else {
					a = "b";
				}
				// Checks if there are any available moves if not the game ends.
				if (anyMovesAvailable(piece, pieceArray, a)) {
					// Checks if the indicated move is valid and if the tile is
					// already occupied or not
					if (ReversiPiece.tileOccupied[array1][array2] == false && validMove(piece, pieceArray, a)) {
						// sets the tile to be occupied
						ReversiPiece.tileOccupied[array1][array2] = true;
						// sets the new color
						piece.setColor(a);
						// prints valid to indicate that the move was valid
						System.out.println("VALID");

						// Increases turnCount and changes turnIndicator based
						// on it.
						ReversiPiece.turnCount++;
						if (ReversiPiece.turnCount % 2 == 0) {
							ReversiBoard.turnIndicator.setText("White's Turn");
						} else {
							ReversiBoard.turnIndicator.setText("Black's Turn");
						}
					} else {
						System.out.println("INVALID");
					}
				} else {
					countScore(pieceArray);
				}
			}
		});
	}

	// sets the color of the button
	public void setColor(String a) {
		if (a.equals("g")) {
			this.button.setStyle("-fx-base: #228B22;");
			this.color = "g";
		} else if (a.equals("b")) {
			this.button.setStyle("-fx-base: #000000;");
			this.color = "b";
		} else if (a.equals("w")) {
			this.button.setStyle("-fx-base: #ffffff;");
			this.color = "w";

		}
	}

	// Checks if a move is valid or not.
	// Parameters are the ReversiPiece instance, the ReversiPiece array, and the
	// color of the piece
	public boolean validMove(ReversiPiece piece, ReversiPiece[][] pieceArray, String tileColor) {
		// variable will become true if the move is valid
		boolean x = false;
		// We want to repaint the pieces so this is true.
		boolean repaint = true;
		// String records the color at the given ReversiPiece
		String a;
		int r = piece.array1;
		int l = piece.array2;
		// recolor is the color that the code is looking for
		String realColor = "";
		if (tileColor.equals("w")) {
			realColor = "b";
		} else {
			realColor = "w";
		}
		// These loops check all of the 8 surrounding pieces to see if they are
		// the opposite color. if they are it executes the valid trace code.
		// Each section of the if statements accounts for if the piece is on
		// the border of or if it is not
		if (r != 0 && r != 7 && l != 0 && l != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					// If the color at the surrounding piece does not equal the
					// tileColor and the color is not green valid trace runs
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							// if valid trace returns true the move is valid and
							// executes
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l != 0 && l != 7) {
			// Same as above, but the index values are altered in the loop to
			// account for being on the border
			for (int i = 0; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l == 0) {
			for (int i = 0; i < 2; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l == 7) {
			for (int i = 0; i < 2; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l == 0) {
			for (int i = -1; i < 1; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l == 7) {
			for (int i = -1; i < 1; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l != 0 && l != 7) {
			for (int i = -1; i < 1; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (l == 7 && r != 0 && r != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (l == 0 && r != 0 && r != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].color;
					if (!(a.equals(tileColor)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColor, realColor, repaint)) {
							x = true;
						}
					}
				}
			}
		}
		return x;
	}

	// Traces the pieces around to check if they will lead to a proper move
	public boolean validTrace(int r, int l, int i, int t, ReversiPiece[][] pieceArray, String tileColor,
			String realColor, boolean repaint) {
		// returns false until the trace is set to be true
		boolean x = false;
		// breaks the do while loop when set false.
		boolean breakLoop = true;
		String a;
		int y = i;
		int z = t;

		// Traces the piece directly below the clicked piece
		if (i == 1 && t == 0) {
			do {
				int j = r + y;
				// checks if the piece below is of another color until one of
				// the same color is found or a green piece is found
				a = pieceArray[j][l].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					// if the repaint variable in valid move is true
					if (repaint) {
						// repaints the pieces to the color they should be
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				// increases the index
				y++;

				if (j == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == 0) {
			// same as above, but traces the above pieces
			do {
				int j = r + y;

				a = pieceArray[j][l].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				y--;

				if (j == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 0 && t == 1) {
			// same as above, but traces the right pieces
			do {
				int j = l + z;

				a = pieceArray[r][j].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				z++;

				if (j == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 0 && t == -1) {
			// same as above, but traces the left pieces
			do {
				int j = l + z;

				a = pieceArray[r][j].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				z--;

				if (j == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == -1) {
			// same as above, but traces up and to the left
			do {
				int j = r + y;
				int s = l + z;

				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				y--;
				z--;
				if (j == 0 || s == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 1 && t == 1) {
			// same as above, but traces down and to the right
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				y++;
				z++;

				if (j == 7 || s == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == 1) {
			// same as above, but traces up and to the right
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				y--;
				z++;
				if (j == 0 || s == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 1 && t == -1) {
			// same as above, but traces down and to the left
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
				} else if (a.equals(tileColor)) {
					x = true;
					breakLoop = false;
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColor, realColor);
					}
				} else {
					breakLoop = false;
				}
				y++;
				z--;
				if (j == 7 || s == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		}
		return x;
	}

	// repaints the pieces to the correct colors based on the move
	public void repaint(int r, int l, int i, int t, ReversiPiece[][] pieceArray, String tileColor, String realColor) {
		// Similar to the valid trace code, however since I know that the trace
		// is valid, I change the color of the pieces
		boolean breakLoop = true;
		String a;
		int y = i;
		int z = t;
		if (i == 1 && t == 0) {
			do {
				int j = r + y;
				a = pieceArray[j][l].color;
				if (a.equals(realColor)) {
					pieceArray[j][l].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y++;
			} while (breakLoop);
		} else if (i == -1 && t == 0) {
			do {
				int j = r + y;
				a = pieceArray[j][l].color;
				if (a.equals(realColor)) {
					pieceArray[j][l].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y--;
			} while (breakLoop);
		} else if (i == 0 && t == 1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].color;
				if (a.equals(realColor)) {
					pieceArray[r][j].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				z++;
			} while (breakLoop);
		} else if (i == 0 && t == -1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].color;
				if (a.equals(realColor)) {
					pieceArray[r][j].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				z--;

			} while (breakLoop);
		} else if (i == -1 && t == -1) {
			do {
				int j = r + y;
				int s = l + z;

				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
					pieceArray[j][s].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y--;
				z--;
			} while (breakLoop);
		} else if (i == 1 && t == 1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
					pieceArray[j][s].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y++;
				z++;
			} while (breakLoop);
		} else if (i == -1 && t == 1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
					pieceArray[j][s].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y--;
				z++;
			} while (breakLoop);
		} else if (i == 1 && t == -1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].color;
				if (a.equals(realColor)) {
					pieceArray[j][s].setColor(tileColor);
				} else {
					breakLoop = false;
				}
				y++;
				z--;
			} while (breakLoop);
		}
	}

	public boolean anyMovesAvailable(ReversiPiece piece, ReversiPiece[][] pieceArray, String tileColor) {
		String realColor;
		if (tileColor.equals("w")) {
			realColor = "b";
		} else {
			realColor = "w";
		}
		boolean x = false;
		String a;
		boolean repaint = false;

		for (int s = 0; s < 8; s++) {
			for (int z = 0; z < 8; z++) {
				if (ReversiBoard.tileOccupied[s][z] == false) {
					if (s != 0 && s != 7 && z != 0 && z != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z != 0 && z != 7) {
						for (int i = 0; i < 2; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z == 0) {
						for (int i = 0; i < 2; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z == 7) {
						for (int i = 0; i < 2; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z == 0) {
						for (int i = -1; i < 1; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z == 7) {
						for (int i = -1; i < 1; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z != 0 && z != 7) {
						for (int i = -1; i < 1; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (z == 7 && s != 0 && s != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (z == 0 && s != 0 && s != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s + i][z + t].color;
								if (!(a.equals(tileColor)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColor, realColor, repaint)) {
										x = true;
									}
								}
							}
						}
					}
				}
			}
		}
		return x;
	}

	// Counts the score and ends the game
	public void countScore(ReversiPiece[][] pieceArray) {
		int numWhite = 0;
		int numBlack = 0;
		for (int s = 0; s < 8; s++) {
			for (int z = 0; z < 8; z++) {
				if (pieceArray[s][z].color.equals("w")) {
					numWhite++;
				} else if (pieceArray[s][z].color.equals("b")) {
					numBlack++;
				}
			}
		}
		if (numWhite > numBlack) {
			ReversiBoard.turnIndicator.setText("White Wins " + numWhite + ":" + numBlack);
		} else {
			ReversiBoard.turnIndicator.setText("Black Wins " + numBlack + ":" + numWhite);
		}
	}
}
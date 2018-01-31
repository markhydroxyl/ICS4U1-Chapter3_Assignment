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
//				if (anyMovesAvailable(piece, pieceArray, a)) {
					if (ReversiPiece.tileOccupied[x][y] == false && validMove(piece, pieceArray, a)) {
						ReversiPiece.tileOccupied[x][y] = true;
						piece.setColor(a);
						System.out.println("VALID");
						ReversiPiece.turnCount++;
						if (ReversiPiece.turnCount % 2 == 0) {
							ReversiBoard.turnIndicator.setText("White's Turn");
						} else {
							ReversiBoard.turnIndicator.setText("Black's Turn");
						}
					} else {
						System.out.println("INVALID");
					}
//				} else {
//					countScore(pieceArray);
//				}
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
		boolean repaint = true;
		String a;
		int r = piece.array1;
		int l = piece.array2;
		String realColour = "";
		if (tileColour.equals("w")) {
			realColour = "b";
		} else {
			realColour = "w";
		}

		if (r != 0 && r != 7 && l != 0 && l != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l != 0 && l != 7) {
			for (int i = 0; i < 2; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l == 0) {
			for (int i = 0; i < 2; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 0 && l == 7) {
			for (int i = 0; i < 2; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l == 0) {
			for (int i = -1; i < 1; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l == 7) {
			for (int i = -1; i < 1; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (r == 7 && l != 0 && l != 7) {
			for (int i = -1; i < 1; i++) {
				for (int t = -1; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (l == 7 && r != 0 && r != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = -1; t < 1; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		} else if (l == 0 && r != 0 && r != 7) {
			for (int i = -1; i < 2; i++) {
				for (int t = 0; t < 2; t++) {
					a = pieceArray[r + i][l + t].colour;
					if (!(a.equals(tileColour)) && !(a.equals("g"))) {
						if (validTrace(r, l, i, t, pieceArray, tileColour, realColour, repaint)) {
							x = true;
						}
					}
				}
			}
		}
		return x;
	}

	public boolean validTrace(int r, int l, int i, int t, ReversiPiece[][] pieceArray, String tileColour,
			String realColour, boolean repaint) {
		boolean x = false;
		boolean breakLoop = true;
		String a;
		int y = i;
		int z = t;

		if (i == 1 && t == 0) {
			do {
				int j = r + y;

				a = pieceArray[j][l].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + l);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				y++;

				if (j == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == 0) {
			do {
				int j = r + y;

				a = pieceArray[j][l].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + l);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				y--;

				if (j == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 0 && t == 1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].colour;
				if (a.equals(realColour)) {
					System.out.println(r + "" + j);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				z++;

				if (j == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 0 && t == -1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].colour;
				if (a.equals(realColour)) {
					System.out.println(r + "" + j);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				z--;

				if (j == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == -1) {
			do {
				int j = r + y;
				int s = l + z;

				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + s);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				y--;
				z--;
				if (j == 0 || s == 0) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 1 && t == 1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + s);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				y++;
				z++;

				if (j == 7 || s == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == -1 && t == 1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + s);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
				}
				y--;
				z++;
				if (j == 0 || s == 7) {
					breakLoop = false;
				}
			} while (breakLoop);
		} else if (i == 1 && t == -1) {
			do {
				int j = r + y;
				int s = l + z;
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					System.out.println(j + "" + s);
				} else if (a.equals(tileColour)) {
					x = true;
					breakLoop = false;
					System.out.println("VALID trace");
					if (repaint) {
						repaint(r, l, i, t, pieceArray, tileColour, realColour);
					}
				} else {
					breakLoop = false;
					System.out.println("INVALID trace");
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

	public void repaint(int r, int l, int i, int t, ReversiPiece[][] pieceArray, String tileColour, String realColour) {
		boolean breakLoop = true;
		String a;
		int y = i;
		int z = t;
		if (i == 1 && t == 0) {
			do {
				int j = r + y;
				a = pieceArray[j][l].colour;
				if (a.equals(realColour)) {
					pieceArray[j][l].setColor(tileColour);
				} else {
					breakLoop = false;
				}
				y++;
			} while (breakLoop);
		} else if (i == -1 && t == 0) {
			do {
				int j = r + y;
				a = pieceArray[j][l].colour;
				if (a.equals(realColour)) {
					pieceArray[j][l].setColor(tileColour);
				} else {
					breakLoop = false;
				}
				y--;
			} while (breakLoop);
		} else if (i == 0 && t == 1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].colour;
				if (a.equals(realColour)) {
					pieceArray[r][j].setColor(tileColour);
				} else {
					breakLoop = false;
				}
				z++;
			} while (breakLoop);
		} else if (i == 0 && t == -1) {
			do {
				int j = l + z;

				a = pieceArray[r][j].colour;
				if (a.equals(realColour)) {
					pieceArray[r][j].setColor(tileColour);
				} else {
					breakLoop = false;
				}
				z--;

			} while (breakLoop);
		} else if (i == -1 && t == -1) {
			do {
				int j = r + y;
				int s = l + z;

				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					pieceArray[j][s].setColor(tileColour);
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
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					pieceArray[j][s].setColor(tileColour);
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
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					pieceArray[j][s].setColor(tileColour);
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
				a = pieceArray[j][s].colour;
				if (a.equals(realColour)) {
					pieceArray[j][s].setColor(tileColour);
				} else {
					breakLoop = false;
				}
				y++;
				z--;
			} while (breakLoop);
		}
	}

	public boolean anyMovesAvailable(ReversiPiece piece, ReversiPiece[][] pieceArray, String tileColour) {
		String realColour = "";
		if (tileColour.equals("w")) {
			realColour = "b";
		} else {
			realColour = "w";
		}
		boolean x = false;
		String a;
		boolean repaint = false;

		for (int s = 0; s < 8; s++) {
			for (int z = 0; z < 8; z++) {
				if (ReversiGrid.tileOccupied[s][z] == false) {
					if (s != 0 && s != 7 && z != 0 && z != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z != 0 && z != 7) {
						for (int i = 0; i < 2; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z == 0) {
						for (int i = 0; i < 2; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 0 && z == 7) {
						for (int i = 0; i < 2; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z == 0) {
						for (int i = -1; i < 1; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z == 7) {
						for (int i = -1; i < 1; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (s == 7 && z != 0 && z != 7) {
						for (int i = -1; i < 1; i++) {
							for (int t = -1; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (z == 7 && s != 0 && s != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = -1; t < 1; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
										x = true;
									}
								}
							}
						}
					} else if (z == 0 && s != 0 && s != 7) {
						for (int i = -1; i < 2; i++) {
							for (int t = 0; t < 2; t++) {
								a = pieceArray[s][z].colour;
								if (!(a.equals(tileColour)) && !(a.equals("g"))) {
									if (validTrace(s, z, i, t, pieceArray, tileColour, realColour, repaint)) {
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

	public void countScore(ReversiPiece[][] pieceArray) {
		int numWhite = 0;
		int numBlack = 0;
		for (int s = 0; s < 8; s++) {
			for (int z = 0; z < 8; z++) {
				if (pieceArray[s][z].colour.equals("w")) {
					numWhite++;
				} else if (pieceArray[s][z].colour.equals("b")) {
					numBlack++;
				}
			}
		}
		System.out.println(numWhite + " " + numBlack);
	}
}
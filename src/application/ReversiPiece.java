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

	public ReversiPiece(int gridspace) {
		this.gridSpace = gridspace;
	}

	public void setCoords() {
		// Initializes a pieces x and y coordinates based on the pieces grid
		// space.
		this.buttonX = 80 * ((this.gridSpace - 1) % 8);
		this.buttonY = 80 * ((this.gridSpace - 1) / 8);
	}

	public void initializeButton() {
		this.button = new Button();
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
				if (tileOccupied[x][y] == false) {
					tileOccupied[x][y] = true;
					System.out.println(gridSpace);
				}
			}
		});
	}

	public void setColor(String a) {
		if (a.equals("green")) {
			this.button.setStyle("-fx-base: #228B22;");
		} else if (a.equals("black")) {
			this.button.setStyle("-fx-base: #000000;");
		} else if (a.equals("white")) {
			this.button.setStyle("-fx-base: #ffffff;");
		}
	}
}
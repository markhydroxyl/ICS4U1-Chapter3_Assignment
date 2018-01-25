package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ReversiPiece extends ReversiBoard {
	public String colour;
	public int buttonX;
	public int buttonY;
	public Button button;

	public ReversiPiece(int gridspace) {
		this.gridSpace = gridspace;
	}

	public void getCoords() {
		// Initializes a pieces x and y coordinates based on the pieces grid space.
		this.buttonX = 80 * (this.gridSpace % 8);
		this.buttonY = 80 * (this.gridSpace / 8);
	}
	
	public void initializeButton() {
		this.button = new Button();
		this.getCoords();
		this.button.setLayoutX(this.buttonX);
		this.button.setLayoutY(this.buttonY);
		this.button.setText(String.valueOf(gridSpace));
		int x = this.gridSpace;
		this.button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println(x);
			}
		});
	}	
}
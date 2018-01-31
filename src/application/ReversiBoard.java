package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReversiBoard extends ReversiGrid {
	// Stores the X and Y sizes of the reversiBoard
	final public int BoardSizeX = 640;
	final public int BoardSizeY = 680;

	// Store the stage, pane, and scene of a ReversiBoard
	public Stage reversiStage;
	public static Pane root2;
	public Scene board;

	// Turn indicator for on the board
	public static Text turnIndicator;

	public byte getState() {
		byte x = 0;
		
		return x;
	}

	// Draw board method sets up the reversi stage
	public void newGame() {
		// stage, pane, and scene setup
		reversiStage = new Stage();
		root2 = new Pane();
		board = new Scene(root2, BoardSizeX, BoardSizeY);
		board.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// sets up the turn indicator
		turnIndicator = new Text(280, 660, "White's Turn");
		root2.getChildren().add(turnIndicator);

		// Initializes the new stage
		reversiStage.setScene(board);
		reversiStage.setTitle("Reversi");
		reversiStage.setResizable(false);
		reversiStage.sizeToScene();
		reversiStage.show();
	}

	// public void drawButtons(){
	// Button o = new Button();//place holder
	// this.root2.getChildren().add(o);
	// }
}
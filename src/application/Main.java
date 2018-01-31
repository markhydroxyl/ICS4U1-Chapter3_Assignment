package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {
	//The array of reversi pieces
	public ReversiPiece[][] reversiObjects = new ReversiPiece[8][8];
	//int used for setup of the individual instances of the array above
	public int setup = 1;

	@Override
	public void start(Stage primaryStage) {
		try {
			// Set up code for the start screen
			Pane root = new Pane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Button startGame = new Button();
			startGame.setLayoutX(50);
			startGame.setLayoutY(50);
			root.getChildren().add(startGame);
			startGame.setText("START");
			// End of setup code
			startGame.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// Beginning of game this button starts the game and draws
					// the new board

					// creates a ReversiBoard object
					ReversiBoard start = new ReversiBoard();
					primaryStage.close();
					// draws the board
					start.newGame();

					// sets up the buttons
					reversiBoardSetUp();
					// adds the buttons to the stage
					for (int i = 0; i < 8; i++) {
						for (int t = 0; t < 8; t++) {
							ReversiBoard.root2.getChildren().add(reversiObjects[i][t].button);
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void reversiBoardSetUp() {
		// Loop sets up the buttons and initializes them with the
		// initializeButton method
		for (int i = 0; i < 8; i++) {
			// setup is a counter used to set up the gridspace value
			for (int t = 0; t < 8; t++, setup++) {
				reversiObjects[i][t] = new ReversiPiece(setup, i, t);
				// Inputs the individual instance as well as the array of
				// instances, and the counters for the specific instance in the
				// array
				reversiObjects[i][t].initializeButton(reversiObjects[i][t], reversiObjects);

			}

		}
		// sets up 4 starting tiles
		reversiObjects[3][3].setColor("w");
		ReversiPiece.tileOccupied[3][3] = true;
		reversiObjects[4][4].setColor("w");
		ReversiPiece.tileOccupied[4][4] = true;
		reversiObjects[3][4].setColor("b");
		ReversiPiece.tileOccupied[3][4] = true;
		reversiObjects[4][3].setColor("b");
		ReversiPiece.tileOccupied[4][3] = true;
	}

}

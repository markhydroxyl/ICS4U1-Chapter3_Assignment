package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReversiBoard implements Grid {
	final public int BoardSizeX = 640;
	final public int BoardSizeY = 680;
	public Stage reversiStage;
	public static Pane root2;
	public Scene board;
	public static Text turnIndicator;
	public static boolean tileOccupied[][] = new boolean[8][8];
	public int gridSpace = 0;

	public static int turnCount = 0;

	public void drawBoard() {
		reversiStage = new Stage();
		root2 = new Pane();
		board = new Scene(root2, BoardSizeX, BoardSizeY);
		board.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		turnIndicator = new Text(280, 660, "White's Turn");
		root2.getChildren().add(turnIndicator);

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
package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReversiBoard extends ReversiGrid {
	final public int BoardSizeX = 640;
	final public int BoardSizeY = 640;
	public Stage reversiStage;
	public Pane root2;
	public Scene board;

	public void drawBoard() {
		reversiStage = new Stage();
		root2 = new Pane();
		board = new Scene(root2, BoardSizeX, BoardSizeY);
		board.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		reversiStage.setScene(board);
		reversiStage.setResizable(false);
		reversiStage.sizeToScene();
		reversiStage.show();
	}
	// public void drawButtons(){
	// Button o = new Button();//place holder
	// this.root2.getChildren().add(o);
	// }
}
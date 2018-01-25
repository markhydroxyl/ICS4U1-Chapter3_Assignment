package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReversiBoard extends ReversiGrid {
	final public int sizeX = 640;
	final public int sizeY = 640;
	
	
	public void drawBoard() {
			Stage reversiStage = new Stage();
			BorderPane root = new BorderPane();
			Scene board = new Scene(root,sizeX,sizeY);
			board.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			reversiStage.setScene(board);
			reversiStage.show();
	}

	public void getCoords(int gridSpace){
		
	}
}
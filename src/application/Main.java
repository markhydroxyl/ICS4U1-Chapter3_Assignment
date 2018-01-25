package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			Button startGame = new Button();
			startGame.setLayoutX(50);
			startGame.setLayoutY(50);
			root.getChildren().add(startGame);
			startGame.setText("START");
			startGame.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					ReversiBoard start = new ReversiBoard();
					start.drawBoard();
					primaryStage.close();
				}
			});
		} catch(Exception e) { 
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import minesweeper.MinesweeperGame;
import util.BoardPosition;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import java.util.Scanner;


public class Main extends Application {
	EventHandler<KeyEvent> keyBtnDownEvent = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			
		}
	};
	
	@Override
	public void start(Stage primaryStage) {
		
	}
	
	public static void startG() {
		Scanner in = new Scanner(System.in);
		
		MinesweeperGame game = new MinesweeperGame();
		game.newGame(10, 10, 15);
		game.gameBoard.display();
		
		boolean gameOver = false;
		while(!gameOver) {
			int xCoor = in.nextInt();
			int yCoor = in.nextInt();
			String instruction = in.nextLine();
			if (instruction.length()<2) {
				System.out.println("Please include a \'D\' for digging, or an \'F\' for flagging.");
			} else if (instruction.charAt(1) == 'D') {
				game.gameBoard.onReveal(new BoardPosition(xCoor, yCoor));
				game.gameBoard.display();
			} else if (instruction.charAt(1) == 'F') {
				game.gameBoard.onFlag(new BoardPosition(xCoor, yCoor));
				game.gameBoard.display();
			}
			gameOver = game.getState()!=null? true: false;
		}
		
		if(game.getState().equals("win")) {
			System.out.println("You won!");
		} else {
			System.out.println("You lose...");
		}
		
		in.close();
		
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		startG();
	}
}

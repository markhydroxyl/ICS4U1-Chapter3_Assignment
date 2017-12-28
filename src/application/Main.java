package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import minesweeper.MinesweeperGame;
import util.BoardPosition;
import util.Difficulty;
import util.Game;
import util.MinesweeperConstants;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

//import java.util.Scanner;


public class Main extends Application {
	Pane root = new Pane();
	Scene scene = new Scene(root, 500, 500);
	Difficulty gameMode = Difficulty.EASY; //TODO change this to proper form
	boolean minesweeper = false;
	boolean reversi = false;
	Game curGame;
	
	EventHandler<MouseEvent> mouseClickEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (minesweeper) {
				double clickX = event.getScreenX()-MinesweeperConstants.X_OFFSET;
				double clickY = event.getScreenY()-MinesweeperConstants.Y_OFFSET;
				System.out.println("Clicked at: ("+clickX+", "+clickY+")");
				
				if(clickX>0 && clickY>0) {
					if (gameMode.toString().equals("EASY") && 
							clickX < MinesweeperConstants.EASY_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
							clickY < MinesweeperConstants.EASY_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET) {
						int x = (int)(clickX)/MinesweeperConstants.TILE_WIDTH;
						int y = (int)(clickY)/MinesweeperConstants.TILE_HEIGHT;
						System.out.println("x: "+x+", y: "+y);
						((MinesweeperGame) curGame).gameBoard.onReveal(new BoardPosition(x, y));
						System.out.println("Clicked on: ("+x+", "+y+")");
					} else if (gameMode.toString().equals("MEDIUM")&& 
							clickX < MinesweeperConstants.MED_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
							clickY < MinesweeperConstants.MED_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET) {
						int x = (int)(clickX)/MinesweeperConstants.TILE_WIDTH;
						int y = (int)(clickY)/MinesweeperConstants.TILE_HEIGHT;
						((MinesweeperGame) curGame).gameBoard.onReveal(new BoardPosition(x, y));
					} else if (gameMode.toString().equals("HARD")&& 
							clickX < MinesweeperConstants.HARD_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
							clickY < MinesweeperConstants.HARD_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET) {
						int x = (int)(clickX)/MinesweeperConstants.TILE_WIDTH;
						int y = (int)(clickY)/MinesweeperConstants.TILE_HEIGHT;
						((MinesweeperGame) curGame).gameBoard.onReveal(new BoardPosition(x, y));
					} else if (gameMode.toString().equals("LUNATIC")&& 
							clickX < MinesweeperConstants.LUNA_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
							clickY < MinesweeperConstants.LUNA_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET) {
						int x = (int)(clickX)/MinesweeperConstants.TILE_WIDTH;
						int y = (int)(clickY)/MinesweeperConstants.TILE_HEIGHT;
						((MinesweeperGame) curGame).gameBoard.onReveal(new BoardPosition(x, y));
					}
				}
			}
			
			event.consume();
		}
	};
	
//	@Override
//	public void test(Stage primaryStage) {
//		Canvas canvas = new Canvas(400, 200);
//		// Get the graphics context of the canvas
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		// Set line width
//		gc.setLineWidth(1.0);
//		// Set fill color
//		gc.setFill(Color.BLUE);
//		
//		// Draw a Text
//		gc.fillText("This is a stroked Text", 10, 50);
//		
//		gc.setFill(Color.RED);
//		
//		Pane aRoot = new Pane();
//		aRoot.getChildren().add(canvas);
//		Scene aScene = new Scene(aRoot);
//		primaryStage.setScene(aScene);
//		primaryStage.show();
//	}
	
	@Override
	public void start(Stage primaryStage) {
		minesweeper = true;
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		if (minesweeper) {
			startMinesweeper(primaryStage);
		} else if (reversi) {}
	}
	
	private void startMinesweeper(Stage primaryStage) {
		curGame = new MinesweeperGame();
		((MinesweeperGame) curGame).newGame(MinesweeperConstants.EASY_NUM_ROWS, MinesweeperConstants.EASY_NUM_COLS, MinesweeperConstants.EASY_MINE_DENSITY);
		((MinesweeperGame) curGame).gameBoard.render(root);
		
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseClickEvent);
		scene.setOnMouseClicked(mouseClickEvent);
		
		new AnimationTimer() {
			@Override
			public void handle(long time) {
				((MinesweeperGame) curGame).gameBoard.timeIncrease();
//				if(time%20 == 0) {
					if (curGame.getState() != 0) {
						primaryStage.close();
						stop();
					} else
						((MinesweeperGame) curGame).gameBoard.render(root);
				}
//			}
		}.start();
	}
	
//	public static void lanuchMinesweeper() {
//		Scanner in = new Scanner(System.in);
//		MinesweeperGame game = new MinesweeperGame();
//		game.newGame(10, 10, 15);
//		game.gameBoard.display(root);
//		
//		boolean gameOver = false;
//		while(!gameOver) {
//			int xCoor = in.nextInt();
//			int yCoor = in.nextInt();
//			String instruction = in.nextLine();
//			if (instruction.length()<2) {
//				System.out.println("Please include a \'D\' for digging, or an \'F\' for flagging.");
//			} else if (instruction.charAt(1) == 'D') {
//				game.gameBoard.onReveal(new BoardPosition(xCoor, yCoor));
//				game.gameBoard.display(root);
//			} else if (instruction.charAt(1) == 'F') {
//				game.gameBoard.onFlag(new BoardPosition(xCoor, yCoor));
//				game.gameBoard.display(root);
//			}
//			gameOver = game.getState()!=null? true: false;
//		}
//		
//		if(game.getState().equals("win")) {
//			System.out.println("You won!");
//		} else {
//			System.out.println("You lose...");
//		}
//		
//		in.close();
//	}
	
	public static void main(String[] args) {
		launch(args);
//		lanuchMinesweeper();
	}
}

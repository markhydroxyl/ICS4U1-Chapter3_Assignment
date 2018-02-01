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
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;

//import java.util.Scanner;


public class Main extends Application {
	Pane root = new Pane();
	Scene scene = new Scene(root, 500, 500);
	Difficulty gameMode = Difficulty.EASY;
	boolean minesweeper = false;
	boolean reversi = false;
	Game curGame;
	
	EventHandler<MouseEvent> mouseClickEvent = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			if (minesweeper) {
				double clickX = event.getScreenX()-MinesweeperConstants.X_OFFSET - (scene.getWindow().getX()+scene.getX());
				double clickY = event.getScreenY()-MinesweeperConstants.Y_OFFSET - (scene.getWindow().getY()+scene.getY());
				System.out.println("Clicked at: ("+clickX+", "+clickY+")");
				boolean inBounds = false;
				
				if((clickX>0 && clickY>0)&&((gameMode.equals(Difficulty.EASY) && 
								clickX < MinesweeperConstants.EASY_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
								clickY < MinesweeperConstants.EASY_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET)||
						(gameMode.equals(Difficulty.MEDIUM) && 
									clickX < MinesweeperConstants.MED_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
								clickY < MinesweeperConstants.MED_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET)||
						(gameMode.equals(Difficulty.HARD) && 
								clickX < MinesweeperConstants.HARD_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
								clickY < MinesweeperConstants.HARD_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET)||
						(gameMode.equals(Difficulty.LUNATIC) && 
								clickX < MinesweeperConstants.LUNA_SCREEN_WIDTH-2*MinesweeperConstants.X_OFFSET &&
								clickY < MinesweeperConstants.LUNA_SCREEN_HEIGHT-2*MinesweeperConstants.Y_OFFSET))) {
					inBounds = true;
				}
				
				if (inBounds) {
					int x = (int)(clickX)/MinesweeperConstants.TILE_WIDTH;
					int y = (int)(clickY)/MinesweeperConstants.TILE_HEIGHT;
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						((MinesweeperGame) curGame).gameBoard.onReveal(new BoardPosition(x, y));
						System.out.print("Dug ");
					} else if (event.getButton().equals(MouseButton.SECONDARY)) {
						((MinesweeperGame) curGame).gameBoard.onFlag(new BoardPosition(x, y));
						System.out.print("Flagged ");
					}
					System.out.println(" on: ("+x+", "+y+")");
				}
			}
			
			event.consume();
		}
	};
	
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
		
		new AnimationTimer() {
			@Override
			public void handle(long time) {
				((MinesweeperGame) curGame).gameBoard.timeIncrease();
//				if(time%20 == 0) {
					if (curGame.getState() == 1) {
						System.out.println("You won!");
						stop();
					} else if (curGame.getState() == -1) {
						System.out.println("You lost...");
						stop();
					}
					
					((MinesweeperGame) curGame).gameBoard.render(root);
				}
//			}
		}.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

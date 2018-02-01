package application;

import javafx.event.ActionEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.MinesweeperGame;
import util.BoardPosition;
import util.Difficulty;
import util.Game;
import util.MinesweeperConstants;


public class Main extends Application {
	Pane root = new Pane();
	Scene scene = new Scene(root, 500, 500);
	Difficulty gameMode = Difficulty.EASY;
	boolean minesweeper = false;
	boolean reversi = false;
	Game curGame;
	
	//The array of reversi pieces
	public ReversiPiece[][] reversiObjects = new ReversiPiece[8][8];
	//int used for setup of the individual instances of the array above
	public int setup = 1;
	
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
		//Minesweeper button
		Button minesweeperStart = new Button("Minesweeper");
		minesweeperStart.setLayoutX(100);
		minesweeperStart.setLayoutY(75);
		
		//Reversi button
		Button reversiStart = new Button("Reversi");
		reversiStart.setLayoutX(210);
		reversiStart.setLayoutY(75);
		
		root.getChildren().add(minesweeperStart);
		root.getChildren().add(reversiStart);
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		minesweeperStart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				minesweeper = true;
				reversi = false;
				startMinesweeper(primaryStage);
			}
		});
		reversiStart.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e) {
				minesweeper = false;
				reversi = true;
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
				if (curGame.getState() == 1) {
					System.out.println("You won!");
					stop();
					//TODO: Add return to home screen code here
				} else if (curGame.getState() == -1) {
					System.out.println("You lost...");
					stop();
					//TODO: Add return to home screen code here
				}
				
//				root.getChildren().remove(root.getChildren().size()-1);
				((MinesweeperGame) curGame).gameBoard.render(root);
			}
		}.start();
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


	public static void main(String[] args) {
		launch(args);
	}
}

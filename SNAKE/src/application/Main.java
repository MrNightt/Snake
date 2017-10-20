package application;
	
import com.test.snake.controller.GameController;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
		
	public static Scene sceneMenu;
	
	public static Scene playScene;
	
	public static Scene scoreboardScene;
	
	public static Stage stage;
		
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			FXMLLoader loaderMenu = new FXMLLoader();
			FXMLLoader scoreboardloader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("../com/test/snake/views/Snake.fxml"));
			loaderMenu.setLocation(Main.class.getResource("../com/test/snake/views/Menu.fxml"));
			scoreboardloader.setLocation(Main.class.getResource("../com/test/snake/views/Scoreboard.fxml"));

			playScene = new Scene(loader.load(),600,480);
			sceneMenu = new Scene(loaderMenu.load());
			scoreboardScene = new Scene(scoreboardloader.load());
			
			primaryStage.setScene(sceneMenu);
			primaryStage.show();
			stage = primaryStage;
			
			scoreboardScene.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					changeScene("menu");
					
				}
				
			}
			);
			
			//Receives user commands to control snakes direction
			playScene.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.W && GameController.snake.getDirection() != "s")
					GameController.snake.setDirection("w");
				
				if (event.getCode() == KeyCode.S && GameController.snake.getDirection() != "w")
					GameController.snake.setDirection("s");

				if (event.getCode() == KeyCode.A  && GameController.snake.getDirection() != "d")
					GameController.snake.setDirection("a");

				if (event.getCode() == KeyCode.D  && GameController.snake.getDirection() != "a")
					GameController.snake.setDirection("d");
				
				if(event.getCode() == KeyCode.ESCAPE) {
					GameController.pausePlay();
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**Changes the Scene of the Stage
	 * 
	 */
	public static void changeScene(String scene) {
		
		switch(scene) {
		case "menu":
			stage.setScene(sceneMenu);
			break;
		case "play":
			stage.setScene(playScene);
			break;
		case "scoreboard":
			stage.setScene(scoreboardScene);
			break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

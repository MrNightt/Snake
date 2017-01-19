package com.test.snake.controller;

import java.util.Random;

import application.Food;
import application.Main;
import application.Position;
import application.Snake;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameController extends AnimationTimer {

	@FXML
	public Canvas canvas;

	public static int speed;

	public static Snake snake;

	private Position snakeHead;

	private static GraphicsContext gc;

	public static Timeline time;

	public Food food = new Food(0,0);
	public Food food1 = new Food(0,0);

	@FXML
	public void initialize() {

		speed = 250;

		time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);

		gc = canvas.getGraphicsContext2D();

		snakeHead = new Position(canvas.getWidth()/2,canvas.getHeight()/2);	

		snake = new Snake(13, canvas, snakeHead);

		start();
		
		snakeMove(speed);

	}

	/**Changes snakes body positions
	 * 
	 * 
	 * @param speed
	 */
	public void snakeMove(int speed) {

		time.getKeyFrames().add(new KeyFrame(Duration.millis(speed),
				(event)-> {

					switch (snake.getDirection()) {

					case "w":

						for (int i = snake.getBody().size()-1; i > 0; i--) {
							snake.getBody().get(i).setCoordY(snake.getBody().get(i-1).getCoordY());
							snake.getBody().get(i).setCoordX(snake.getBody().get(i-1).getCoordX());
						}

						//Changes the position of snake head
						snake.getBody().get(0).setCoordY(snake.getBody().get(0).getCoordY()-15);
						break;

					case "s":
						for (int i = snake.getBody().size()-1; i > 0; i--) {
							snake.getBody().get(i).setCoordY(snake.getBody().get(i-1).getCoordY());
							snake.getBody().get(i).setCoordX(snake.getBody().get(i-1).getCoordX());
						}

						//Changes the position of snake head
						snake.getBody().get(0).setCoordY(snake.getBody().get(0).getCoordY()+15);
						break;

					case "a":

						for (int i = snake.getBody().size()-1; i > 0; i--) {
							snake.getBody().get(i).setCoordY(snake.getBody().get(i-1).getCoordY());
							snake.getBody().get(i).setCoordX(snake.getBody().get(i-1).getCoordX());
						}

						//Changes the position of snake head
						snake.getBody().get(0).setCoordX(snake.getBody().get(0).getCoordX()-15);
						break;

					case "d":

						for (int i = snake.getBody().size()-1; i > 0; i--) {
							snake.getBody().get(i).setCoordY(snake.getBody().get(i-1).getCoordY());
							snake.getBody().get(i).setCoordX(snake.getBody().get(i-1).getCoordX());
						}

						//Changes the position of snake head
						snake.getBody().get(0).setCoordX(snake.getBody().get(0).getCoordX()+15);
						break;

					}			
					
					
					//Transfers snakes head to the opposite border of the border that the snakes head collides
					if (snake.getBody().get(0).getCoordX() < 0) {
						
						snake.getBody().get(0).setCoordX(canvas.getWidth()- 15);
						
					} else if (snake.getBody().get(0).getCoordX() >= canvas.getWidth()) {
						
						snake.getBody().get(0).setCoordX(0);
						
					} else if (snake.getBody().get(0).getCoordY() < 0) {
						
						snake.getBody().get(0).setCoordY(canvas.getHeight() - 15);
						
					} else if (snake.getBody().get(0).getCoordY() >= canvas.getHeight()) {
						
						snake.getBody().get(0).setCoordY(0);
						
					}
					

					//Verifies if snake is colliding with it self
					for (int i = 1; i < snake.getBody().size(); i++) {

						if (snake.getBody().get(i).getCoordX() == snake.getBody().get(0).getCoordX() &&
								snake.getBody().get(i).getCoordY() == snake.getBody().get(0).getCoordY()) {
							time.stop();
							initialize();
							Main.changeScene();
						}
					}

				}));
	}

	/**
	 * Visual representation of the snake
	 */
	public void drawSnake() {

		gc.setFill(Color.WHITE);

		for (Position pos : snake.getBody()) {

			gc.fillRect(pos.getCoordX(), pos.getCoordY(), 10, 10);

		}

	}
	
	/**
	 * Visual representation of the food
	 */
	public void drawFood(Food food) {

		gc.setFill(Color.WHITE);

		//Generates a new position for the food inside the canvas
		if (food.isEated()) {

			food.setCoordX(new Random().nextInt(600));
			food.setCoordY(new Random().nextInt(450));

			while ( food.getCoordX() % 15 != 0 ) {

				if (food.getCoordX() < 585) {

					food.setCoordX( food.getCoordX()+1 );

				} else {

					food.setCoordX( food.getCoordX()-1 );
				}

			}

			while ( food.getCoordY() % 15 != 0) {

				if (food.getCoordY() < 435) {

					food.setCoordY( food.getCoordY()+1 );

				} else {

					food.setCoordY( food.getCoordY()-1 );
				}
			}

			food.setEated(false);
		}

		//The food is eaten if the snakes head collides with the food
		if (snake.getBody().get(0).getCoordX() == food.getCoordX() && 
				snake.getBody().get(0).getCoordY() == food.getCoordY()) {

			snake.getBody().add(new Position(snake.getBody().get(snake.getBody().size()-1).getCoordX(),
					snake.getBody().get(snake.getBody().size()-1).getCoordY()));

			food.setEated(true);
		}

		gc.fillRect(food.getCoordX() , food.getCoordY(), 10, 10);

	}

	/**Draws canvas background
	 * 
	 */
	public void drawBG() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	/**Updates the canvas 60 times per second 
	 * 
	 */
	@Override
	public void handle(long arg0) {

		drawBG();
		drawSnake();
		drawFood(food);
		drawFood(food1);

		if (snake.getBody().size() == 5 && speed == 250) {

			speed = 150; time.stop(); time.getKeyFrames().clear(); snakeMove(speed); time.play();

		}

		if (snake.getBody().size() == 10 && speed == 150) {

			speed = 50; time.stop(); time.getKeyFrames().clear(); snakeMove(speed); time.play();


		}

	}

}

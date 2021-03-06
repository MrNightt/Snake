package com.test.snake.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import application.Food;
import application.Main;
import application.Position;
import application.Snake;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameController extends AnimationTimer {

	@FXML
	public Canvas canvas;

	@FXML
	public Canvas score;

	public static int speed;

	public static Snake snake;

	private Position snakeHead;

	public static GraphicsContext gc;

	private static GraphicsContext sgc;

	public static Timeline time;

	Color random;

	public Food[] food;
	
	public Font theFont;

	@FXML
	public void initialize() {

		//Number of food blocks
		food = new Food[4];

		for(int i = 0; i < food.length; i++) {
			food[i] = new Food(0,0);
		}

		random = Color.WHITE;

		speed = 250;

		time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);

		gc = canvas.getGraphicsContext2D();

		sgc = score.getGraphicsContext2D();

		theFont = Font.font("Times New Roman", FontWeight.BOLD, 18);
		sgc.setFont(theFont);

		snakeHead = new Position(canvas.getWidth() / 2, canvas.getHeight() / 2);

		snake = new Snake(3, canvas, snakeHead);

		start();

		snakeMove(speed);

	}

	/**
	 * Changes snakes body positions
	 * 
	 * 
	 * @param speed
	 */
	public void snakeMove(int speed) {

		time.getKeyFrames().add(new KeyFrame(Duration.millis(speed), (event) -> {

			switch (snake.getDirection()) {

			case "w":

				for (int i = snake.getBody().size() - 1; i > 0; i--) {
					snake.getBody().get(i).setCoordY(snake.getBody().get(i - 1).getCoordY());
					snake.getBody().get(i).setCoordX(snake.getBody().get(i - 1).getCoordX());
				}

				// Changes the position of snake head
				snake.getBody().get(0).setCoordY(snake.getBody().get(0).getCoordY() - 15);
				break;

			case "s":
				for (int i = snake.getBody().size() - 1; i > 0; i--) {
					snake.getBody().get(i).setCoordY(snake.getBody().get(i - 1).getCoordY());
					snake.getBody().get(i).setCoordX(snake.getBody().get(i - 1).getCoordX());
				}

				// Changes the position of snake head
				snake.getBody().get(0).setCoordY(snake.getBody().get(0).getCoordY() + 15);
				break;

			case "a":

				for (int i = snake.getBody().size() - 1; i > 0; i--) {
					snake.getBody().get(i).setCoordY(snake.getBody().get(i - 1).getCoordY());
					snake.getBody().get(i).setCoordX(snake.getBody().get(i - 1).getCoordX());
				}

				// Changes the position of snake head
				snake.getBody().get(0).setCoordX(snake.getBody().get(0).getCoordX() - 15);
				break;

			case "d":

				for (int i = snake.getBody().size() - 1; i > 0; i--) {
					snake.getBody().get(i).setCoordY(snake.getBody().get(i - 1).getCoordY());
					snake.getBody().get(i).setCoordX(snake.getBody().get(i - 1).getCoordX());
				}

				// Changes the position of snake head
				snake.getBody().get(0).setCoordX(snake.getBody().get(0).getCoordX() + 15);
				break;

			}

			// Transfers snakes head to the opposite border of the border that
			// the snakes head collides
			if (snake.getBody().get(0).getCoordX() < 0) {

				snake.getBody().get(0).setCoordX(canvas.getWidth() - 15);

			} else if (snake.getBody().get(0).getCoordX() >= canvas.getWidth()) {

				snake.getBody().get(0).setCoordX(0);

			} else if (snake.getBody().get(0).getCoordY() < 0) {

				snake.getBody().get(0).setCoordY(canvas.getHeight() - 15);

			} else if (snake.getBody().get(0).getCoordY() >= canvas.getHeight()) {

				snake.getBody().get(0).setCoordY(0);

			}

			// Verifies if snake is colliding with it self
			for (int i = 1; i < snake.getBody().size(); i++) {

				if (snake.getBody().get(i).getCoordX() == snake.getBody().get(0).getCoordX()
						&& snake.getBody().get(i).getCoordY() == snake.getBody().get(0).getCoordY()) {

					updateScoreboard();

					//Resets game
					time.stop();
					initialize();
					Main.changeScene("menu");
				}
			}

		}));
	}

	//Updates the scoreboard file
	public void updateScoreboard() {
		try {
			File scoreboard = new File("src/resouces/scoreboard.txt");
			PrintWriter score = new PrintWriter(scoreboard);
			Scanner sc = new Scanner(scoreboard);
			if(sc.hasNext()) {
				if(sc.nextInt() <= snake.getBody().size()) {
					System.out.print("pass");
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					score.write(snake.getBody().size() + " " + dateFormat.format(date));
					score.close();
				}
			} else {
				System.out.print("pass on else");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				score.write(snake.getBody().size() + " " + dateFormat.format(date));
				score.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Visual representation of the snake
	 */
	public void drawSnake() {


		int section = 0;
		random = Color.GREEN;
		for (Position pos : snake.getBody()) {

			switch(section) {
			case 2:
				random = Color.RED;
				break;
			case 4:
				random = Color.GREEN;
				break;
			case 8:
				random = Color.ORANGERED;
				break;
			case 16:
				random = Color.ORANGE;
				break;
			case 32:
				random = Color.BLUE;
			}

			gc.setFill(random);

			section++;
			gc.fillRect(pos.getCoordX(), pos.getCoordY(), 10, 10);

		}

	}

	/**
	 * Visual representation of the food
	 */
	public void drawFood(Food food) {

		gc.setFill(Color.WHITE);

		// Generates a new position for the food inside the canvas
		if (food.isEated()) {

			food.setCoordX(new Random().nextInt(600));
			food.setCoordY(new Random().nextInt(450));

			while (food.getCoordX() % 15 != 0) {

				if (food.getCoordX() < 585) {

					food.setCoordX(food.getCoordX() + 1);

				} else {

					food.setCoordX(food.getCoordX() - 1);
				}

			}

			while (food.getCoordY() % 15 != 0) {

				if (food.getCoordY() < 435) {

					food.setCoordY(food.getCoordY() + 1);

				} else {

					food.setCoordY(food.getCoordY() - 1);
				}
			}

			food.setEated(false);
		}

		// The food is eaten if the snakes head collides with the food
		if (snake.getBody().get(0).getCoordX() == food.getCoordX()
				&& snake.getBody().get(0).getCoordY() == food.getCoordY()) {

			snake.getBody().add(new Position(snake.getBody().get(snake.getBody().size() - 1).getCoordX(),
					snake.getBody().get(snake.getBody().size() - 1).getCoordY()));

			food.setEated(true);
		}

		gc.fillRect(food.getCoordX(), food.getCoordY(), 10, 10);

	}

	public void drawScore() {
		sgc.setFill(Color.WHITE);
		sgc.setTextBaseline(VPos.CENTER);
		sgc.setTextAlign(TextAlignment.CENTER);
		sgc.fillText("Score: " + snake.getBody().size(), (score.getWidth() / 2), score.getHeight() / 2 );
	}

	/**
	 * Draws canvas background
	 * 
	 */
	public void drawBG() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.WHITE);
		gc.strokeLine(0, canvas.getHeight(), canvas.getWidth(), canvas.getHeight());
		sgc.setFill(Color.BLACK);
		sgc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

	}

	public static void pausePlay() {
		if(time.getStatus() == Animation.Status.RUNNING){
			time.pause();
		} else {
			time.play();
		}
	}

	/**
	 * Updates the canvas 60 times per second
	 * 
	 */
	@Override
	public void handle(long arg0) {

		drawBG();
		drawSnake();
		for(int i = 0; i < food.length; i++) {
			drawFood(food[i]);
		}
		
		drawScore();
		
		if(time.getStatus() == Animation.Status.PAUSED) {
			
			theFont = Font.font(47);
			gc.setFont(theFont);
			gc.setFill(Color.WHITE);
			gc.fillText("PAUSED", 100, 100);
		}

		//GAME SPEED - ENTRY
		if (snake.getBody().size() == 5 && speed == 250) {

			speed = 150;
			time.stop();
			time.getKeyFrames().clear();
			snakeMove(speed);
			time.play();

		}

		if (snake.getBody().size() == 10 && speed == 150) {

			speed = 100;
			time.stop();
			time.getKeyFrames().clear();
			snakeMove(speed);
			time.play();

		}
		//GAME SPEED END

	}

}

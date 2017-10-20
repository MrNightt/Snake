package com.test.snake.controller;

import java.io.FileNotFoundException;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MenuController {
	
	@FXML
	public Canvas canvasMenu;
	
	private static GraphicsContext gc;
	
	@FXML
	public void initialize() {
		
		gc = canvasMenu.getGraphicsContext2D();
		drawBG();
		
		gc.setFill(Color.WHITE);
		
		gc.fillRect( (canvasMenu.getWidth()/2)-125, (canvasMenu.getHeight()/2)-100, 250, 50);
		gc.fillRect( (canvasMenu.getWidth()/2)-125, (canvasMenu.getHeight()/2), 250, 50);
		gc.fillRect( (canvasMenu.getWidth()/2)-125, (canvasMenu.getHeight()/2)+100, 250, 50);
		
		Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
		
		gc.setFont(theFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("Snake Game", (canvasMenu.getWidth()/2), 60);
		
		theFont = Font.font( "Times New Roman", FontWeight.BOLD, 28 );
		gc.setFont(theFont);
		
		gc.setFill(Color.BLACK);
		gc.fillText("START",canvasMenu.getWidth()/2 , (canvasMenu.getHeight()/2)-75);
		gc.fillText("SCOREBOARD",canvasMenu.getWidth()/2 , (canvasMenu.getHeight()/2)+25);
		gc.fillText("EXIT",canvasMenu.getWidth()/2 ,(canvasMenu.getHeight()/2)+125);
		
		canvasMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			       new EventHandler<MouseEvent>() {
			           @Override
			           public void handle(MouseEvent e) {
			               if (e.getX() > (canvasMenu.getWidth()/2)-125 && e.getX() < (canvasMenu.getWidth()/2)+125 && e.getY() > (canvasMenu.getHeight()/2)-100 && e.getY() < (canvasMenu.getHeight()/2)-50) {
			            	   Main.changeScene("play");
			            	   GameController.time.play();
			               }
			               
			               if (e.getX() > (canvasMenu.getWidth()/2)-125 && e.getX() < (canvasMenu.getWidth()/2)+125 && e.getY() > (canvasMenu.getHeight()/2) && e.getY() < (canvasMenu.getHeight()/2)+50) {
			            	   Main.changeScene("scoreboard");
			               }
			               
			               if (e.getX() > (canvasMenu.getWidth()/2)-125 && e.getX() < (canvasMenu.getWidth()/2)+125 && e.getY() > (canvasMenu.getHeight()/2)+100 && e.getY() < (canvasMenu.getHeight()/2)+150) {
			            	   Main.stage.close();
			               }
			           }
			       });

		
	}
	
	/**Draws the background
	 * 
	 */
	public void drawBG() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvasMenu.getWidth(), canvasMenu.getHeight());
	}
	

}

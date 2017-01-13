package com.test.snake.controller;

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
		
		gc.fillRect( (canvasMenu.getWidth()/2)-125, (canvasMenu.getHeight()/2)-100, 250, 100);
		gc.fillRect( (canvasMenu.getWidth()/2)-125, (canvasMenu.getHeight()/2)+50, 250, 100);
		
		Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
		
		gc.setFont(theFont);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("Snake Game", (canvasMenu.getWidth()/2), 60);
		
		gc.setFill(Color.BLACK);
		gc.fillText("Start",canvasMenu.getWidth()/2 , 170);
		gc.fillText("Exit",canvasMenu.getWidth()/2 , 325);
		
		canvasMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			       new EventHandler<MouseEvent>() {
			           @Override
			           public void handle(MouseEvent e) {
			               if (e.getX() > (canvasMenu.getWidth()/2)-125 && e.getX() < (canvasMenu.getWidth()/2)+125 && e.getY() > (canvasMenu.getHeight()/2)-100 && e.getY() < canvasMenu.getHeight()/2) {
			            	   Main.changeScene();
			            	   GameController.time.play();
			               }
			               
			               if (e.getX() > (canvasMenu.getWidth()/2)-125 && e.getX() < (canvasMenu.getWidth()/2)+125 && e.getY() > (canvasMenu.getHeight()/2)+50 && e.getY() < (canvasMenu.getHeight()/2)+150) {
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

package application;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

public class Snake {

	private ArrayList<Position> body;
	public String direction = "d";

	public Snake(int size, Canvas canvas, Position start) {

		body = new ArrayList<Position>(size);

		for (int i = 0 ; i < size ; i++) {

			Position parts = new Position(0,0);
			parts.setCoordY(start.getCoordY());
			parts.setCoordX(start.getCoordX()-(i*15));
			body.add(parts);

		}

	}

	public ArrayList<Position> getBody() {
		return body;
	}

	public void setBody(ArrayList<Position> body) {
		this.body = body;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}

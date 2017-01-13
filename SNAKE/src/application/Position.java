package application;

public class Position {

	private double coordX, coordY;
			
	public Position(double coordX, double coordY) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public double getCoordX() {
		return coordX;
	}
	
	public double getCoordY(){
		return coordY;
	}

	public void setCoordY(double y){
		coordY = y;
	}
	
	public void setCoordX(double x){
		coordX = x;
	}
	
	
}

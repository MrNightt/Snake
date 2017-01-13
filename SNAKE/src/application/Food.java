package application;

public class Food extends Position {

	private boolean eated = true;
	
	public Food(double coordX, double coordY) {
		super(coordX, coordY);
	}

	public boolean isEated() {
		return eated;
	}

	public void setEated(boolean eated) {
		this.eated = eated;
	}

}

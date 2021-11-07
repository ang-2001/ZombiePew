package ZombiesGame.model;

public class Zombie {
	private boolean hasAttackedPlayer = false;
	private boolean isDead = false;
	private double xPos = 0.0;
	private double yPos = 0.0;
	
	public Zombie(double xPos, double yPos)
	{
	    super();
	    this.xPos = xPos;
	    this.yPos = yPos; 
	}
	
	public double getxPos() {
	    return xPos;
	}

	public void setxPos(double xPos) {
	    this.xPos = xPos;
	}

	public double getyPos() {
	    return yPos;
	}

	public void setyPos(double yPos) {
	    this.yPos = yPos;
	}
	
	public void move() {
		//code here
	}
	
	public void die() {
		isDead = true;
	}
	
	public void hit() {
		//code here
	}
	
	public boolean getStatus() {
		//code here
		return false;
	}

}

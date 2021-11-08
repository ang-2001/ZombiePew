package ZombiesGame.model;

public class Zombie {
	private boolean hasAttackedPlayer = false;
	private boolean isDead = false;
	private int x;
	private int y;

	public Zombie(int x, int y)
	{
	    super();
	    this.x = x;
	    this.y = y;
	}
	
	public int getXPos()
	{
	    return x;
	}

	public void setXPos(int x)
	{
	    this.x = x;
	}

	public int getYPos()
	{
	    return y;
	}

	public void setYPos(int y)
	{
	    this.y = y;
	}
	
	public void move()
	{
		//code here
	}
	
	public void die() {
		isDead = true;
	}
	
	public void hit()
	{
		//code here
	}
	
	public boolean getStatus()
	{
		//code here
		return false;
	}

}

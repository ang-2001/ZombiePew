package ZombiesGame.model;

import java.awt.*;

public abstract class Entity {
	// positions and states
	protected boolean isColliding;
	private boolean isActive;
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;


	public Entity(int x, int y)
	{
		isColliding = false;
		isActive = true;
		this.x  = x;
		this.y  = y;
	}


	public void translate()
	{
		 x += dx;
		 y += dy;
	}


	public void setInactive()
	{
		isActive = false;
	}

	/**
	 * returns the x component of entity position
	 * @return x component of position
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * returns y component of entity positions
	 * @return y component of position
	 */
	public int getY()
	{
		return y;
	}

	public boolean isActive()
	{
		return this.isActive;
	}

	public abstract Rectangle getHitBox();

	public abstract boolean collidesWith(Entity e);
}

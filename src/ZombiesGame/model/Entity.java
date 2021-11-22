package ZombiesGame.model;

import java.awt.*;

public abstract class Entity {
	// positions and states
	private String status;
	protected int x;
	protected int y;


	/**
	 * translates position of entity by set amount defined by dx and dy
	 * @param dx total change in x performed in one translate call
	 * @param dy total change in y performed in one translate call
	 */
	public void translate(int dx, int dy)
	{
		x += dx;
		y += dy;
	}


	/**
	 * does nothing -- to be overriden by classes that need to use it(Zombie,Projectile)
	 * otherwise, useless
	 */
	public void translate()
	{
		// does nothing -- to be overriden by classes that need to implement it
	}


	/**
	 *
	 * @param status state of the entity(dead or alive)
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	public abstract Rectangle getHitBox();


	/**
	 * this method returns the x component of entity position
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


	/**
	 * gets the status of entity
	 * @return status of entity (dead or alive)
	 */
	public String getStatus()
	{
		return status;
	}
}

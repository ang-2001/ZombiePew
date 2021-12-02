package ZombiesGame.model;

import java.awt.*;

public class Enemy extends Entity
{

	public Enemy(int x, int y)
	{
		super(x, y);
	}


	@Override
	public Rectangle getHitBox()
	{
		int hitboxWidth 	= 64;
		int hitboxHeight 	= 64;

		// creates hit box that should be in the center of the enemy;
		return new Rectangle(x, y, hitboxWidth, hitboxHeight);
	}


	@Override
	public boolean collidesWith(Entity e)
	{
		Class type = e.getClass();
		boolean hasCollision = getHitBox().intersects(e.getHitBox()) && this != e;

		if ((type == Player.class || type == Enemy.class || type == Projectile.class) && hasCollision)
		{
			return true;
		}

		return false;
	}
}

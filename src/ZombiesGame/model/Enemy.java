package ZombiesGame.model;

import java.awt.*;

public class Enemy extends Entity
{

	public Enemy(int x, int y)
	{
		this.x = x;
		this.y = y;
	}


	@Override
	public Rectangle getHitBox()
	{
		int hitboxWidth 	= 48;
		int hitboxHeight 	= 48;

		// creates hit box that should be in the center of the enemy;
		return new Rectangle(x + hitboxWidth/2, y + hitboxHeight/2, hitboxWidth, hitboxWidth);
	}
}

package ZombiesGame.model;

import java.awt.*;

public class Projectile extends Entity
{
    /**
     *
     * @param e
     * @param
     * @param
     */
    public Projectile(Entity e, int dx, int dy)
    {
        this.x = e.x;
        this.y = e.y;

        this.dx = dx;
        this.dy = dy;
    }


    /**
     *
     * @return
     */
    public Rectangle getHitBox()
    {
        int hitboxWidth = 32;
        int hitboxHeight = 32;

        return new Rectangle(x + hitboxWidth/2, y + hitboxHeight/2, hitboxWidth, hitboxWidth);
    }
}

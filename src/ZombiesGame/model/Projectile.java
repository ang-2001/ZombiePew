package ZombiesGame.model;

import java.awt.*;

public class Projectile extends Entity
{
    // delta x and delta y of mouse and player pos. not the same as dx and dy
    private int deltaX;
    private int deltaY;

    private final int HITBOX_WIDTH = 64;
    private final int HITBOX_HEIGHT = 64;


    /**
     *
     * @param e
     * @param deltaX
     * @param deltaY
     */
    public Projectile(Entity e, int deltaX, int deltaY)
    {
        this.x = e.x;
        this.y = e.y;

        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }


    /**
     *
     */
    public void translate()
    {
        // coefficent k determines magnitude of movement per call to translate
        // theta is angle between mouse and player, converted to radians
        int k         = 25;
        double theta  = Math.toDegrees(Math.atan2(deltaY, deltaX));
        theta         = Math.toRadians(theta);

        // change in x and change in y per call to translate
        double dx   = k * Math.cos(theta);
        double dy   = k * Math.sin(theta);

        translate((int) dx, (int) dy);
    }


    /**
     *
     * @return
     */
    public Rectangle getHitBox()
    {
        return new Rectangle(getX(), getY(), HITBOX_WIDTH, HITBOX_HEIGHT);
    }
}

package ZombiesGame.model;

import java.awt.*;

public class Player extends Entity {

    public Player(int x, int y) {
        super(x, y);
    }


    /**
     * gets player hitbox for collision checks, defined by rectangle
     * @return Rectangle, created at player position, with height and width 3
     */
    @Override
    public Rectangle getHitBox()
    {
        int hitboxWidth = 64;
        int hitboxHeight = 64;

        return new Rectangle(x, y, hitboxWidth, hitboxHeight);
    }


    @Override
    public boolean collidesWith(Entity e)
    {
        Class type = e.getClass();
        boolean hasCollision = getHitBox().intersects(e.getHitBox()) && this != e;

        if ((type == Enemy.class || type == Item.class) && hasCollision)
        {
            return true;
        }
        return false;
    }
}

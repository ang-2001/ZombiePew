package ZombiesGame.model;

import java.awt.*;

public class Item extends Entity
{
    public Item(int x, int y) {
        super(x, y);
    }


    public Item (Entity e)
    {
        super(e.x, e.y);
    }


    @Override
    public Rectangle getHitBox()
    {
        int hitboxHeight = 64;
        int hitboxWidth = 64;

        return new Rectangle(x, y, hitboxWidth, hitboxHeight);
    }


    @Override
    public boolean collidesWith(Entity e)
    {
        return false;
    }
}

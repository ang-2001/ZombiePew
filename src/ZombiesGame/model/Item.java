package ZombiesGame.model;

import java.awt.*;
import java.util.Random;

public class Item extends Entity
{

    Random r = new Random();
    private int type = 0;
    public Item(int x, int y) {
        super(x, y);
    }


    public Item (Entity e)
    {
        super(e.x, e.y);
        type = r.nextInt(3);
    }

    public int getType(){
        return type;
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

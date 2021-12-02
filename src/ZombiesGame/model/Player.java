package ZombiesGame.model;

import java.awt.*;

public class Player extends Entity {

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * gets player hitbox for collision checks, defined by rectangle
     * @return Rectangle, created at player position, with height and width 3
     */
    @Override
    public Rectangle getHitBox()
    {
        int hitboxWidth = 48;
        int hitboxHeight = 48;

        return new Rectangle(x + hitboxWidth/2, y + hitboxHeight/2, hitboxWidth, hitboxWidth);
    }
}

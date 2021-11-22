package ZombiesGame.model;

import java.awt.*;

public class Player extends Entity {
    private final int HITBOX_WIDTH = 32;
    private final int HITBOX_HEIGHT = 32;


    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * gets player hitbox for collision checks, defined by rectangle
     * @return Rectangle, created at player position, with height and width 3
     */
    @Override
    public Rectangle getHitBox() {
        return new Rectangle(getX(), getY(), HITBOX_WIDTH, HITBOX_HEIGHT);
    }
}

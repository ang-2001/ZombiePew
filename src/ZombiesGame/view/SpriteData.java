package ZombiesGame.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SpriteData
{
    // stores all sprite buffered images, passes them to the view when we need to draw them -> how do we do zombie direction?

    BufferedImage playerUp, playerDown, playerLeft, playerRight;
    BufferedImage projectile;
    BufferedImage background;
    BufferedImage enemy;
    BufferedImage coffee;
    BufferedImage mushroom;
    BufferedImage chicken;

    public SpriteData()  {
        try
        {
            playerUp = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/player_up.png"));
            playerDown = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/player_down.png"));
            playerLeft = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/player_left.png"));
            playerRight = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/player_right.png"));

            projectile = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/projectile.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/background_2.png"));
            enemy = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/zombie.png"));


            coffee = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/item_1.png"));
            mushroom = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/mushroom.png"));
            chicken = ImageIO.read(getClass().getResourceAsStream("/ZombiesGame/sprites/chicken.png"));

        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
    }

    public BufferedImage getPlayerSprite()
    {
        ActionTracker keysPressed = ActionTracker.getInstance();

        if (keysPressed.isDown())
        {
            return playerDown;
        }
        else if (keysPressed.isLeft())
        {
            return playerLeft;
        }
        else if (keysPressed.isRight())
        {
            return playerRight;
        }
        else
        {
            return playerUp;
        }
    }

    public BufferedImage getProjectileSprite()
    {
        return projectile;
    }

    public BufferedImage getEnemySprite() {
        return enemy;
    }

    public BufferedImage getCoffeeSprite()
    {
        return coffee;
    }

    public BufferedImage getMushroomSprite(){
        return mushroom;
    }

    public BufferedImage getChickenSprite(){
        return chicken;
    }


    public BufferedImage getBackground()
    {
        return background;
    }
}

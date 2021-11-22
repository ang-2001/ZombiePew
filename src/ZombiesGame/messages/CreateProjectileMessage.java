package ZombiesGame.messages;

import java.awt.*;

public class CreateProjectileMessage implements Message
{
    private Point mousePosition;

    public CreateProjectileMessage(Point mousePosition)
    {
        this.mousePosition = mousePosition;

//        System.out.println( mousePosition + "  " + playerPosition);
    }

    public Point getMousePosition()
    {
        return mousePosition;
    }
}
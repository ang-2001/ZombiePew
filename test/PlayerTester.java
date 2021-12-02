import ZombiesGame.model.Player;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTester
{
    @Test
    public void testHitBox()
    {
        Player p = new Player(100, 100);

        Rectangle hitbox = p.getHitBox();

        boolean equalPosition = (p.getX() + hitbox.width/2 == hitbox.x) && (p.getY() + hitbox.height/2 == hitbox.y);
        boolean equalDimensions = (hitbox.width == 48) && (hitbox.height == 48);

        assertTrue(equalPosition && equalDimensions,
                "expected output was true, actual value was false, positions not equal or hitbox not correct dimensions");
    }
}

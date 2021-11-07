package ZombiesGame;

import ZombiesGame.view.GameWindow;
import ZombiesGame.view.View;

public class PewGame
{
    public static void main(String[] args)
    {
        View panel = new View(16, 12);
        GameWindow window = new GameWindow("Pew Game", panel);
    }
}

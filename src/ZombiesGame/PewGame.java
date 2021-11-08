package ZombiesGame;

import ZombiesGame.view.GameWindow;
import ZombiesGame.view.View;

public class PewGame
{
    public static void main(String[] args)
    {
        View view = new View();
        GameWindow window = new GameWindow("Pew Game", view);
    }
}

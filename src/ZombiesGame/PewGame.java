package ZombiesGame;

import ZombiesGame.view.GameWindow;
import ZombiesGame.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PewGame
{
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

    public static void main(String[] args)
    {
        View panel = new View(16, 12);
        GameWindow window = new GameWindow("Pew Game", panel);
    }
}

package ZombiesGame;

import ZombiesGame.messages.Message;
import ZombiesGame.view.GameWindow;
import ZombiesGame.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PewGame
{
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

    public static void main(String[] args)
    {
        View view = new View();
        GameWindow window = new GameWindow("Pew Game", view);
    }
}

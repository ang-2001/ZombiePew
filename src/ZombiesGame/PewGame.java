package ZombiesGame;

import ZombiesGame.controller.Controller;
import ZombiesGame.model.Model;
import ZombiesGame.view.GameWindow;
import ZombiesGame.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PewGame
{
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
    private static Controller controller;
    private static Model model;
    private static View view;

    public static void main(String[] args)
    {
        model = new Model();
        view = new View();
        controller = new Controller(queue, model, view);

        GameWindow window = new GameWindow("Pew Game", view);
    }
}

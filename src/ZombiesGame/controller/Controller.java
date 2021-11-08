package ZombiesGame.controller;

import ZombiesGame.Message;
import ZombiesGame.model.Model;
import ZombiesGame.view.View;

import java.util.concurrent.BlockingQueue;

public class Controller
{
    private BlockingQueue<Message> queue;
    private Model model;
    private View view;

    
    // add constructor arguments as needed
    //queue, model, view
    public Controller(BlockingQueue<Message> queue, Model model, View view)
    {
        this.queue  = queue;
        this.model  = model;
        this.view   = view;
    }


    public void mainLoop() throws Exception
    {

    }
}

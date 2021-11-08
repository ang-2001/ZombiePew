package ZombiesGame.controller;

import ZombiesGame.view.View;

import java.util.concurrent.BlockingQueue;

public class Controller
{
//    private BlockingQueue<Message> queue;
//    private Model model;
    private View view;

    
    // add constructor arguments as needed
    //queue, model, view
    public Controller(View view)
    {

        this.view = view;
    }


    public void mainLoop() throws Exception
    {

    }
}

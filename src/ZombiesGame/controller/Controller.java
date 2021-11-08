package ZombiesGame.controller;

import ZombiesGame.Message;
import ZombiesGame.model.Model;
import ZombiesGame.view.View;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller
{
    private BlockingQueue<Message> queue;
    private Model model;
    private View view;
    private List<Valve> valves = new LinkedList<>();
    
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
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH){
            try {
                message = queue.take();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }
}

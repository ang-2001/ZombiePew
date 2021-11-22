package ZombiesGame.controller;

import ZombiesGame.messages.ChangeGameStateMessage;
import ZombiesGame.messages.Message;
import ZombiesGame.messages.NewGameMessage;
import ZombiesGame.messages.UpdatePlayerMessage;
import ZombiesGame.model.Model;
import ZombiesGame.view.ActionTracker;
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

        valves.add(new NewGameValve());
        valves.add(new UpdatePlayerValve());
        valves.add(new ChangeGameStateValve());
    }


    public void mainLoop() throws Exception
    {
        ValveResponse response = ValveResponse.EXECUTED;

        Message message = null;
        while (response != ValveResponse.FINISH)
        {
            try
            {
                message = queue.take();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            for (Valve valve : valves)
            {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS)
                    break;
            }
        }
    }


    private class NewGameValve implements Valve
    {
        /**
         * resets game state and gamedata, initializes start position of player
         * @param message
         * @return
         */
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != NewGameMessage.class)
            {
                return ValveResponse.MISS;
            }

            NewGameMessage m = (NewGameMessage) message;

            // reset game state
            model.createNewGame();

            // sets player position relative to screen height and width( should be near centered)

            model.createPlayer(m.getWidth(), m.getHeight(), m.getSpriteSize());

            // send render data to View
            GameInfo data = model.getGameStatus();
            view.updateView(data);

            return ValveResponse.EXECUTED;
        }
    }


    private class UpdatePlayerValve implements Valve
    {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != UpdatePlayerMessage.class)
            {
                return ValveResponse.MISS;
            }

            UpdatePlayerMessage m = (UpdatePlayerMessage) message;
            ActionTracker keysPressed = ActionTracker.getInstance();
            int displacement      = 5;

            if (keysPressed.isUp())
                model.updatePlayer(0, -displacement);
            if (keysPressed.isLeft())
                model.updatePlayer(-displacement, 0);
            if (keysPressed.isDown())
                model.updatePlayer(0,displacement);
            if (keysPressed.isRight())
                model.updatePlayer(displacement,0);

            // might need to change this,
            GameInfo data = model.getGameStatus();
            view.updateView(data);

            return ValveResponse.EXECUTED;
        }
    }

    private class ChangeGameStateValve implements Valve{

        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != ChangeGameStateMessage.class)
            {
                return ValveResponse.MISS;
            }

            ChangeGameStateMessage m = (ChangeGameStateMessage) message;
            if (m.getIsPressed()) {
                System.out.println("Change game state message sent!");
                view.changeLayout();
            }

            return ValveResponse.EXECUTED;
        }
    }
}

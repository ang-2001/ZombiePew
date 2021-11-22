package ZombiesGame.view;


import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.Message;
import ZombiesGame.messages.NewGameMessage;
import ZombiesGame.messages.UpdatePlayerMessage;
import ZombiesGame.model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel
{
    private BlockingQueue<Message> queue;
    private ActionTracker keysPressed;
    private Dimension dimensions;
    private int spriteSize;


    private LinkedList<Entity> entities;
    /**
     * constructor for GamePanel class that should initialize panelWidth and panelHeight,
     * which will be used to set up the preferred size of the panel
     */
    public GamePanel(BlockingQueue<Message> queue, int spriteSize, Dimension d)
    {
        this.dimensions = d;
        this.spriteSize = spriteSize;
        keysPressed     = ActionTracker.getInstance();
        this.queue      = queue;


        this.addKeyListener(new KeyHandler());

        // temporary placement of game initiation(to be moved to an actual button)
        try {
            queue.put(new NewGameMessage(dimensions.width, dimensions.height, spriteSize));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // timer that should handle all animations(movement)
        // defines delay in message generation for messages involved in animation = ~60 refreshes/sec
        int REFRESH_DELAY = 1000 / 60;

        Timer animationTimer = new Timer(REFRESH_DELAY, e -> {

            // all messages related to continual movement
            try {
                queue.put(new UpdatePlayerMessage());

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        animationTimer.start();

        this.setFocusable(true);
        this.setDoubleBuffered(true);
    }


    public void updateView(GameInfo info)
    {
        this.entities = info.getEntityInfo();
        repaint();
    }


    @Override
    public Dimension getPreferredSize()
    {
        return dimensions;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if(entities != null)
        {
            for(Entity e: entities)
            {
                g2.setColor(Color.BLACK);
                g2.fillRect(e.getX(), e.getY(), spriteSize, spriteSize);
            }
        }

        g2.dispose();
    }


    private class KeyHandler extends KeyAdapter
    {
        // will read key presses (when they are held down)
        @Override
        public void keyPressed(KeyEvent e)
        {
            int code = e.getKeyCode();

            if(code == KeyEvent.VK_W)
                keysPressed.setUp(true);
            if(code == KeyEvent.VK_A)
                keysPressed.setLeft(true);
            if(code == KeyEvent.VK_S)
                keysPressed.setDown(true);
            if(code == KeyEvent.VK_D)
                keysPressed.setRight(true);
        }


        // will read key presses (when they are released)
        @Override
        public void keyReleased(KeyEvent e)
        {
            int code = e.getKeyCode();

            if(code == KeyEvent.VK_W)
                keysPressed.setUp(false);
            if(code == KeyEvent.VK_A)
                keysPressed.setLeft(false);
            if(code == KeyEvent.VK_S)
                keysPressed.setDown(false);
            if(code == KeyEvent.VK_D)
                keysPressed.setRight(false);
        }
    }
}

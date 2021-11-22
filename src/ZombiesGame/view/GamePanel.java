package ZombiesGame.view;


import ZombiesGame.messages.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel
{
    private BlockingQueue<Message> queue;
    private Dimension dimensions;
    private int tileSize;

    /**
     * constructor for GamePanel class that should initialize panelWidth and panelHeight,
     * which will be used to set up the preferred size of the panel
     */
    public GamePanel(BlockingQueue<Message> queue, int tileSize, Dimension d)
    {
        this.dimensions = d;
        this.tileSize   = tileSize;
        this.queue      = queue;


        this.addKeyListener(new KeyHandler());

        this.setFocusable(true);
    }


    private class KeyHandler implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {
        }

        // will read key presses (when they are held down)
        @Override
        public void keyPressed(KeyEvent e)
        {

        }

        // will read key presses (when they are released)
        @Override
        public void keyReleased(KeyEvent e)
        {

        }
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
    }
}

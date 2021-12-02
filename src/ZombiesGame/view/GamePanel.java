package ZombiesGame.view;


import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.*;
import ZombiesGame.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class GamePanel extends JPanel
{
    private BlockingQueue<Message> queue;
    private ActionTracker keysPressed;
    private Dimension dimensions;
    private int spriteSize;

    private Timer animationTimer;
    private Timer projectileTimer;
    private Timer enemyTimer;

    private LinkedList<Entity> entities;
    private Point mousePosition;

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

        addMouseMotionListener(new MouseDraggedHandler());
        addMouseListener(new MouseHandler());
        addKeyListener(new KeyHandler());

        // defines delay in message generation for game updates = ~60 refreshes/sec
        int REFRESH_DELAY = 1000 / 60;

        // defines delay in message generation for projectile creation = ~5 projectiles/sec
        int FIRE_RATE_DELAY = 1000 / 5;

        // defines delay in message generation for enemy creation = ~2 enemies/sec
        int SPAWN_DELAY = 1000 / 10;

        // timer that should handle all animations(movement)
        animationTimer = new Timer(REFRESH_DELAY, e -> {
            try {
                queue.put(new UpdateEntitiesMessage());

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // timer for rate of projectile generation
        projectileTimer = new Timer(FIRE_RATE_DELAY, e -> {
            try {
                if (keysPressed.isClicked())
                    queue.put(new CreateProjectileMessage(mousePosition));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        // timer for rate of enemy creation
        enemyTimer = new Timer(SPAWN_DELAY, e -> {
            try {
                queue.put(new CreateEnemyMessage());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        this.setFocusable(true);
        this.setDoubleBuffered(true);
    }


    public void start()
    {
        // temporary placement of game initiation(to be moved to an actual button)
        try {
            queue.put(new NewGameMessage(dimensions.width, dimensions.height, spriteSize));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // add in another method later during screen switching
        animationTimer.start();
        projectileTimer.start();
        enemyTimer.start();
    }


    public void stop()
    {
        animationTimer.stop();
        projectileTimer.stop();
        enemyTimer.stop();
        keysPressed.reset();

    }
    /**
     *
     * @param info
     */
    public void updateView(GameInfo info)
    {
        this.entities = info.getEntityInfo();
        repaint();
    }

    /**
     *
     * @return
     */
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

        // these are just placeholders, will replace with sprites later
        if(entities != null)
        {
            for(Entity e: entities)
            {
                if (e.getClass() == Player.class)
                {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(e.getX(), e.getY(), spriteSize, spriteSize);
                }
                else if (e.getClass() == Projectile.class)
                {
                    g2.setColor(Color.BLACK);
                    g2.fillOval(e.getX(), e.getY(), spriteSize/4, spriteSize/4);
                }
                else if (e.getClass() == Enemy.class)
                {
                    g2.setColor(Color.RED);
                    g2.fillRect(e.getX(), e.getY(), spriteSize, spriteSize);
                }
                else if (e.getClass() == Item.class)
                {
                    g2.setColor(Color.GREEN);
                    g2.fillOval(e.getX(), e.getY(), spriteSize, spriteSize);
                }
            }
        }
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


    /**
     *
     */
    private class MouseHandler extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            //System.out.println("clicked at: " + mousePosition);
            mousePosition = e.getPoint();
            keysPressed.setClicked(true);
        }

        public void mouseReleased(MouseEvent e)
        {
            keysPressed.setClicked(false);
        }
    }

    /**
     *
     */
    private class MouseDraggedHandler extends MouseMotionAdapter
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            mousePosition = e.getPoint();
        }
    }
}

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
    private SpriteData spriteData;

    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private int score = 0;
    private int highScore = 0;

    /**
     * constructor for GamePanel class that should initialize panelWidth and panelHeight,
     * which will be used to set up the preferred size of the panel
     */
    public GamePanel(BlockingQueue<Message> queue, int spriteSize, Dimension d)
    {
        this.dimensions = d;
        this.spriteSize = spriteSize;
        keysPressed     = ActionTracker.getInstance();
        this.spriteData = new SpriteData();
        this.queue      = queue;

        addMouseMotionListener(new MouseDraggedHandler());
        addMouseListener(new MouseHandler());
        addKeyListener(new KeyHandler());

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        add(scoreLabel);

        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.BLACK);
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        add(highScoreLabel);

        // defines delay in message generation for game updates = ~60 refreshes/sec
        int REFRESH_DELAY = 1000 / 60;

        // defines delay in message generation for projectile creation = ~5 projectiles/sec
        int FIRE_RATE_DELAY = 1000 / 5;

        // defines delay in message generation for enemy creation = ~2 enemies/sec
        int SPAWN_DELAY = 1000 / 20;

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
        score = info.getScore();
        scoreLabel.setText("Score: " + score);
        highScore = info.getHighScore();
        highScoreLabel.setText("High score: " + highScore);
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

        g2.drawImage(spriteData.getBackground(), 0, 0, null);
        // these are just placeholders, will replace with sprites later
        if(entities != null)
        {
            for(Entity e: entities)
            {
                if (e.getClass() == Player.class)
                {
                    g2.drawImage(spriteData.getPlayerSprite(), e.getX(), e.getY(), spriteSize, spriteSize, null);
                }
                else if (e.getClass() == Projectile.class)
                {
                    g2.drawImage(spriteData.getProjectileSprite(), e.getX(), e.getY(), spriteSize, spriteSize, null);
                }
                else if (e.getClass() == Enemy.class)
                {
                    g2.drawImage(spriteData.getEnemySprite(), e.getX(), e.getY(), spriteSize, spriteSize, null);
                }
                else if (e.getClass() == Item.class)
                {
                    g2.drawImage(spriteData.getItemSprite(), e.getX(), e.getY(), spriteSize, spriteSize, null);
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
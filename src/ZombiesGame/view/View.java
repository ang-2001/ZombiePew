package ZombiesGame.view;

import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class View extends JFrame
{
    private final int SCALE             = 4;
    private final int BASE_TILE_SIZE    = 16;
    private final int TILE_SIZE = SCALE * BASE_TILE_SIZE;

    private final int WIDTH     = 16 * TILE_SIZE;
    private final int HEIGHT    = 12 * TILE_SIZE;

    private String title;

    private CardLayout layout;
    private JPanel cardPanel;
    private StartPanel startPanel;
    private GamePanel gamePanel;


    private BlockingQueue<Message> queue;

    public View(String title, BlockingQueue<Message> queue)
    {
        this.queue = queue;
        setTitle(title);

        layout = new CardLayout();

        cardPanel = new JPanel(layout);
        startPanel = new StartPanel(queue);
        gamePanel = new GamePanel(queue, TILE_SIZE, new Dimension(WIDTH, HEIGHT));

        cardPanel.add(startPanel, "startPanel");
        cardPanel.add(gamePanel, "gamePanel");

        add(cardPanel);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public void switchPanel(String panel)
    {
        layout.show(cardPanel, panel);

        if (panel.equals("startPanel"))
        {
            startPanel.requestFocus();
        }
        else if (panel.equals("gamePanel"))
        {
            gamePanel.requestFocus();
            gamePanel.start();
        }
        else if (panel.equals("gameOverPanel"))
        {

        }

    }

    public void updateView(GameInfo info)
    {
        gamePanel.updateView(info);
    }
}

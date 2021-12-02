package ZombiesGame.view;

import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.Message;
import ZombiesGame.messages.StartGameMessage;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class GameOverPanel extends JPanel {

    BlockingQueue<Message> queue;

    private Dimension dimensions;

    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    public GameOverPanel(BlockingQueue<Message> queue, Dimension d) {
        this.dimensions = d;
        this.queue = queue;

        BoxLayout startLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(startLayout);
        setBackground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);

        JLabel title = new JLabel();
        String labelText = String.format("<html><div style = 'text-align: center;' " +
                "'WIDTH=%d>%s</div></html>", dimensions.width/2, "GAME OVER");

        title.setText(labelText);
        title.setForeground(Color.RED);
        title.setFont(new Font("Serif", Font.PLAIN, dimensions.height/7));

        titlePanel.add(title);

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, dimensions.height/20));

        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, dimensions.height/20));


        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setFont(new Font("Serif", Font.PLAIN, dimensions.height/20));
        playAgainButton.setForeground(Color.WHITE);

        // PLAY AGAIN BUTTON : REMOVES THE 3D LOOK OF THE BUTTON
        playAgainButton.setBorderPainted(true);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setContentAreaFilled(false);

        titlePanel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        playAgainButton.setAlignmentX(CENTER_ALIGNMENT);


        add(Box.createRigidArea(new Dimension(0,dimensions.height/10)));
        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0,dimensions.height/25)));
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0,dimensions.height/30)));
        add(highScoreLabel);
        add(Box.createRigidArea(new Dimension(0,dimensions.height/15)));
        add(playAgainButton);
        add(Box.createRigidArea(new Dimension(0, dimensions.height)));

        // PLAY AGAIN BUTTON: ACTION LISTENER
        playAgainButton.addActionListener(e -> {
            try {
                queue.put(new StartGameMessage());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public Dimension getPreferredSize()
    {
        return dimensions;
    }

    public void updateScore(GameInfo info)
    {
        scoreLabel.setText("Score: " + info.getScore());
        highScoreLabel.setText("High Score: " + info.getHighScore());
    }

    @Override
    public Dimension getPreferredSize()
    {
        return dimensions;
    }
}

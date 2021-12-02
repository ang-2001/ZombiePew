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
    private int spriteSize;

    private JLabel scoreLabel;
    private JLabel highScoreLabel;

    public GameOverPanel(BlockingQueue<Message> queue, int spriteSize, Dimension d) {
        this.dimensions = d;
        this.spriteSize = spriteSize;
        this.queue = queue;

        BoxLayout startLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(startLayout);
        setBackground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);

        JLabel title = new JLabel();
        String labelText = String.format("<html><div style = 'text-align: center;' " +
                "'WIDTH=%d>%s</div></html>", d.width/2, "GAME OVER");

        title.setText(labelText);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.PLAIN, d.height/7));

        titlePanel.add(title);

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        titlePanel.add(scoreLabel);


        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        titlePanel.add(highScoreLabel);


        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setFont(new Font("Serif", Font.PLAIN, d.height/20));
        playAgainButton.setForeground(Color.WHITE);


        // START BUTTON : REMOVES THE 3D LOOK OF THE BUTTON
        playAgainButton.setBorderPainted(true);
        playAgainButton.setFocusPainted(false);
        playAgainButton.setContentAreaFilled(false);


        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,d.height/10)));
        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0,d.height/15)));
        add(playAgainButton);
        add(Box.createRigidArea(new Dimension(0,d.height)));

        // START BUTTON: ACTION LISTENER
        playAgainButton.addActionListener(e -> {
            try {
                queue.put(new StartGameMessage());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void updateScore(GameInfo info){
        scoreLabel.setText("Score: " + info.getScore());
        highScoreLabel.setText("High Score: " + info.getHighScore());

    }
}

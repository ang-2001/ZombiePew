package ZombiesGame.view;

import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.FirstScreenMessage;
import ZombiesGame.messages.StartGameMessage;
import ZombiesGame.messages.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class StartPanel extends JPanel{

    private final int SCALE = 4;
    private final int BASE_TILE_SIZE = 16;
    private final int SCALED_TILE_SIZE = SCALE * BASE_TILE_SIZE;

    private final int width = 16 * SCALED_TILE_SIZE;
    private final int height = 12 * SCALED_TILE_SIZE;
    BlockingQueue<Message> queue;

    private JLabel highScoreLabel;

    public StartPanel(BlockingQueue<Message> queue){
        this.queue = queue;



        BoxLayout startLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(startLayout);
        setBackground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);

        JLabel title = new JLabel();
        String labelText = String.format("<html><div style = 'text-align: center;' " +
                "'WIDTH=%d>%s</div></html>", width/2, "CALL OF PEW PEW");

        title.setText(labelText);
        title.setForeground(Color.RED);
        title.setFont(new Font("Serif", Font.PLAIN, height/7));

        titlePanel.add(title);


        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        highScoreLabel.setText("High score: ");

        titlePanel.add(highScoreLabel);

        // START BUTTON: SETS FONT STYLE, SIZE, COLOR
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Serif", Font.PLAIN, height/20));
        startButton.setForeground(Color.WHITE);


        // START BUTTON : REMOVES THE 3D LOOK OF THE BUTTON
        startButton.setBorderPainted(true);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);

        // required to center components, very lame
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,height/10)));
        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0,height/15)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0,height)));

        try {
            queue.put(new FirstScreenMessage());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        // START BUTTON: ACTION LISTENER
        startButton.addActionListener(e -> {
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
        return new Dimension(width, height);
    }

    public void updateScore(GameInfo info){
        System.out.println("Entered this function");
        highScoreLabel.setText("High Score: " + info.getHighScore());

    }
}

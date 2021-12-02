package ZombiesGame.view;

import ZombiesGame.controller.GameInfo;
import ZombiesGame.messages.FirstScreenMessage;
import ZombiesGame.messages.StartGameMessage;
import ZombiesGame.messages.Message;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class StartPanel extends JPanel{

    private Dimension dimensions;
    private BlockingQueue<Message> queue;

    private JLabel highScoreLabel;

    public StartPanel(BlockingQueue<Message> queue, Dimension d){
        this.queue = queue;
        this.dimensions = d;


        BoxLayout startLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(startLayout);
        setBackground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);

        JLabel title = new JLabel();
        String labelText = String.format("<html><div style = 'text-align: center;' " +
                "'WIDTH=%d>%s</div></html>", dimensions.width/2, "PEW PEW");

        title.setText(labelText);
        title.setForeground(Color.RED);
        title.setFont(new Font("Serif", Font.PLAIN, dimensions.height/7));

        titlePanel.add(title);


        // HIGH SCORE TEXT: FONT SIZE, COLOR
        highScoreLabel = new JLabel();
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Serif", Font.PLAIN, dimensions.height/20));


        // START BUTTON: SETS FONT STYLE, SIZE, COLOR
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Serif", Font.PLAIN, dimensions.height/20));
        startButton.setForeground(Color.WHITE);


        // START BUTTON : REMOVES THE 3D LOOK OF THE BUTTON
        startButton.setBorderPainted(true);
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);

        // required to center components, very lame
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,dimensions.height/10)));
        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0,dimensions.height/15)));
        add(highScoreLabel);
        add(Box.createRigidArea(new Dimension(0,dimensions.height/15)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, dimensions.height)));

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
        return dimensions;
    }

    public void updateScore(GameInfo info)
    {
        highScoreLabel.setText("High Score: " + info.getHighScore());
    }
}

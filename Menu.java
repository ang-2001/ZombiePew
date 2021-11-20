import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JPanel panel, title, play, quit, score;
    private JLabel backgroundImage, titleLabel, highScore;
    private JButton playButton, quitButton;

    public Menu(){
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());

        backgroundImage = new JLabel();
        backgroundImage.setLayout(new FlowLayout());
        backgroundImage.setIcon(new ImageIcon("CS151-ZombiePew/src/zom.png"));
        backgroundImage.setLayout(new BorderLayout());

        title = new JPanel();
        titleLabel = new JLabel("PEW!PEW!");
        titleLabel.setBounds(500,180,800,200);
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 90));

        play = new JPanel();
        playButton = new JButton("PLAY");
        playButton.setBounds(670,400,100,50);
        playButton.setForeground(Color.black);
        playButton.setFont(new Font("Courier New", Font.BOLD, 30));
        playButton.addActionListener(new OpenListener());

        quit = new JPanel();
        quitButton = new JButton("QUIT");
        quitButton.setBounds(670,450,100,50);
        quitButton.setForeground(Color.black);
        quitButton.setFont(new Font("Courier New", Font.BOLD, 30));
        quitButton.addActionListener(new CloseListener());

        score = new JPanel();
        highScore = new JLabel("Highest Score: 999999");
        highScore.setBounds(15,15,600,50);
        highScore.setForeground(Color.white);
        highScore.setFont(new Font("Courier New", Font.PLAIN, 30));

        add(titleLabel);
        add(playButton);
        add(quitButton);
        add(highScore);
        backgroundImage.add(panel);
        add(backgroundImage);

        setTitle("PEW!PEW!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(getPreferredSize());
        setVisible(true);
    }

    public static void main(String args[])
    {
        // Run in the EDT
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {new Menu();}
        });
    }

    private class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Game g = new Game();
        }
    }

    private class CloseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}

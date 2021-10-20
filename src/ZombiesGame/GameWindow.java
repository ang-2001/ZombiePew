package ZombiesGame;

import javax.swing.*;

public class GameWindow
{
    public GameWindow(String title, GamePanel panel)
    {
        JFrame frame = new JFrame(title);

        frame.add(panel);

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}

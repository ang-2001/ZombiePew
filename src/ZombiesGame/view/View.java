package ZombiesGame.view;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel
{
    private final int SCALE             = 4;
    private final int BASE_TILE_SIZE    = 16;
    private final int SCALED_TILE_SIZE  = SCALE * BASE_TILE_SIZE;

    private final int WIDTH = 16 * SCALED_TILE_SIZE;
    private final int HEIGHT = 12 * SCALED_TILE_SIZE;


    /**
     * constructor for GamePanel class that should initialize panelWidth and panelHeight,
     * which will be used to set up the preferred size of the panel
     */
    public View()
    {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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

package ZombiesGame.view;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel
{
    private final int SCALE             = 4;
    private final int BASE_TILE_SIZE    = 16;
    private final int SCALED_TILE_SIZE  = SCALE * BASE_TILE_SIZE;

    private int panelWidth;
    private int panelHeight;


    /**
     * constructor for GamePanel class that should initialize panelWidth and panelHeight,
     * which will be used to set up the preferred size of the panel
     * @param width the width of the Panel in terms of the number of tiles
     * @param height the height of the Panel in terms of the number of tiles
     */
    public View(int width, int height)
    {
        panelWidth  = width * SCALED_TILE_SIZE;
        panelHeight = height * SCALED_TILE_SIZE;

        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
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

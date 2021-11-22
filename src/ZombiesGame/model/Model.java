package ZombiesGame.model;

import ZombiesGame.controller.GameInfo;

import java.util.LinkedList;

public class Model
{
    private LinkedList<Entity> entities;


    public Model()
    {
        entities    = new LinkedList<>();
        // other gamedata created here
    }


    public void createNewGame()
    {
        entities.clear();
    }


    public void createPlayer(int panelWidth, int panelHeight, int spriteSize)
    {
        int startX = (panelWidth/2) - (spriteSize/2);
        int startY = (panelHeight/2) - (spriteSize/2);

        entities.add(new Player(startX, startY));
    }


    public void updatePlayer(int dx, int dy)
    {
        Player p = (Player) entities.getFirst();

        p.translate(dx, dy);
    }


    public LinkedList<Entity> getEntities()
    {
        return (LinkedList<Entity>) entities.clone();
    }


    public GameInfo getGameStatus()
    {
        return new GameInfo(this);
    }
}

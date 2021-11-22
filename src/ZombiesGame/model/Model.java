package ZombiesGame.model;

import ZombiesGame.controller.GameInfo;

import java.awt.*;
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


    public void createProjectile(Point mousePos)
    {
        Entity player   = entities.getFirst();
        int deltaX      = mousePos.x - player.getX();
        int deltaY      = mousePos.y - player.getY();

        // new projectile created based on position of player, add to entities

        entities.add(new Projectile(player, deltaX, deltaY));
    }


    /**
     *
     * @param dx
     * @param dy
     */
    public void updatePlayer(int dx, int dy)
    {
        Player p = (Player) entities.getFirst();

        p.translate(dx, dy);
    }


    /**
     *
     */
    public void updateEntities()
    {
        for(Entity e : entities)
        {
            if (e.getClass() == Projectile.class)
            {
                e.translate();
            }
        }
    }


    /**
     *
     * @return
     */
    public LinkedList<Entity> getEntities()
    {
        return (LinkedList<Entity>) entities.clone();
    }


    /**
     *
     * @return
     */
    public GameInfo getGameStatus()
    {
        return new GameInfo(this);
    }
}

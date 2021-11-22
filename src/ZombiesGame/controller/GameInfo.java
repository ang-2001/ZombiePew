package ZombiesGame.controller;

import ZombiesGame.model.Entity;
import ZombiesGame.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GameInfo
{
    private LinkedList<Entity> entities;

    public GameInfo(Model m)
    {
        entities = m.getEntities();
    }


    public LinkedList<Entity> getEntityInfo()
    {
        return entities;
    }
}

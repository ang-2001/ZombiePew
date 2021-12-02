package ZombiesGame.controller;

import ZombiesGame.model.Entity;
import ZombiesGame.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GameInfo
{
    private LinkedList<Entity> entities;
    private int score;
    private int highScore;

    public GameInfo(Model m)
    {
        entities = m.getEntities();
        score = m.getScore();
        highScore = m.getHighScore();
    }


    public LinkedList<Entity> getEntityInfo()
    {
        return entities;
    }

    public int getScore(){
        return score;
    }

    public int getHighScore(){
        return highScore;
    }




}

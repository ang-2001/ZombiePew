package ZombiesGame.model;

import ZombiesGame.controller.GameInfo;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class Model
{
    private LinkedList<Entity> entities;

    private int screenWidth;
    private int screenHeight;
    private int spriteSize;
    private int currentEnemies;
    private int maxEnemies;

    private int score = 0;
    private int highScore = 0;
    private File scoreFile;
    private static final int POINTS_SCORED = 5;

    private final Random r = new Random();

    public Model()
    {
        entities    = new LinkedList<>();
        try {
            scoreFile = new File("score.txt");
            if (scoreFile.createNewFile()) {
                System.out.println("File created: " + scoreFile.getName());
                saveScoreToFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createNewGame(int width, int height, int spriteSize)
    {
        this.screenWidth      = width;
        this.screenHeight     = height;
        this.spriteSize       = spriteSize;
        this.maxEnemies       = 7;
        entities.clear();
    }


    public void createPlayer()
    {
        int startX = (screenWidth/2) - (spriteSize/2);
        int startY = (screenHeight/2) - (spriteSize/2);

        entities.add(new Player(startX, startY));
    }


    public void createProjectile(Point mousePos)
    {
        // projectile velocity and position calculated upon creation, no need to change it afterwards
        Entity player   = entities.getFirst();
        int deltaX      = mousePos.x - player.getX();
        int deltaY      = mousePos.y - player.getY();

        int k         = 20;
        double theta  = Math.toDegrees(Math.atan2(deltaY, deltaX));
        theta         = Math.toRadians(theta);

        // Velocity : change in x and change in y per call to translate
        double dx   = k * Math.cos(theta);
        double dy   = k * Math.sin(theta);

        // new projectile created based on position of player, add to entities
        entities.add(new Projectile(player, (int) dx, (int) dy));
    }


    /**
     *
     */
    public void createEnemy()
    {
        // default x and y positions
        int x = 0;
        int y = 0;

        // if current number of enemies on the screen is below the limit create an enemy at a random position
        // along the sides of the screen
        if (currentEnemies < maxEnemies) {
            // randomly generated boolean that determines whether to create the enemy on the x or y axis of screen
            boolean isOnXAxis = r.nextBoolean();

            if (isOnXAxis) {
                // generates an x-coords, taking into account the size of the sprite (64-960)
                x = r.nextInt(screenWidth - (spriteSize)) + spriteSize;

                // random boolean to determine whether the enemy spawns on the bottom or top of screen
                boolean isOnBottom = r.nextBoolean();

                if (isOnBottom) {
                    // sets y to be the very bottom of the screen, else defaults to top of the screen(0)
                    y = screenHeight;
                }
            }
            else
            {
                // generates random y-coords, taking into account size of sprite ()
                y = r.nextInt(screenHeight - (spriteSize)) + spriteSize;

                // determines whether the enemy will spawn on the right or left side of the screen
                boolean isOnRight = r.nextBoolean();

                if (isOnRight)
                {
                    // sets y to be on right of the screen, else defaults to left of the screen
                    x = screenWidth;
                }
            }

            // creates enemy based the new x and y coordinates, adds to entities list and increments enemy counter
            Enemy enemy = new Enemy(x, y);

            entities.add(enemy);
            currentEnemies++;
        }
    }


    private void updateEnemyVelocity(Entity enemy)
    {
        // updates enemy velocity using same calculations as projectile velocity calculation
        Entity player   = entities.getFirst();
        int deltaX      = player.getX() - enemy.getX();
        int deltaY      = player.getY() - enemy.getY();

        int k         = 7;
        double theta  = Math.toDegrees(Math.atan2(deltaY, deltaX));
        theta         = Math.toRadians(theta);

        // Velocity : change in x and y per update call
        double dx   = k * Math.cos(theta);
        double dy   = k * Math.sin(theta);

        enemy.dx = (int) dx;
        enemy.dy = (int) dy;
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
            if (e.getClass() == Enemy.class)
            {
                updateEnemyVelocity(e);
            }

            e.translate();
        }
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(){
        this.score += POINTS_SCORED;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void saveScoreToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(scoreFile));
            bw.write(String.valueOf(getScore()));
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String checkScoreInFile(){
        String firstLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            firstLine = br.readLine();
            //System.out.println("Read score: " + firstLine);
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return firstLine;
    }

    public void setHighScore(){
        String keptScore = checkScoreInFile();
        if (getScore() > Integer.parseInt(keptScore)){
            saveScoreToFile();
            System.out.println("New high score is " + getScore() + ". Previous high score was " + keptScore);
            highScore = getScore();
        }
        highScore = Integer.parseInt(keptScore);
    }

    public int getHighScore(){
        return highScore;
    }


    /**
     *
     * @return
     */
    public LinkedList<Entity> getEntities()
    {
        return new LinkedList<>(entities);
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

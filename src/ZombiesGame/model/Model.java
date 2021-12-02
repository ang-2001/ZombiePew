package ZombiesGame.model;

import ZombiesGame.controller.GameInfo;
import ZombiesGame.view.ActionTracker;

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
    private static final int NORMAL_POINT = 5;
    private static final int ITEM_POINT = 50;

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
        this.maxEnemies       = 8;
        this.currentEnemies   = 0;
        resetScore();
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

        int k         = 25;
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


    public void updatePlayerVelocity()
    {
        Player player               = (Player) entities.getFirst();
        ActionTracker keysPressed   = ActionTracker.getInstance();
        int distance                = 7;

        if (!(keysPressed.isDown() || keysPressed.isUp()) || (keysPressed.isDown() && keysPressed.isUp()))
        {
            player.dy = 0;
        }
        else if (keysPressed.isUp())
        {
            player.dy = -distance;
        }
        else if (keysPressed.isDown())
        {
            player.dy = distance;
        }
        
        if (!(keysPressed.isLeft() || keysPressed.isRight()) || (keysPressed.isLeft() && keysPressed.isRight()))
        {
            player.dx = 0;
        }
        else if (keysPressed.isLeft())
        {
            player.dx = -distance;
        }
        else if (keysPressed.isRight())
        {
            player.dx = distance;
        }
    }


    private void updateEnemyVelocity(Entity enemy)
    {
        if (!enemy.isColliding)
        {
            // updates enemy velocity using same calculations as projectile velocity calculation
            Entity player   = entities.getFirst();
            int deltaX      = player.getX() - enemy.getX();
            int deltaY      = player.getY() - enemy.getY();

            int k         = 8;
            double theta  = Math.toDegrees(Math.atan2(deltaY, deltaX));
            theta         = Math.toRadians(theta);

            // Velocity : change in x and y per update call
            double dx   = k * Math.cos(theta);
            double dy   = k * Math.sin(theta);

            enemy.dx = (int) dx;
            enemy.dy = (int) dy;
        }
    }


    /**
     *
     */
    public void updateEntities()
    {
        for(Entity e : entities)
        {
            e.translate();

            if (e.getClass() == Player.class)
            {
                updatePlayerVelocity();
            }
            else if (e.getClass() == Enemy.class)
            {
                updateEnemyVelocity(e);
            }
        }
    }


    public void removeInactive()
    {
        LinkedList<Entity> inactiveEntities = new LinkedList<>();
        LinkedList<Entity> itemsGenerated   = new LinkedList<>();
        int enemiesRemoved = 0;

        for (Entity e : entities)
        {
            if(!e.isActive() && e.getClass() != Player.class)
            {
                inactiveEntities.add(e);
                if (e.getClass() == Enemy.class)
                {
                    enemiesRemoved++;

                    // probability of item generating is 1/14
                    boolean itemIsGenerated = r.nextInt(14) == 0;

                    if (itemIsGenerated)
                    {
                        itemsGenerated.add(new Item(e));
                    }
                }
            }
        }

        currentEnemies -= enemiesRemoved;
        entities.removeAll(inactiveEntities);
        entities.addAll(itemsGenerated);
    }

    // check collisions and perform actions specific actions based on collision cases
    public void checkCollisions()
    {
        Entity e1;
        Entity e2;

        // reset collision states for active entities
        for (Entity e : entities)
        {
            e.isColliding = false;
        }

        // check for collisions
        for(int i = 0; i < entities.size(); i++)
        {
            e1 = entities.get(i);

            for (int j = i + 1; j < entities.size(); j++)
            {
                e2 = entities.get(j);

                if(e1.collidesWith(e2))
                {
                    if (e1.getClass() == Player.class && e2.getClass() == Enemy.class)
                    {
                        // player interacts with enemy
                        e1.setInactive();
                    }
                    else if (e1.getClass() == Player.class && e2.getClass() == Item.class)
                    {
                        // player interacts with item
                        incrementScore(ITEM_POINT);
                        System.out.println("Score: " + score);
                        e2.setInactive();
                    }
                    else if (e1.getClass() == Enemy.class && e2.getClass() == Enemy.class)
                    {
                        // both are enemies very complicated

                        e1.isColliding = true;
                        e2.isColliding = true;

                        // collision vectors(x and y component)
                        int colVectorX = e2.getX() - e1.getX();
                        int colVectorY = e2.getY() - e1.getY();

                        // calculate distance
                        double distance = (float) Math.sqrt((colVectorX * colVectorX) + (colVectorY * colVectorY));

                        // calculate normalized collision vector = (direction)
                        double normColVectorX = colVectorX / distance;
                        double normColVectorY = colVectorY / distance;

                        // relative velocity vector
                        int relVelX = e1.dx - e2.dx;
                        int relVelY = e1.dy - e2.dy;
                        double speed = (relVelX * normColVectorX) + (relVelY * normColVectorY);

                        if (speed < 0)
                        {
                            break;
                        }

                        // calculate and assign new velocities
                        e1.dx -= (speed * normColVectorX);
                        e1.dy -= (speed * normColVectorY);
                        e2.dx += (speed * normColVectorX);
                        e2.dy += (speed * normColVectorY);
                    }
                    else if ((e1.getClass() == Enemy.class && e2.getClass() == Projectile.class)
                            || (e1.getClass() == Projectile.class && e2.getClass() == Enemy.class))
                    {
                        // when zombie hits projectile or projectile hits zombie
                        incrementScore(NORMAL_POINT);
                        System.out.println("Score: " + score);
                        e1.setInactive();
                        e2.setInactive();
                    }
                }
            }
        }
    }


    public void checkBoundaryCollisions()
    {
        for (Entity e : entities)
        {
            Class type = e.getClass();

            if ( type == Player.class)
            {
                if ((e.dx < 0 && e.x < 0) || (e.dx > 0 && e.x >= screenWidth - e.getHitBox().width))
                {
                    // if player is moving left and hits left wall
                    // or if player is moving right and hits right wall -> stop
                    e.dx = 0;
                }

                if ((e.dy < 0 && e.y < 0) || (e.dy > 0 && e.y >= screenHeight - e.getHitBox().height))
                {
                    // if player is moving up and hits top wall
                    // if player is moving down and hits lower wall -> stop
                    e.dy = 0;
                }
            }
            else
            {
                if (e.x < 0 || e.x > screenWidth - e.getHitBox().width)
                {
                    if (type == Projectile.class)
                    {
                        e.setInactive();
                    }
                    else
                    {
                        e.dx *= -2;
                    }
                }

                if (e.y < 0 || e.y > screenHeight - e.getHitBox().height)
                {
                    if (type == Projectile.class)
                    {
                        e.setInactive();
                    }
                    else
                    {
                        e.dy *= -2;
                    }
                }
            }

            // in-case a game object slips through the border checks, removes it
            int errorMargin = 40;

            if ((e.x < -errorMargin || e.x > screenWidth + errorMargin) ||
                    (e.y < -errorMargin || e.y > screenHeight + errorMargin))
            {
                if (e.getClass() != Player.class)
                    e.setInactive();
            }
        }
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(int scoreValue){
        this.score += scoreValue;
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
        else {
            highScore = Integer.parseInt(keptScore);
        }
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

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Gun {
    private final int bullets = 100;
    private BufferedImage skin;
    private int width;
    private int bulletsPerRound, round, totalBullets;
    private Timer firing, shootAminDelay, reloadDelay, emptyGunDelay, reloadLimit;
    private boolean shoot = false;
    private boolean reload = false;

    public Gun(BufferedImage skin, int bulletsPerRound, int totalBullets){
        this.skin = skin;
        this.bulletsPerRound = bulletsPerRound;
        this.totalBullets = totalBullets;
        this.round = bulletsPerRound;
    }

    public void shoot(){
        if(round <= 0){
            if(!emptyGunDelay.isRunning()){
                emptyGunDelay.start();
            }
            return;
        }
        if(reload){
            return;
        }
    }

    public void reload(){
        if(reloadLimit.isRunning()){
            return;
        }
        reloadLimit.start();

        if(round != 0 && totalBullets >= bulletsPerRound)
        {
            totalBullets = totalBullets - bulletsPerRound + round;
            round = bulletsPerRound;
        }else if (totalBullets < bulletsPerRound && round != 0){

            if(round + totalBullets > bulletsPerRound){
                totalBullets = round + totalBullets - bulletsPerRound;
                round = bulletsPerRound;
            }else{
                round += totalBullets;
                totalBullets = 0;
            }
        }else if (totalBullets < bulletsPerRound){
            round = totalBullets;
            totalBullets = 0;
        }else{
            round = bulletsPerRound;
            totalBullets -= bulletsPerRound;
        }

        if(!reloadDelay.isRunning())
        {
            reload = true;
            reloadDelay.start();
        }
    }

    public int getRound(){
        return round;
    }
    public int getBulletsPerRound(){
        return bulletsPerRound;
    }
    public int getTotalBullets(){
        return totalBullets;
    }
}
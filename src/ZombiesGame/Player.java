import java.awt.*;

public class Player {
    private boolean alive;
    private boolean dying;
    private Image image;
    double xPos, yPos;

    public Player(){
        alive = true;
    }
    public void die(){
        alive = false;
    }
    public boolean isAlive(){
        return alive;
    }
    protected void setAlive(boolean alive){
        this.alive = alive;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage() {
        return image;
    }
    public void setxPos(double xPos){
        this.xPos = xPos;
    }
    public void setyPos(double yPos){
        this.yPos = yPos;
    }
    public double getxPos(){
        return xPos;
    }
    public double getyPos(){
        return yPos;
    }
    public void setDying(boolean dying){
        this.dying = dying;
    }
    public boolean isDying(){
        return dying;
    }
}
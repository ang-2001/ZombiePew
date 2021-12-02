import javax.swing.ImageIcon;

public class Zombie extends Sprite {

    public Zombie(int x, int y) {

        initZombie(x, y);
    }

    private void initZombie(int x, int y) {

        this.x = x;
        this.y = y;

        var zombieImg = "src/images/zombie.png";
        var ii = new ImageIcon(zombieImg);

        setImage(ii.getImage());
    }

    public void act(int direction) {

        this.x += direction;
    }
}

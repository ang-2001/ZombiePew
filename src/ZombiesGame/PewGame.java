package ZombiesGame;

public class PewGame
{
    public static void main(String[] args)
    {
        GamePanel panel = new GamePanel(16, 12);
        GameWindow window = new GameWindow("Pew Game", panel);
    }
}

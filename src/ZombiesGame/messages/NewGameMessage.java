package ZombiesGame.messages;

public class NewGameMessage implements Message
{
    private int width;
    private int height;
    private int spriteSize;

    public NewGameMessage(int panelWidth, int panelHeight, int spriteSize)
    {
        width = panelWidth;
        height = panelHeight;
        this.spriteSize = spriteSize;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getSpriteSize()
    {
        return spriteSize;
    }
}

package ZombiesGame.view;

public class ActionTracker
{
    // ensures one instance of ActionTracker exists
    private static ActionTracker instance;
    private boolean isUp, isDown, isLeft, isRight, isClicked;


    private ActionTracker()
    {
        // does nothing
    }

    public void reset()
    {
        //instance = null;
        isUp = false; isDown = false; isLeft = false; isRight = false; isClicked = false;
    }

    // setter methods
    public void setUp(boolean b)
    {
        this.isUp = b;
    }

    public void setDown(boolean b)
    {
        this.isDown = b;
    }

    public void setRight(boolean b)
    {
        this.isRight = b;
    }

    public void setLeft(boolean b)
    {
        this.isLeft = b;
    }

    public void setClicked(boolean b)
    {
        isClicked = b;
    }


    // getter directions
    public boolean isUp()
    {
        return isUp;
    }

    public boolean isDown()
    {
        return isDown;
    }

    public boolean isRight()
    {
        return isRight;
    }

    public boolean isLeft()
    {
        return isLeft;
    }

    public boolean isClicked()
    {
        return isClicked;
    }


    public static ActionTracker getInstance()
    {
        if (ActionTracker.instance == null)
            instance = new ActionTracker();

        return ActionTracker.instance;
    }

}

package ZombiesGame.messages;

public class KeyPressedMessage implements Message
{
    private boolean isPressed;

    /**
     * creates message that indicates whether the key is pressed(may be assumed to be true if message is created)
     * @param isPressed
     */
    public KeyPressedMessage(boolean isPressed)
    {
        this.isPressed = isPressed;
    }

    
    /**
     * returns boolean value of whether key is pressed or not
     * @return
     */
    public boolean isPressed()
    {
        return isPressed;
    }

}

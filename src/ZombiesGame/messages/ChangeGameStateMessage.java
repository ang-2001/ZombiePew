package ZombiesGame.messages;

public class ChangeGameStateMessage implements Message{
    private boolean isPressed;

    public ChangeGameStateMessage(boolean isPressed){
        this.isPressed = isPressed;
    }

    public boolean getIsPressed(){
        return isPressed;
    }
}

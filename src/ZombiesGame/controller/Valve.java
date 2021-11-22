package ZombiesGame.controller;

import ZombiesGame.messages.Message;

public interface Valve {
    public ValveResponse execute(Message message);
}

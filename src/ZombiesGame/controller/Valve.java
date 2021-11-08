package ZombiesGame.controller;

import ZombiesGame.Message;

public interface Valve {
    public ValveResponse execute(Message message);
}

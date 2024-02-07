package it.unibo.model.pacman.api;

import it.unibo.model.api.GameObject;

public interface Life extends GameObject{

    public void decreaseLife();

    public void increaseLife();

    public int getNumLife();
}

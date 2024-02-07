package it.unibo.model.pacman.api;

import it.unibo.model.api.GameObject;

public interface Points extends GameObject{
    void removePoints(int points);

    void addPoints(int points);

    int getPoints();
}

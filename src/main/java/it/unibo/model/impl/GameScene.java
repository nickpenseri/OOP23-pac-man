package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.Model;


/** Basic Implementation of a model of a scene. */
public class  GameScene implements Model {


    private final List<GameObject> gameObjects;
    //private final List<Character> characters;
    //private final Character pacMan;

    /** Constructor of a generic scene. */
    public GameScene() {
        this.gameObjects = new ArrayList<>();
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public  List<GameObject> getObjects() {
        return new ArrayList<>(gameObjects);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public  void processInput(final List<Command> commands) {
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        //characters.forEach(c -> c.updateState());
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public  boolean isSceneOver() {
        return false;
    }

}

package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.Model;
import it.unibo.model.api.Character;

/** Basic Implementation of a model of a scene. */
public class ModelImpl implements Model {


    private final List<GameObject> gameObjects;
    private final List<Character> characters;

    /** Constructor of a generic scene. */
    public ModelImpl() {
        this.gameObjects = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
       return new ArrayList<>(gameObjects);
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(final List<Command> commands) {
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void updateState() {
        characters.forEach(c -> c.updateState());
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void initGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initGame'");
    }

}

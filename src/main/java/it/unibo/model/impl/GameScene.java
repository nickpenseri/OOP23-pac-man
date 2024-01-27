package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;


import java.awt.Dimension;
import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.URL;
import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.Model;

/** Basic Implementation of a model of a scene. */
public class  GameScene implements Model {

    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<GameObject> gameObjects;
    //private final List<Character> characters;
    //private final Character pacMan;

    /** Constructor of a generic scene. */
    public GameScene() {
        this.gameObjects = new ArrayList<>();
        final URL image = ClassLoader.getSystemResource("image/ghost/blue/BlueGhostDown.png");
        this.gameObjects.add(new GameObjectImpl(new Point(0, 0), image, new Dimension(10, 10)));
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
        if (!commands.isEmpty()) {
            log.info(commands.toString());
        }
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

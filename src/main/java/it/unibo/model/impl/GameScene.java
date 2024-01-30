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
import it.unibo.model.impl.GameObjectImpl.Type;

/** Basic Implementation of a model of a scene. */
public class  GameScene implements Model {

    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<GameObject> gameObjects;
    //private final Dimension dimension;
    //private final List<Character> characters;
    //private final Character pacMan;

    /** Constructor of a generic scene. 
    * @param width the width of the scene
    * @param height the height of the scene
    */
    public GameScene(final int width, final int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        this.gameObjects = new ArrayList<>();
        //dimension = new Dimension(width, height);
        final URL image = ClassLoader.getSystemResource("image/ghost/blue/BlueGhostDown.png");
        this.gameObjects.add(new GameObjectImpl(new Point(0, 0), image, new Dimension(10, 10), Type.GHOST));
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
        log.info("udpategame");
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public  boolean isSceneOver() {
        return false;
    }

}

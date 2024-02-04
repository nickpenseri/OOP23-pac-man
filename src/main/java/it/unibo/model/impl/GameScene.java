package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.input.api.Command;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.api.Model;
import it.unibo.model.map.api.MapBuilder;
import it.unibo.model.map.api.MapReader;
import it.unibo.model.map.impl.MapBuilderImpl;
import it.unibo.model.map.impl.MapReaderImpl;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.PickableGenerator;

/** Basic Implementation of a model of a scene. */
public class GameScene implements Model {

    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<List<GameObject>> gameObjects;
    private final PacMan pacman;
    private final PickableGenerator pickableGenerator;
    // private final Dimension dimension;
    // private final List<Character> characters;
    // private final Character pacMan;

    /**
     * Constructor of a generic scene.
     * 
     * @param width  the width of the scene
     * @param height the height of the scene
     */

    public GameScene(final int width, final int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        this.gameObjects = new ArrayList<>();
        // dimension = new Dimension(width, height);
        /*
         * final URL image =
         * ClassLoader.getSystemResource("image/ghost/blue/BlueGhostDown.png");
         * this.gameObjects.add(new GameObjectImpl(new Point(0, 0), image, new
         * Dimension(10, 10), Type.GHOST));
         */

        // Creo il mapReader passandogli la mappa
        final MapReader map = new MapReaderImpl("src/main/resources/map1.txt");

        final GameObjectFactory gameObjectFactory = new GameObjectFactoryImpl(width, height, map.getMap().length,
                map.getMap()[0].length);
        // Creo il mapBuilder con la mappa che ha letto il mapReader
        final MapBuilder mapBuilder = new MapBuilderImpl(map.getMap(), gameObjectFactory);
        final List<GameObject> walls = mapBuilder.getWallsPath();
        this.gameObjects.add(walls);

        // Creao il pickable generator e creo la mappa dei pickable
        pickableGenerator = gameObjectFactory.createPickableGenerator(mapBuilder.getSpawnCollectibleItems());
        final List<GameObject> pickable = new ArrayList<>(pickableGenerator.getPickableList());
        // Prendo la mappa dei pickable dal pickableGenerator
        this.gameObjects.add(pickable);

        final Dimension standardDimension = gameObjectFactory.getStandardDimension();


        // Creo il pacMan
        this.pacman = gameObjectFactory.createPacMan(mapBuilder.getPacManSpawn(), standardDimension.getWidth(), 3);
        final List<GameObject> pacMan = new ArrayList<>();
        pacMan.add(pacman);
        this.gameObjects.add(pacMan);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        gameObjects.get(1).clear();
        gameObjects.get(1).addAll(pickableGenerator.getPickableList());
        gameObjects.get(2).clear();
        gameObjects.get(2).add(pacman);
        final List<GameObject> gameObjectsFlat = new ArrayList<>();
        for (final List<GameObject> list : gameObjects) {
            gameObjectsFlat.addAll(list);
        }
        return gameObjectsFlat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(final List<Command> commands) {

        if (!commands.isEmpty()) {
            log.info(commands.toString());
            commands.forEach(c -> {
                switch (c) {
                    case SET_DIR_UP:
                        pickableGenerator.takePickable(new Point(1, 1), pacman);
                        pacman.setDirection(Direction.UP);
                        break;
                    case SET_DIR_DOWN:
                        pacman.setDirection(Direction.DOWN);
                        break;
                    case SET_DIR_LEFT:
                        pacman.setDirection(Direction.LEFT);
                        break;
                    case SET_DIR_RIGHT:
                        pacman.setDirection(Direction.RIGHT);
                        break;
                    default:
                        break;
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long elapsed) {
        // characters.forEach(c -> c.updateState());
        pacman.updateState(elapsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSceneOver() {
        return false;
    }

    /**
     * Return the PacMan lifes.
     */
    @Override
    public int getPacManLifes() {
        return pacman.getRemainingLives();
    }

    /**
     * Return the PacMan scores.
     */
    @Override
    public int getPacManScores() {
        return pacman.getPoints();
    }

}

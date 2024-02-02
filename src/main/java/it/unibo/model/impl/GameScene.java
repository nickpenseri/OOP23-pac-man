package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.input.api.Command;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.api.Model;
import it.unibo.model.ghost.api.Ghost;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.map.api.MapBuilder;
import it.unibo.model.map.api.MapReader;
import it.unibo.model.map.impl.MapBuilderImpl;
import it.unibo.model.map.impl.MapGraphImpl;
import it.unibo.model.map.impl.MapReaderImpl;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pacman.impl.PacManImpl;
import it.unibo.model.pickable.api.PickableGenerator;
import it.unibo.model.pickable.impl.PickableGeneratorImpl;

/** Basic Implementation of a model of a scene. */
public class GameScene implements Model {

    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<List<GameObject>> gameObjects;
    private final PickableGenerator pickableGenerator = new PickableGeneratorImpl();
    private final Ghost ghost;
    private final MapBuilder mapBuilder;
    private final GameObjectImpl[][] objectsMap;
    //private final Graph<GameObject, DefaultEdge> graph;
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

        // Creo il mapReader passandogli la mappa
        final MapReader map = new MapReaderImpl("src/main/resources/map1.txt");

        final GameObjectFactory gameObjectFactory = new GameObjectFactoryImpl(width, height, map.getMap().length,
                map.getMap()[0].length);
      
        mapBuilder = new MapBuilderImpl(map.getMap(), gameObjectFactory);
        objectsMap = mapBuilder.getObjectsMap();

        //graph = new MapGraphImpl(objectsMap).getGraph();
        ghost = gameObjectFactory.createGhost(new Point(0,0), 100, GhostColor.RED);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        gameObjects.clear();
        gameObjects.add(new ArrayList<>(List.of(ghost)));
        gameObjects.add(new ArrayList<>(mapBuilder.getPaintMap()));
        
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
                     
                        ghost.setDirection(Direction.UP);
                        break;
                    case SET_DIR_DOWN:
                        ghost.setDirection(Direction.DOWN);
                        break;
                    case SET_DIR_LEFT:
                        ghost.setDirection(Direction.LEFT);
                        break;
                    case SET_DIR_RIGHT:
                        ghost.setDirection(Direction.RIGHT);
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
        ghost.updateState(elapsed);
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
        return 2;
    }

    /**
     * Return the PacMan scores.
     */
    @Override
    public int getPacManScores() {
        return 15;
    }

}

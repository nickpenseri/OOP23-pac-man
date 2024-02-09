package it.unibo.model.impl;

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
import it.unibo.model.ghost.api.GhostBehaviour;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.ghost.impl.FollowingGhost;
import it.unibo.model.ghost.impl.GhostBehaviourImpl;
import it.unibo.model.map.api.MapBuilder;
import it.unibo.model.map.api.MapReader;
import it.unibo.model.map.api.MapSelector;
import it.unibo.model.map.impl.MapBuilderImpl;
import it.unibo.model.map.impl.MapGraphImpl;
import it.unibo.model.map.impl.MapReaderImpl;
import it.unibo.model.map.impl.MapSelectorImpl;
import it.unibo.model.physics.collisions.api.CollisionChecker;
import it.unibo.model.physics.collisions.api.CollisionCheckerFactory;
import it.unibo.model.physics.collisions.impl.CollisionCheckerFactoryImpl;
import it.unibo.model.physics.objectsmover.api.DirectionSelector;
import it.unibo.model.physics.objectsmover.impl.GraphDirectionSelector;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.pickable.api.PickableGenerator;

/** Basic Implementation of a model of a scene. */
public class GameScene implements Model {

    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<List<GameObject>> gameObjects;
    private final PacMan pacman;
    private final PickableGenerator pickableGenerator;
    private final CollisionChecker<GameObject> checker;
    // private final GameObjectImpl[][] objectsMap;
    private final Ghost ghost;
    private final Ghost ghost2;
    private final Ghost ghost3; 
    private final Ghost ghost4;
    private final DirectionSelector directionSelector;
    private final DirectionSelector directionSelector2;
    private final DirectionSelector directionSelector3;
    private final DirectionSelector directionSelector4;
    private final List<GameObject> cammini;
    private static final int RANDOMPOS2 = 59;

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
        final MapSelector mapChooser = new MapSelectorImpl();
        // Creo il mapReader passandogli la mappa
        final MapReader map = new MapReaderImpl(mapChooser.getMapName());

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
        this.pacman = gameObjectFactory.createPacMan(mapBuilder.getPacManSpawn(),
                3,
                mapBuilder.getWallsPath());
        // final List<GameObject> pacMan = new ArrayList<>();
        // pacMan.add(pacman);
        this.gameObjects.add(new ArrayList<>(List.of(pacman)));
        final var objectsMap = mapBuilder.getObjectsMap();

        final Graph<GameObject, DefaultEdge> graph = new MapGraphImpl(objectsMap).getGraph();
        directionSelector = new GraphDirectionSelector(graph);
        directionSelector2 = new GraphDirectionSelector(graph);
        directionSelector3 = new GraphDirectionSelector(graph);
        directionSelector4 = new GraphDirectionSelector(graph);
        cammini = new ArrayList<>(graph.vertexSet());

        
        GhostBehaviour behaviour = new GhostBehaviourImpl(directionSelector, pacman, mapBuilder.getSpawnGhost().get(0), pacman);
        GhostBehaviour behaviour2 = new GhostBehaviourImpl(directionSelector2, pacman, mapBuilder.getSpawnGhost().get(0), pacman);
        GhostBehaviour behaviour3 = new GhostBehaviourImpl(directionSelector3, pacman, mapBuilder.getSpawnGhost().get(0), pacman);
        GhostBehaviour behaviour4 = new GhostBehaviourImpl(directionSelector4, pacman, mapBuilder.getSpawnGhost().get(0), pacman);

        ghost = new FollowingGhost(gameObjectFactory.createGhost(cammini.get(0).getPosition(), GhostColor.RED),behaviour);
        ghost2 = new FollowingGhost(gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(0).getPosition(), GhostColor.BLUE) ,behaviour2);
        ghost3 = new FollowingGhost(gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(1).getPosition(), GhostColor.PINK),behaviour3);
        ghost4 = new FollowingGhost(gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(2).getPosition(), GhostColor.ORANGE), behaviour4);
        this.gameObjects.add(new ArrayList<>(List.of(ghost, ghost2)));
    

        final CollisionCheckerFactory factory = new CollisionCheckerFactoryImpl();
        this.checker = factory.gameObjectChecker();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        gameObjects.get(1).clear();
        gameObjects.get(1).addAll(pickableGenerator.getPickableList());
        gameObjects.get(2).clear();
        gameObjects.get(2).addAll(new ArrayList<>(List.of(pacman)));
        gameObjects.get(3).clear();
        gameObjects.get(3).addAll(new ArrayList<>(List.of(ghost, ghost2, ghost3, ghost4)));
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
       
        ghost4.updateState(elapsed);
        ghost3.updateState(elapsed);
        ghost.updateState(elapsed);
      
        ghost2.updateState(elapsed);
        pickUp();
    }   

    private void pickUp() {
        pickableGenerator.getPickableList().forEach(pickable -> {
            if (checker.areColliding(pickable, pacman)) {
                pickableGenerator.takePickable(pickable.getPosition(), pacman, List.of(ghost, ghost2));
            }
        });
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

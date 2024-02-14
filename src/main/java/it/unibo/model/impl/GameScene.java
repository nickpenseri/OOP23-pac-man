package it.unibo.model.impl;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.core.api.SoundEvent;
import it.unibo.input.api.Command;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.api.GameObjectFactory;
import it.unibo.model.api.Model;
import it.unibo.model.api.SceneBuilder;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostState;
import it.unibo.model.ghost.api.ghostbehaviour.FollowingGhost;
import it.unibo.model.ghost.api.ghostbehaviour.GhostBehaviours;
import it.unibo.model.ghost.api.ghostbehaviour.GhostCoordinates;
import it.unibo.model.ghost.impl.ghostbehaviour.GhostCoordinatesOnGraph;
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
import it.unibo.model.pacman.api.GamePacMan;
import it.unibo.model.pickable.api.EffectPickable;
import it.unibo.model.pickable.api.PickableGenerator;
import it.unibo.model.ui.GameObjectLife;
import it.unibo.model.ui.GameObjectText;

/** Basic Implementation of a model of a scene. */
public class GameScene implements Model {

    private static final int GHOST_DEATH_POINTS = 200;
    private static final int DELAY = 3_000;
    private final Logger log = LoggerFactory.getLogger(GameScene.class);
    private final List<List<GameObject>> gameObjects;
    private final GamePacMan pacman;
    private PickableGenerator pickableGenerator;
    private final CollisionChecker<GameObject> checker;
    // private final GameObjectImpl[][] objectsMap;
    private FollowingGhost ghost;
    private FollowingGhost ghost2;
    private FollowingGhost ghost3;
    private FollowingGhost ghost4;
    private Optional<String> effectText;
    private MapBuilder mapBuilder;
    private final MapSelector mapChooser;
    private MapReader map;
    private final GameObjectFactory gameObjectFactory;
    private final Random random;
    private final SceneBuilder sceneBuilder;
    private final List<SoundEvent> soundEvent;
    private Point lastPacManPos;

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
        this.soundEvent = new ArrayList<>();
        this.gameObjects = new ArrayList<>();
        mapChooser = new MapSelectorImpl();
        map = new MapReaderImpl(mapChooser.getMapName());

        sceneBuilder = new SceneBuilderImpl(width, height, map.getMap().length, map.getMap()[0].length);
        gameObjectFactory = new GameObjectFactoryImpl(sceneBuilder);
        mapBuilder = new MapBuilderImpl(map.getMap(), gameObjectFactory);
        this.pacman = gameObjectFactory.createPacMan(mapBuilder.getPacManSpawn(),
                3,
                mapBuilder.getWallsPath());
        this.gameObjects.add(new ArrayList<>(List.of(pacman)));

        final CollisionCheckerFactory factory = new CollisionCheckerFactoryImpl();
        this.lastPacManPos = this.pacman.getPosition();
        this.checker = factory.gameObjectChecker();
        this.random = new Random();
        initializeMap();
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
        gameObjects.get(4).clear();
        gameObjects.get(4).addAll(uiInfo());
        final List<GameObject> gameObjectsFlat = new ArrayList<>();
        for (final List<GameObject> list : gameObjects) {
            gameObjectsFlat.addAll(list);
        }
        return gameObjectsFlat;
    }

    private List<GameObject> uiInfo() {

        final Dimension panelDimension = sceneBuilder.getUiDimension();
        final Dimension dimension = new Dimension((int) panelDimension.getHeight() / 2,
                (int) panelDimension.getHeight() / 2);
        final List<GameObject> ui = new ArrayList<>();
        final var lives = pacman.getRemainingLives();
        final var points = pacman.getPoints();
        final var y = (int) (sceneBuilder.getGameWorldDimension().getHeight()
                + (sceneBuilder.getUiDimension().getHeight() / 4));
        for (int i = 0; i < lives; i++) {
            ui.add(new GameObjectLife(
                    new Point((int) (panelDimension.getWidth() - (dimension.getWidth() * (i + 2))), y), dimension));
        }
        ui.add(new GameObjectText(new Point((int) dimension.getWidth(), y), dimension, "Score: " + points));
        if (effectText != null) {
            ui.add(new GameObjectText(new Point((int) (dimension.getWidth() + (panelDimension.getWidth() / 4)), y),
                    dimension,
                    effectText.orElse("")));
        }
        return ui;
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
        ghostCollision();
        finishedLevel();
        this.checkPacManPos();
    }

    private void finishedLevel() {
        if (pickableGenerator.finishedPickable()) {
            initializeMap();
        }
    }

    private void initializeMap() {
        map = new MapReaderImpl(mapChooser.getMapName());
        // Creo il mapBuilder con la mappa che ha letto il mapReader
        mapBuilder = new MapBuilderImpl(map.getMap(), gameObjectFactory);
        final List<GameObject> walls = mapBuilder.getWallsPath();
        gameObjects.clear();
        this.gameObjects.add(walls);

        pickableGenerator = gameObjectFactory.createPickableGenerator(mapBuilder.getSpawnCollectibleItems());
        final List<GameObject> pickable = new ArrayList<>(pickableGenerator.getPickableList());
        // Prendo la mappa dei pickable dal pickableGenerator
        this.gameObjects.add(pickable);
        this.pacman.changeMap(walls, mapBuilder.getPacManSpawn());

        this.gameObjects.add(new ArrayList<>(List.of(pacman)));
        final var objectsMap = mapBuilder.getObjectsMap();

        final Optional<Graph<GameObject, DefaultEdge>> graph = Optional.of(new MapGraphImpl(objectsMap).getGraph());

        final GhostCoordinates ghostCoord = new GhostCoordinatesOnGraph(graph, pacman, mapBuilder.getSpawnGhost());
        final GhostCoordinates ghostCoord2 = new GhostCoordinatesOnGraph(graph, pacman, mapBuilder.getSpawnGhost());
        final GhostCoordinates ghostCoord3 = new GhostCoordinatesOnGraph(graph, pacman, mapBuilder.getSpawnGhost());
        final GhostCoordinates ghostCoord4 = new GhostCoordinatesOnGraph(graph, pacman, mapBuilder.getSpawnGhost());

        ghost = gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(0).getPosition(), GhostColor.RED,
                ghostCoord, GhostBehaviours.AGGRESSIVE);
        ghost2 = gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(1).getPosition(), GhostColor.BLUE,
                ghostCoord2, GhostBehaviours.NORMAL);
        ghost3 = gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(2).getPosition(), GhostColor.PINK,
                ghostCoord3, GhostBehaviours.NORMAL);
        ghost4 = gameObjectFactory.createGhost(mapBuilder.getSpawnGhost().get(1).getPosition(), GhostColor.ORANGE,
                ghostCoord4, GhostBehaviours.NORMAL);
        this.gameObjects.add(new ArrayList<>(List.of(ghost, ghost2, ghost3, ghost4)));
        this.gameObjects.add(uiInfo());

    }

    private void pickUp() {
        pickableGenerator.getPickableList().forEach(pickable -> {
            if (checker.areColliding(pickable, pacman)) {
                if (pickable instanceof EffectPickable) {
                    effectText = pickableGenerator.takePickable(pickable.getPosition(), pacman,
                            List.of(ghost, ghost2, ghost3, ghost4));
                    this.soundEvent.add(SoundEvent.BONUS);
                    resetText();
                } else {
                    pickableGenerator.takePickable(pickable.getPosition(), pacman,
                            List.of(ghost, ghost2, ghost3, ghost4));
                }
            }
        });
    }

    private void resetText() {
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Do the action to decrease the speed
                effectText = Optional.empty();
            }
        };

        /*
         * Create new Timer and Schedule the task to reset the text after DELAY seconds
         */
        new Timer().schedule(task, DELAY);
    }

    private void ghostCollision() {
        final List<FollowingGhost> ghosts = new ArrayList<>(List.of(ghost, ghost2, ghost3, ghost4));
        ghosts.forEach(ghost -> {
            if (checker.areColliding(ghost, pacman)) {
                if (ghost.getState().equals(GhostState.NORMAL)) {
                    pacman.removeLife();
                    this.soundEvent.add(SoundEvent.DEATH);
                    pacman.respawn(mapBuilder.getPacManSpawn());
                    for (final var g : ghosts) {
                        g.setPosition(
                                mapBuilder.getSpawnGhost().get(random.nextInt(mapBuilder.getSpawnGhost().size()))
                                        .getPosition());
                        g.resetBehaviour();
                    }
                } else if (ghost.getState().equals(GhostState.SCARED)) {
                    ghost.setState(GhostState.DEAD);
                    pacman.addPoints(GHOST_DEATH_POINTS);
                }

            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSceneOver() {
        return pacman.getRemainingLives() <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SoundEvent> getEvents() {
        final List<SoundEvent> copy = new ArrayList<>(this.soundEvent);
        this.soundEvent.clear();
        return copy;
    }

    private void checkPacManPos() {
        if (!this.lastPacManPos.equals(this.pacman.getPosition())) {
            this.soundEvent.add(SoundEvent.PACMAN);
        } else {
            this.soundEvent.add(SoundEvent.PACMAN_STOP);
        }
        this.lastPacManPos = this.pacman.getPosition();
    }

}

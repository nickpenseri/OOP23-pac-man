package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.awt.geom.Dimension2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Direction;
import it.unibo.model.api.GameObject;
import it.unibo.model.pacman.api.PacMan;
import it.unibo.model.physics.collisions.api.CollisionChecker;
import it.unibo.model.physics.collisions.api.CollisionCheckerFactory;
import it.unibo.model.physics.collisions.impl.CollisionCheckerFactoryImpl;

/**
 * This class models an entity of pacman which moves in a space with walls, that
 * cannot be passed.
 * It decorates an object of PacMan, changing respawn and updateState.
 * @see PacMan
 */
public class PacManWalls implements PacMan {

    private final List<GameObject> walls;
    private final PacMan decorated;
    private final CollisionCheckerFactory checkerFactory;
    private final CollisionChecker<GameObject> collisionChecker;

    /**
     * Creates an object that decorates the PacMan passed as a parameter and with
     * the walls passed in the list passed as a parameter.
     * 
     * @param decorated the PacMan to be decorated
     * @param walls the list of walls
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP2"
    }, justification = "Changings of the decorated object should also affect this object")
    public PacManWalls(final PacMan decorated, final List<GameObject> walls) {
        this.decorated = Objects.requireNonNull(decorated);
        this.walls = new ArrayList<>(Objects.requireNonNull(walls));
        this.checkerFactory = new CollisionCheckerFactoryImpl();
        this.collisionChecker = checkerFactory.gameObjectChecker();

    }

    /** {@inheritDoc} */
    @Override
    public void setDirection(final Direction direction) {
        this.decorated.setDirection(direction);
    }

    /** {@inheritDoc} */
    @Override
    public void setPosition(final Point position) {
        this.decorated.setPosition(position);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Direction> getDirection() {
        return this.decorated.getDirection();
    }

    /** {@inheritDoc} */
    @Override
    public void resetDirection() {
        this.decorated.resetDirection();
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long elapsed) {
        this.decorated.updateState(elapsed);
    }

    /** {@inheritDoc} */
    @Override
    public boolean increaseSpeed() {
        return this.decorated.decreaseSpeed();
    }

    /** {@inheritDoc} */
    @Override
    public boolean decreaseSpeed() {
        return this.decorated.decreaseSpeed();
    }

    /** {@inheritDoc} */
    @Override
    public Point getPosition() {
        return this.decorated.getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public URL getImageUrl() {
        return this.decorated.getImageUrl();
    }

    /** {@inheritDoc} */
    @Override
    public Dimension2D getDimension() {
        return this.decorated.getDimension();
    }

    /** {@inheritDoc} */
    @Override
    public int getPoints() {
        return this.decorated.getPoints();
    }

    /** {@inheritDoc} */
    @Override
    public int getRemainingLives() {
        return this.decorated.getRemainingLives();
    }

    /** {@inheritDoc} */
    @Override
    public void addLife() {
        this.decorated.addLife();
    }

    /** {@inheritDoc} */
    @Override
    public void removeLife() {
        this.decorated.removeLife();
    }

    /** {@inheritDoc} */
    @Override
    public void addPoints(final int points) {
        this.decorated.addPoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public void removePoints(final int points) {
        this.decorated.removePoints(points);
    }

    /** {@inheritDoc} */
    @Override
    public int getSpeedLevel() {
        return this.decorated.getSpeedLevel();
    }

    /** {@inheritDoc} */
    @Override
    public void respawn(final Point spawnPoint) {
        this.decorated.respawn(spawnPoint);
    }

}

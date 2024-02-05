package it.unibo.model.pacman.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class PacManWalls extends PacManDecoratorImpl {

    private final List<GameObject> walls;
    private final CollisionChecker<GameObject> collisionChecker;

    /**
     * Creates an object that decorates the PacMan passed as a parameter and with
     * the walls passed in the list passed as a parameter.
     * 
     * @param decorated the PacMan to be decorated
     * @param walls the list of walls
     * @throws NullPointerException if the list passed is null
     */
    public PacManWalls(final PacMan decorated, final List<GameObject> walls) {
        super(decorated);
        this.walls = new ArrayList<>(Objects.requireNonNull(walls));
        final CollisionCheckerFactory checkerFactory = new CollisionCheckerFactoryImpl();
        this.collisionChecker = checkerFactory.gameObjectChecker();
    }

    @Override
    public void correctPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'correctPosition'");
    }

    /**
     * Changes PacMan's position to the given point and resets the direction.
     * 
     * @param spawnPoint the new position of pacman
     * @throws IllegalArgumentException if the given position collides with a wall
     */
    @Override
    public void respawn(final Point spawnPoint) {
        final Point initialPos = this.getPosition();
        this.setPosition(spawnPoint);
        if (this.isInWalls()) {
            this.setPosition(initialPos);
            throw new IllegalArgumentException("Cannor respawn inside walls");
        }
        super.respawn(spawnPoint);
    }

    private boolean isInWalls() {
        return walls.stream()
            .anyMatch(wall -> collisionChecker.areColliding(this, wall));
    }

}

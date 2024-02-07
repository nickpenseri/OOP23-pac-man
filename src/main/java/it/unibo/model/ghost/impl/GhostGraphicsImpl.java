package it.unibo.model.ghost.impl;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import it.unibo.model.api.Direction;
import it.unibo.model.ghost.api.GhostColor;
import it.unibo.model.ghost.api.GhostGraphics;
import it.unibo.model.ghost.api.GhostState;

/**
 * This class represents an implementation of ImageChooser, which can be updated
 * and can give
 * the actual image.
 */
public class GhostGraphicsImpl implements GhostGraphics {

    private static final String BASE_PATH = "/image/ghost/";

    private final String stopped;
    private final String upTailExtended;
    private final String upTailRetracted;
    private final String rightTailExtended;
    private final String rightTailRetracted;
    private final String downTailExtended;
    private final String downTailRetracted;
    private final String leftTailExtended;
    private final String leftTailRetracted;
    private final String fear;
    private final String dead;
    private boolean tailExtended;
    private GhostState state;

    /**
     * Creates an object of this class which initial state is closed.
     * @param color the color of the ghost
     */
    public GhostGraphicsImpl(final GhostColor color) {
        final String commonName = BASE_PATH + color.name().toLowerCase(Locale.US) + "/";
        this.stopped = commonName + "Ghost.png";
        this.upTailExtended = commonName + "GhostUp.png";
        this.upTailRetracted = commonName + "GhostUp2.png";
        this.rightTailExtended = commonName + "GhostRight2.png";
        this.rightTailRetracted = commonName + "GhostRight.png";
        this.downTailExtended = commonName + "GhostDown2.png";
        this.downTailRetracted = commonName + "GhostDown.png";
        this.leftTailExtended = commonName + "GhostLeft2.png";
        this.leftTailRetracted = commonName + "GhostLeft.png";
        this.fear = BASE_PATH + "fear/GhostFear.png";
        this.dead = BASE_PATH + "eyes/GhostEyes.png";
        this.tailExtended = true;
        this.state = GhostState.NORMAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.tailExtended = !this.tailExtended;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL actualImageUrl(final Optional<Direction> dir) {

    

        return switch (state) {
            case NORMAL ->  getClass().getResource(select(dir));
            case DEAD ->  getClass().getResource(dead);
            case SCARED -> getClass().getResource(this.tailExtended ? fear : select(dir));
            default -> null;
        };
    }

    private String select(final Optional<Direction> dir) {
        if (dir.isEmpty()) {
            return stopped;
        } else if (this.tailExtended) {
            return switch (dir.get()) {
                case UP -> upTailRetracted;
                case RIGHT -> rightTailRetracted;
                case DOWN -> downTailRetracted;
                case LEFT -> leftTailRetracted;
            };
        } else {
            return switch (dir.get()) {
                case UP -> upTailExtended;
                case RIGHT -> rightTailExtended;
                case DOWN -> downTailExtended;
                case LEFT -> leftTailExtended;
            };
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(final GhostState state) {
       this.state = state;
    }
}

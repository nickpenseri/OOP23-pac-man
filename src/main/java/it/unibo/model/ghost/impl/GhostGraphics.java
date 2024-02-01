package it.unibo.model.ghost.impl;

import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import it.unibo.model.api.Direction;
import it.unibo.model.api.ImageChooser;
import it.unibo.model.ghost.api.GhostColor;

/**
 * This class represents an implementation of ImageChooser, which can be updated
 * and can give
 * the actual image.
 */
public class GhostGraphics implements ImageChooser {

    private static final String BASE_PATH = "image/ghost/";

    private final String stopped;
    private final String upTailExtended;
    private final String upTailRetracted;
    private final String rightTailExtended;
    private final String rightTailRetracted;
    private final String downTailExtended;
    private final String downTailRetracted;
    private final String leftTailExtended;
    private final String leftTailRetracted;
    private boolean tailExtended;

    /**
     * Creates an object of this class which initial state is closed.
     * @param color the color of the ghost
     */
    public GhostGraphics(final GhostColor color) {
        final String commonName = BASE_PATH + color.name().toLowerCase(Locale.US) + "/";
        this.stopped = commonName + "Ghost.png";
        this.upTailExtended = commonName + "GhostUp.png";
        this.upTailRetracted = commonName + "GhostUp2.png";
        this.rightTailExtended = commonName + "Right2.png";
        this.rightTailRetracted = commonName + "Right.png";
        this.downTailExtended = commonName + "Down2.png";
        this.downTailRetracted = commonName + "Down.png";
        this.leftTailExtended = commonName + "Left2.png";
        this.leftTailRetracted = commonName + "Left.png";
        this.tailExtended = true;
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
        final String actualImage = select(dir);
        return ClassLoader.getSystemResource(actualImage);
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
}

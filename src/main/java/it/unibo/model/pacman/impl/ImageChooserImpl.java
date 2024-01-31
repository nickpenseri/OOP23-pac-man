package it.unibo.model.pacman.impl;

import java.net.URL;
import java.util.Optional;
import it.unibo.model.api.Direction;
import it.unibo.model.pacman.api.ImageChooser;

/**
 * This class represents an implementation of ImageChooser, which can be updated
 * and can give
 * the actual image.
 */
public class ImageChooserImpl implements ImageChooser {
    private static final String COMMON_NAME = "image/pac_man/";
    private static final String STOPPED = COMMON_NAME + "PacMan.png";
    private static final String UP_OPEN = COMMON_NAME + "PacManUp2.png";
    private static final String UP_CLOSED = COMMON_NAME + "PacManUp.png";
    private static final String RIGHT_OPEN = COMMON_NAME + "PacManRight2.png";
    private static final String RIGHT_CLOSED = COMMON_NAME + "PacManRight.png";
    private static final String DOWN_OPEN = COMMON_NAME + "PacManDown2.png";
    private static final String DOWN_CLOSED = COMMON_NAME + "PacManDown.png";
    private static final String LEFT_OPEN = COMMON_NAME + "PacManLeft2.png";
    private static final String LEFT_CLOSED = COMMON_NAME + "PacManLeft.png";
    private boolean closed;

    /**
     * Creates an object of this class which initial state is closed.
     */
    public ImageChooserImpl() {
        this.closed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.closed = !this.closed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL actualImageUrl(final Optional<Direction> dir) {
        return ClassLoader.getSystemResource(this.select(dir));
    }

    private String select(final Optional<Direction> dir) {
        if (dir.isEmpty()) {
            return STOPPED;
        } else if (this.closed) {
            return switch (dir.get()) {
                case UP -> UP_CLOSED;
                case RIGHT -> RIGHT_CLOSED;
                case DOWN -> DOWN_CLOSED;
                case LEFT -> LEFT_CLOSED;
            };
        } else {
            return switch (dir.get()) {
                case UP -> UP_OPEN;
                case RIGHT -> RIGHT_OPEN;
                case DOWN -> DOWN_OPEN;
                case LEFT -> LEFT_OPEN;
            };
        }
    }
}

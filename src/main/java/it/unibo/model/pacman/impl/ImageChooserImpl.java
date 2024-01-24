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
    private static final String CLOSED_NAME = "";
    private static final String UP_NAME = "";
    private static final String RIGHT_NAME = "";
    private static final String DOWN_NAME = "";
    private static final String LEFT_NAME = "";
    private static final URL CLOSED_URL = ClassLoader.getSystemResource(CLOSED_NAME);
    private static final URL UP_URL = ClassLoader.getSystemResource(UP_NAME);
    private static final URL RIGHT_URL = ClassLoader.getSystemResource(RIGHT_NAME);
    private static final URL DOWN_URL = ClassLoader.getSystemResource(DOWN_NAME);
    private static final URL LEFT_URL = ClassLoader.getSystemResource(LEFT_NAME);
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
        return dir.isEmpty() || this.closed ? CLOSED_URL : switch (dir.get()) {
                case UP -> UP_URL;
                case RIGHT -> RIGHT_URL;
                case DOWN -> DOWN_URL;
                case LEFT -> LEFT_URL;
        };
    }
}

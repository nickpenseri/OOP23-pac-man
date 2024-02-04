package it.unibo.view.impl;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.view.api.View;

/** Swing Implementation of View Interface. */
public abstract class ViewImpl extends JPanel implements View, KeyListener {

    private static final long serialVersionUID = 1L;

    private final List<Command> readedCommands;
    private final Logger log = LoggerFactory.getLogger(ViewImpl.class);
    private List<GameObject> gameObjects = new ArrayList<>();
    private static final int DIMENSION = 20;

    /**
     * Constructor for the View.
     * 
     * @param width  the width of the view
     * @param height the height of the view
     */
    public ViewImpl(final int width, final int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        setSize(width, height);
        this.readedCommands = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateView(final List<GameObject> gameObjects) {
        this.gameObjects = new ArrayList<>(Objects.requireNonNull(gameObjects));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;

            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());

            // Create a Map to store the scaled images
            final Map<String, Image> scaledImages = new HashMap<>();

            // In your drawing method
            this.gameObjects.stream().forEach(obj -> {
                final Point pos = obj.getPosition();
                Image img = null;
                try {
                    final var url = obj.getImageUrl().getPath();
                    // Check if the scaled image is already in the Map
                    if (scaledImages.containsKey(url)) {
                        img = scaledImages.get(url);
                    } else {
                        // If not, read and scale the image, and put it in the Map
                        img = ImageIO.read(new File(url)).getScaledInstance(DIMENSION, DIMENSION, SCALE_DEFAULT);
                        scaledImages.put(url, img);
                    }
                } catch (IOException e) {
                    log.error("error during image reading" + e.getMessage());
                }
                g2.drawImage(img, pos.x * DIMENSION, pos.y * DIMENSION, this);
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized List<Command> getListCommands() {
        final List<Command> commands = new ArrayList<>(readedCommands);
        readedCommands.clear();
        return commands;
    }

    /**
     * Every time that the view is added to the frame, it request the focus for read
     * the key.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        setkeyListenerSettings();
    }

    /**
     * This method is used to add a command to the list of readed commands.
     * 
     * @param command the command to add
     */
    protected final synchronized void addCommand(final Command command) {
        this.readedCommands.add(command);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return new Dimension(this.getWidth(), this.getHeight());
    }

    @Override
    public abstract void keyTyped(KeyEvent e);

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    /**
     * This method is used to set the keyListener for the view.
     */
    private void setkeyListenerSettings() {
        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
    }

}

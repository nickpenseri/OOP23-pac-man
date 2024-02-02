package it.unibo.view.impl;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
    private Set<GameObject> gameObjects;
    private static final int DIMENSION = 20;
     // Create a Map to store the scaled images
    final Map<String, Image> scaledImages;
    private Map<GameObject, Point> previousPositions = new HashMap<>();
    private Map<GameObject, String> previousImageUrls = new HashMap<>();
    private List<GameObject> objectsToRedraw = new ArrayList<>();

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

        this.setDoubleBuffered(true);
        setSize(width, height);
        this.readedCommands = new ArrayList<>();
        this.scaledImages = new HashMap<>();
        gameObjects = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateView(final List<GameObject> gameObjects) {
        this.gameObjects.addAll(gameObjects);
        gameObjects.forEach(obj -> {
            final var url = obj.getImageUrl().getPath();
            if (!scaledImages.containsKey(url)) {
                try {
                    System.out.println("Reading image: " + url);
                    Image img = ImageIO.read(new File(url)).getScaledInstance((int) obj.getDimension().getHeight(),(int) obj.getDimension().getWidth(), SCALE_DEFAULT);
                    scaledImages.put(url, img);
                } catch (IOException e) {
                    log.error("error during image reading" + e.getMessage());
                }
            }
     
    });
       

       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;

            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());

                    // Iterate over all game objects
        for (GameObject obj : gameObjects) {
            final var url = obj.getImageUrl().getPath();

            // Check if the object's position or image URL has changed
            if (!Objects.equals(previousPositions.get(obj), obj.getPosition()) || !Objects.equals(previousImageUrls.get(obj), url)) {
                // Add the object to the list of objects to redraw
                objectsToRedraw.add(obj);
                System.out.println("Adding object to redraw: " + obj);
                // Update the previous position and image URL
                previousPositions.put(obj, obj.getPosition());
                previousImageUrls.put(obj, url);
            } else {
                // Remove the object from the list of objects to redraw
                objectsToRedraw.remove(obj);
            }
        }
          
            // In your drawing method
            this.objectsToRedraw.stream().forEach(obj -> {
                final Point pos = obj.getPosition();
                var img = scaledImages.get(obj.getImageUrl().getPath());
              
                g2.drawImage(img, pos.x , (int) (this.getHeight() - obj.getDimension().getWidth() - obj.getPosition().y) , this);
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

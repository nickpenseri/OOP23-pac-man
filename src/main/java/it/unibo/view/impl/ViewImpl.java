package it.unibo.view.impl;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.awt.Point;
import java.awt.RenderingHints;
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


/**Swing Implementation of View Interface.  */
public abstract class ViewImpl extends JPanel implements View, KeyListener {

    private static final long serialVersionUID = 1L;

    private final List<Command> readedCommands;
    private final int width;
    private final int height;
    private final Logger log = LoggerFactory.getLogger(ViewImpl.class);
    private List<GameObject> gameObjects;

    /** Constructor for the View.
     * @param width the width of the view
     * @param height the height of the view
     */
    public ViewImpl(final int width, final int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }

        setSize(width, height);
        this.width = width;
        this.height = height;
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
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());

            this.gameObjects.stream().forEach(obj -> {
                final Point pos = obj.getPosition();
                Image img = null;
                try {
                    final var url = "src/main/resources/image/ghost/blue/BlueGhostDown.png";
                    //log.info(url);
                    img = ImageIO.read(new File(url)).getScaledInstance(this.width / 10, this.height / 10, SCALE_DEFAULT);
                } catch (IOException e) {
                    log.error("error during image reading" + e.getMessage());
                }
                g2.drawImage(img, pos.x, pos.x, this);
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final synchronized  List<Command> getListCommands() {
       final List<Command> commands = new ArrayList<>(readedCommands);
       readedCommands.clear();
       return commands;
    }

    /**
     * Every time that the view is added to the frame, it request the focus for read the key.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        setkeyListenerSettings();
    }


    /**
     * This method is used to add a command to the list of readed commands.
     * @param command the command to add
     */
    protected final synchronized  void addCommand(final Command command) {
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

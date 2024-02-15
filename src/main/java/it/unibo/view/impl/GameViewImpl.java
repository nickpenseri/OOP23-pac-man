package it.unibo.view.impl;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.model.ui.api.GameObjectText;
import it.unibo.view.api.GameView;

/** View of the actual Game. */
public abstract class GameViewImpl extends ViewImpl implements KeyListener, GameView {

    private static final long serialVersionUID = 1L;
    private final transient Logger log = LoggerFactory.getLogger(GameViewImpl.class);
    private transient List<GameObject> gameObjects;
    private final transient Map<String, Image> scaledImages;
    private final List<Command> readedCommands;

    /**
     * Constructor of the GameView.
     */
    public GameViewImpl() {
        this.setOpaque(true);
        gameObjects = new ArrayList<>();
        this.scaledImages = new HashMap<>();
        this.readedCommands = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final List<GameObject> gameObjects) {
        this.gameObjects = new ArrayList<>(Objects.requireNonNull(gameObjects));
        gameObjects.forEach(obj -> {
            final var url = obj.getImageUrl();
            if (!scaledImages.containsKey(url.toString())) {
                try {
                    final Image img = ImageIO.read(url).getScaledInstance(
                            (int) obj.getDimension().getHeight(), (int) obj.getDimension().getWidth(),
                            SCALE_DEFAULT);
                    scaledImages.put(url.toString(), img);
                } catch (IOException e) {
                    log.error("error during image reading" + e.getMessage());
                }
            }
        });
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
     * {@inheritDoc}
     */
    @Override
    public final synchronized List<Command> getListCommands() {
        final List<Command> commands = new ArrayList<>(readedCommands);
        readedCommands.clear();
        return commands;
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
     * This method is used to set the keyListener for the view.
     */
    private void setkeyListenerSettings() {
        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
    }

    @Override
    public abstract void keyTyped(KeyEvent e);

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(final Graphics g) {
        if (g instanceof Graphics2D) {
            final Graphics2D g2 = (Graphics2D) g;
            SetupGraphics2D.setupGraphics2DStatic(g2, this.getWidth(), this.getHeight());

            this.gameObjects.stream().forEach(obj -> {
                if (obj instanceof GameObjectText) {
                    g2.setColor(Color.BLACK);
                    g2.setFont(new Font("Arial", Font.PLAIN, (int) ((GameObjectText) obj).getDimension().getHeight()));
                    final GameObjectText text = (GameObjectText) obj;
                    g2.drawString(text.getText(), text.getPosition().x, this.getHeight() - text.getPosition().y);
                } else {
                    final Point pos = obj.getPosition();
                    final var img = scaledImages.get(obj.getImageUrl().toString());
                    g2.drawImage(img, pos.x,
                            (int) (this.getHeight() - obj.getDimension().getWidth() - obj.getPosition().y),
                            this);
                }
            });
        }
    }
}

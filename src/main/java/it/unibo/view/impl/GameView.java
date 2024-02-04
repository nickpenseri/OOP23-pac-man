package it.unibo.view.impl;

import static java.awt.Image.SCALE_DEFAULT;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
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

/** View of the actual Game. */
public class GameView extends ViewImpl {

    private static final long serialVersionUID = 1L;
    private final transient Logger log = LoggerFactory.getLogger(GameView.class);
    private transient List<GameObject> gameObjects;
    private final transient Map<String, Image> scaledImages;
    /**
     * Constructor of the GameView.
     */
    public GameView() {
        this.setOpaque(true);
        gameObjects = new ArrayList<>();
        this.scaledImages = new HashMap<>();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final List<GameObject> gameObjects) {
        this.gameObjects = new ArrayList<>(Objects.requireNonNull(gameObjects));
        gameObjects.forEach(obj -> {
            final var url = obj.getImageUrl().getPath();
            if (!scaledImages.containsKey(url)) {
                try {
                    final Image img = ImageIO.read(new File(url)).getScaledInstance(
                                (int) obj.getDimension().getHeight(), (int) obj.getDimension().getWidth(), SCALE_DEFAULT);
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

             this.gameObjects.stream().forEach(obj -> {
                final Point pos = obj.getPosition();
                final var img = scaledImages.get(obj.getImageUrl().getPath());
                g2.drawImage(img, pos.x, (int) (this.getHeight() - obj.getDimension().getWidth() - obj.getPosition().y), this);
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        System.out.println("KeyTyped");
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            log.info("UP");
            addCommand(Command.SET_DIR_UP);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            log.info("DOWN");
            addCommand(Command.SET_DIR_DOWN);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            log.info("LEFT");
            addCommand(Command.SET_DIR_LEFT);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            log.info("RIGHT");
            addCommand(Command.SET_DIR_RIGHT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}

package it.unibo.core.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.core.api.Window;
import it.unibo.view.api.View;

/** Implementation of a Swing window. */
public class WindowImpl implements Window {

    private final Logger log = LoggerFactory.getLogger(WindowImpl.class);
    private final JFrame frame;

    private final Dimension dimension;
    private View gameViewPanel;
    final View InfoViewInfo;

    /**
     * Constructor of a window.
     * 
     * @param view1        the scene panel
     * @param gameViewInfo the score panel
     * @param gameName     the name of the game
     * @param weight       the weight of the window
     * @param height       the height of the window
     */
    public WindowImpl(final View gameViewPanel, final View gameViewInfo, final String gameName, final int weight,
            final int height) {

        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }
        frame = new JFrame(gameName);
        dimension = new Dimension(weight, height);
        frame.setSize(weight, height);
        frame.setMinimumSize(new Dimension(weight, height));
        frame.setResizable(true);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        this.gameViewPanel = gameViewPanel;
        this.InfoViewInfo = gameViewInfo;
        
        
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 1;
        constraints.weighty = 0.95; 
        constraints.fill = GridBagConstraints.BOTH;
        frame.add((Component)this.gameViewPanel, constraints);

       constraints.gridx = 0;
       constraints.gridy = 0;
       constraints.weightx = 1.0;
       constraints.weighty = 0.05; 
        frame.add((Component) this.InfoViewInfo, constraints);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent ev) {
                System.exit(-1);
            }

            @Override
            public void windowClosed(final WindowEvent ev) {
                System.exit(-1);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                frame.repaint();
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            log.error("Error meanwhile repaintg frame", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPanelScene(final View scenePanel) {

        if (!Objects.isNull(this.gameViewPanel)) {
            frame.getContentPane().remove((Component) this.gameViewPanel);
        }

        this.gameViewPanel = scenePanel;
        frame.getContentPane().add((Component) gameViewPanel);
        frame.pack();
        frame.validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return new Dimension((int) this.dimension.getWidth(), (int) this.dimension.getHeight());
    }


    @Override
    public Dimension getGamePanelDimension() {
        return new Dimension((int) this.gameViewPanel.getDimension().getWidth(), (int) this.gameViewPanel.getDimension().getHeight());
    }

    @Override 
    public Dimension getInfoPanelDimension() {
        return new Dimension((int) this.InfoViewInfo.getDimension().getWidth(), (int) this.InfoViewInfo.getDimension().getHeight());
    }

}

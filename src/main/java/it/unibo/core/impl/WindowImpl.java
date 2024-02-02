package it.unibo.core.impl;

import java.awt.BorderLayout;
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
import it.unibo.view.impl.ViewImplInfo;

/** Implementation of a Swing window. */
public class WindowImpl implements Window {

    private final Logger log = LoggerFactory.getLogger(WindowImpl.class);
    private final JFrame frame;

    private final Dimension dimension;
    private View panel;

    /**
     * Constructor of a window.
     * 
     * @param view1        the scene panel
     * @param gameViewInfo the score panel
     * @param gameName     the name of the game
     * @param weight       the weight of the window
     * @param height       the height of the window
     */
    public WindowImpl(final View view1, final ViewImplInfo gameViewInfo, final String gameName, final int weight,
            final int height) {

        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height must be positive");
        }
        frame = new JFrame(gameName);
        dimension = new Dimension(weight, height);
        frame.setSize(weight, height);
        frame.setMinimumSize(new Dimension(weight, height));
        frame.setResizable(false);
     
        final ViewImplInfo panel2 = gameViewInfo.clone();
      
        this.panel = view1;
     

        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        
        constraints.gridy = 1;
        constraints.weighty = 0.9; // Questo pannello occupa il 70% dello spazio verticale
        constraints.fill = GridBagConstraints.BOTH;
        frame.add((Component)this.panel, constraints);

        // Aggiunta del pannello 2 (più alto)
       // Aggiunta del pannello 1 (più basso)
       constraints.gridx = 0;
       constraints.gridy = 0;
       constraints.weightx = 1.0;
       constraints.weighty = 0.1; // Questo pannello occupa il 30% dello spazio verticale
        frame.add(panel2, constraints);

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

        if (!Objects.isNull(this.panel)) {
            frame.getContentPane().remove((Component) this.panel);
        }

        this.panel = scenePanel;
        frame.getContentPane().add((Component) panel);
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

}

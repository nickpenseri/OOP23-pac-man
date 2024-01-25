package it.unibo.core.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.core.api.Window;
import it.unibo.view.api.View;

/** Implementation of a Swing window. */
public class WindowImpl implements Window {


    private final JFrame frame;
    private final int weight;
    private final int height;
    private  View panel;

    /** Constructor of a window.
     * @param scenePanel the scene panel
     * @param gameName the name of the game
     * @param weight the weight of the window
     * @param height the height of the window
     */
    public WindowImpl(final View scenePanel, final String gameName, final int weight, final int height) {
        frame = new JFrame(gameName);
        this.weight = weight;
        this.height = height;
        frame.setSize(this.weight, this.height);
    	frame.setMinimumSize(new Dimension(weight, height));
        frame.setResizable(false);

        setPanelScene(scenePanel);

        frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent ev) {
				System.exit(-1);
			}
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
    	} catch (Exception ex) {
    		ex.printStackTrace();
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
    
}

package it.unibo.view.impl;

import java.awt.Dimension;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.unibo.view.api.View;

/** Swing Implementation of View Interface. */
public abstract class ViewImpl extends JPanel implements View {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getDimension() {
        return new Dimension(this.getWidth(), this.getHeight());
    }

    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

}

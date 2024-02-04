package it.unibo.view.impl;



import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.unibo.input.api.Command;
import it.unibo.model.api.GameObject;
import it.unibo.view.api.View;

/** Swing Implementation of View Interface. */
public abstract class ViewImpl extends JPanel implements View, KeyListener {

    private static final long serialVersionUID = 1L;
    private final List<Command> readedCommands;
    /**
     * Constructor for the View.
     * 
     */
    public ViewImpl() {
        this.readedCommands = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateView(List<GameObject> gameObjects);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void paint(Graphics g);

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

    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

}

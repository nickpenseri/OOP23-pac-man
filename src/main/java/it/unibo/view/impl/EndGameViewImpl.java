package it.unibo.view.impl;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.input.api.MenuCommand;
import it.unibo.view.api.MenuView;

/**
 * Swing Implementation of EndGame.
 */
public class EndGameViewImpl extends ViewImpl implements MenuView {
    private static final long serialVersionUID = 1L;
    private final List<MenuCommand> commands;

    /**
     * Constructor of the EndGameViewImpl.
     * 
     * @param score the score of the game
     */
    public EndGameViewImpl(final int score) {
        super();
        commands = new ArrayList<>();

        final JLabel titleLabel = new JLabel("Score: " + score);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel);
        final JButton restart = new JButton();
        restart.setText("Restart");
        this.add(restart);
        final JButton quit = new JButton();
        quit.setText("Quit");
        this.add(quit);

        restart.addActionListener(e -> commands.add(MenuCommand.START));
        quit.addActionListener(e -> {
            // Get the current frame
            final JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(quit);
            // Dispose the frame
            if (frame != null) {
                frame.dispose();
            }
        });
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public List<MenuCommand> getListCommands() {
        final List<MenuCommand> list = new ArrayList<>(commands);
        commands.clear();
        return list;
    }

}

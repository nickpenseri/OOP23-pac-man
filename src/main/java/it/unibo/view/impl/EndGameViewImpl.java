package it.unibo.view.impl;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import it.unibo.input.api.MenuCommand;
import it.unibo.view.api.MenuView;

/**
 * Swing Implementation of EndGame.
 */
public class EndGameViewImpl extends ViewImpl implements MenuView {
    private static final int FONT_SIZE = 50;
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
        titleLabel.setForeground(Color.WHITE);  // Set the text color to white
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));  // Set the font size to 20

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        final JButton restart = new JButton();
        restart.setText("Restart");
        final JButton quit = new JButton();
        quit.setText("Quit");
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(restart);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(quit);

        final JPanel horizzontalPanel = new JPanel();
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        horizzontalPanel.setLayout(new GridBagLayout());
        horizzontalPanel.add(titleLabel);
        horizzontalPanel.add(Box.createVerticalStrut(100), gbc);
        horizzontalPanel.add(buttonPanel);

        final JPanel gridPanel = new JPanel(new GridBagLayout());

        gridPanel.add(horizzontalPanel);
        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.CENTER);
        this.setBackground(Color.BLUE);
        buttonPanel.setOpaque(false);
        horizzontalPanel.setOpaque(false);
        gridPanel.setOpaque(false);

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

package it.unibo.view.impl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.Box;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.unibo.input.api.MenuCommand;
import it.unibo.view.api.MenuView;

/**
 * Swing Implementation of MenuView Interface.
 */
public class MenuViewImpl extends ViewImpl implements MenuView {

    private static final float DERIVE_FONT = 50.0f;
    private static final int WHIDTH = 500;
    private static final long serialVersionUID = 1L;
    private final List<MenuCommand> commands;

    /**
     * Constructor of the MenuViewImpl.
     */
    public MenuViewImpl() {
        super();
        commands = new ArrayList<>();
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        final JButton start = new JButton();
        start.setText("Start");
        final JButton quit = new JButton();
        quit.setText("Quit");
        final ImageIcon moveIconOriginal = new ImageIcon(MenuViewImpl.class.getResource("/image/view/Move.png"));
        final Image moveImage = moveIconOriginal.getImage();
        final double aspectRatioMove = (double) moveImage.getWidth(null) / moveImage.getHeight(null);

        final int scaledWidthMove = WHIDTH;
        final int scaledHeightMove = (int) (scaledWidthMove / aspectRatioMove);

        final ImageIcon moveIcon = new ImageIcon(
                moveImage.getScaledInstance(scaledWidthMove, scaledHeightMove, Image.SCALE_DEFAULT));

        final JLabel moveLabel = new JLabel(moveIcon);
        buttonPanel.add(start);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(quit);
        final JPanel gridPanel = new JPanel(new GridBagLayout());
        final JPanel horizzontalPanel = new JPanel();
        horizzontalPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        final ImageIcon originalIcon = new ImageIcon(MenuViewImpl.class.getResource("/image/view/PacMan2dot0.png"));
        final Image originalImagePacMan = originalIcon.getImage();
        final double aspectRatioPacMan = (double) originalImagePacMan.getWidth(null) / originalImagePacMan.getHeight(null);

        final int scaledWidthPacMan = WHIDTH;
        final int scaledHeightPacMan = (int) (scaledWidthPacMan / aspectRatioPacMan);

        final ImageIcon pacManIcon = new ImageIcon(
                originalImagePacMan.getScaledInstance(scaledWidthPacMan, scaledHeightPacMan, Image.SCALE_DEFAULT));
        final JLabel titleLabel = new JLabel(pacManIcon);
        titleLabel.setFont(titleLabel.getFont().deriveFont(DERIVE_FONT));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        horizzontalPanel.add(titleLabel, gbc);
        horizzontalPanel.add(Box.createVerticalStrut(100), gbc);
        horizzontalPanel.add(buttonPanel, gbc);
        horizzontalPanel.add(Box.createVerticalStrut(100), gbc);
        horizzontalPanel.add(moveLabel, gbc);
        gridPanel.add(horizzontalPanel);
        this.setLayout(new BorderLayout());
        this.add(gridPanel, BorderLayout.CENTER);
        this.setBackground(Color.BLUE);
        buttonPanel.setOpaque(false);
        horizzontalPanel.setOpaque(false);
        gridPanel.setOpaque(false);

        start.addActionListener(e -> commands.add(MenuCommand.START));
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
     * {@inheritDoc}
     */
    @Override
    public List<MenuCommand> getListCommands() {
        final List<MenuCommand> list = new ArrayList<>(commands);
        commands.clear();
        return list;
    }

}

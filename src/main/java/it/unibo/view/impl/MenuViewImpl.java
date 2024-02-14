package it.unibo.view.impl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.BorderLayout;

import it.unibo.input.api.MenuCommand;
import it.unibo.view.api.MenuView;

/**
 * Swing Implementation of MenuView Interface.
 */
public class MenuViewImpl extends ViewImpl implements MenuView {

    private static final long serialVersionUID = 1L;
    private final List<MenuCommand> commands;
    // private final JLabel imageLabel;
    // private final ImageIcon startIcon = new
    // ImageIcon(MenuView.class.getResource("/image/images.png"));

    /**
     * Constructor of the MenuViewImpl.
     */
    public MenuViewImpl() {
        super();
        commands = new ArrayList<>();
        final JButton start = new JButton();
        start.setText("Start");
        this.setLayout(new BorderLayout());
        this.add(start, BorderLayout.CENTER);
        // this.imageLabel = new JLabel(startIcon);
        // this.add(imageLabel, BorderLayout.SOUTH);
        start.addActionListener(e -> commands.add(MenuCommand.START));
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

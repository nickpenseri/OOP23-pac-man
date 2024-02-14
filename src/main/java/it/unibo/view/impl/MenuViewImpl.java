package it.unibo.view.impl;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.BorderLayout;

import it.unibo.input.api.MenuCommand;
import it.unibo.view.api.MenuView;


public class MenuViewImpl extends ViewImpl implements MenuView{
    private final JButton start;
    private final JButton quit;
    private final List<MenuCommand> commands;
    //private final JLabel imageLabel;
    //private final ImageIcon startIcon = new ImageIcon(MenuView.class.getResource("/image/images.png"));

    public MenuViewImpl(){
        super();
        commands = new ArrayList<>();
        start = new JButton();
        start.setText("Start");
        quit = new JButton();
        quit.setText("Quit");
        this.setLayout(new BorderLayout());
        this.add(start, BorderLayout.CENTER);
        this.add(quit, BorderLayout.SOUTH);
        //this.imageLabel = new JLabel(startIcon);
        //this.add(imageLabel, BorderLayout.SOUTH);

        start.addActionListener(e -> commands.add(MenuCommand.START));
        quit.addActionListener(e -> commands.add(MenuCommand.QUIT));
    }

    @Override
    public List<MenuCommand> getListCommands() {  
        final List<MenuCommand> list = new ArrayList<>(commands);
        commands.clear();
        return list;
    }
    
}

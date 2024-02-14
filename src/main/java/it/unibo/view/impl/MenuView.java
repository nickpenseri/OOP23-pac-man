package it.unibo.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import it.unibo.view.api.View;

public class MenuView extends ViewImpl {
    private final JButton start;
    private final JButton quit;
    //private final JLabel imageLabel;
    //private final ImageIcon startIcon = new ImageIcon(MenuView.class.getResource("/image/images.png"));

    public MenuView(){
        super();
        start = new JButton();
        start.setText("Start");
        quit = new JButton();
        quit.setText("Quit");
        this.setLayout(new BorderLayout());
        this.add(start, BorderLayout.CENTER);
        this.add(quit, BorderLayout.EAST);
        //this.imageLabel = new JLabel(startIcon);
        //this.add(imageLabel, BorderLayout.SOUTH);

        
        quit.addActionListener(e -> System.exit(0));
    }

    @Override
    public void paint(Graphics g) {
    }
    
}

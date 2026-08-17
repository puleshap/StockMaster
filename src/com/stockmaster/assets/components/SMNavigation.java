package com.stockmaster.assets.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class SMNavigation extends JButton {

    private static final int ROUND_CORNER_SIZE = 999;

    public SMNavigation() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gd.setColor(this.getBackground());
        gd.fillRoundRect(0, 0, getWidth(), getHeight(), ROUND_CORNER_SIZE, ROUND_CORNER_SIZE);

        gd.dispose();
        super.paint(g); 
    }
}

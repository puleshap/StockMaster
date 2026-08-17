package com.stockmaster.assets.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Background1 extends JPanel {

    private static final int ROUND_CORNER_SIZE = 35;

    public Background1() {
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

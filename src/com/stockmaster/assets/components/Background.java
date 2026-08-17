package com.stockmaster.assets.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Background extends JPanel {

    private static final int ROUND_CORNER_SIZE = 35;
    private static final int BORDER_THICKNESS = 2;
    private static final Color BORDER_COLOR = new Color(243, 243, 243);

    public Background() {
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gd.setColor(this.getBackground());
        gd.fillRoundRect(0, 0, getWidth(), getHeight(), ROUND_CORNER_SIZE, ROUND_CORNER_SIZE);

        gd.setColor(BORDER_COLOR);
        gd.setStroke(new BasicStroke(BORDER_THICKNESS));
        gd.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ROUND_CORNER_SIZE, ROUND_CORNER_SIZE);

        gd.dispose();
        super.paint(g); 
    }
}

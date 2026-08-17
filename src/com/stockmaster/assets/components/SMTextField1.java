package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;
import java.awt.Color;

public class SMTextField1 extends JTextField {

    public SMTextField1() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,10,5,10");
    }
}

package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;
import java.awt.Color;

public class spicyTextField extends JTextField {

    public spicyTextField() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:25; margin:5,10,5,10");
    }
}

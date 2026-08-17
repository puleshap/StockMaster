package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class SMFormattedField extends JFormattedTextField {

    public SMFormattedField() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:15; margin:5,10,5,10");
    }
}

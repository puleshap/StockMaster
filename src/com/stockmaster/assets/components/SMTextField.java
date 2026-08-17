package com.stockmaster.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JTextField;

public class SMTextField extends JTextField {

    public SMTextField() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:10; margin:5,10,5,10");
    }
}

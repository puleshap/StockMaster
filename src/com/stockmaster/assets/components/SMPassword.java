package com.stockmaster.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPasswordField;

public class SMPassword extends JPasswordField {

    public SMPassword() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:10; margin:5,10,5,10");
    }
}

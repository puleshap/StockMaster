package com.stockmaster.components;

import javax.swing.JButton;
import com.formdev.flatlaf.FlatClientProperties;

public class RoundButton extends JButton {

    public RoundButton() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999;");
    }

}

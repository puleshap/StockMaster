/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author SinuraWahalathanthri
 */
public class SMButton1 extends JButton{
      public SMButton1() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:999;");
    }
}

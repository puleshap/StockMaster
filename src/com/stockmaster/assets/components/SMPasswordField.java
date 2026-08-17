/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JPasswordField;

/**
 *
 * @author SinuraWahalathanthri
 */
public class SMPasswordField extends JPasswordField{
     public SMPasswordField() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:25; margin:5,10,5,10");
    }
}

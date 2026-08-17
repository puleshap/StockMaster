package com.stockmaster.assets.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.imageio.ImageIO;

public class imageLabel extends JButton {
    private Image image;

    public imageLabel() {
        init();
    }

    private void init() {
        this.putClientProperty(FlatClientProperties.STYLE, "arc:9999;");
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                selectImage();
            }
        });
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String ext = getExtension(f);
                return ext != null && (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"));
            }

            @Override
            public String getDescription() {
                return "Image Files (jpg, jpeg, png, gif)";
            }

            private String getExtension(File f) {
                String name = f.getName();
                int dotIndex = name.lastIndexOf('.');
                return (dotIndex == -1) ? null : name.substring(dotIndex + 1).toLowerCase();
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Image img = ImageIO.read(file);
                if (img != null) {
                    // Resize image to fit the button
                    Image scaledImage = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    setImage(scaledImage);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "The selected file is not a valid image.", "Invalid Image", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Error loading image: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int diameter = Math.min(getWidth(), getHeight());
            Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, diameter, diameter);

            g2.setClip(clip);
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        } else {
            super.paintComponent(g);
        }
    }
}

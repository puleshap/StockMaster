/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stockmaster.main.panel;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.cj.protocol.Resultset;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import connection.Sql;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import com.stockmaster.main.Dashboard;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 *
 * @author SinuraWahalathanthri
 */
public class Test extends javax.swing.JPanel {

    /**
     * Creates new form ManageProducts
     */
    private static HashMap<String, Integer> province;
    private static HashMap<String, Integer> district;
    private static HashMap<String, Integer> city;

    public Test() {
        province = new HashMap<>();
        district = new HashMap<>();
        city = new HashMap<>();
        initComponents();

        placeholders_company();
        updateTable();
        load_province_company();
        load_table_company();

    }

    private void placeholders_company() {
        search_t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Company Name or Hotline");
        ImageLabel.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Company Image");
        name.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Company Name");
        owner.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Company Owner");
        address_no_t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Comapany Address No.");
        line1_t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Comapany Line 01");
        line2_t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Comapany Line 02");
        zip.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Zip Code");
        mail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Company Email");
        hotlin.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Hot Line");

    }

    private void load_table_company() {
        try {

            ResultSet rs = Sql.search("SELECT * FROM  `suppliercompany` ORDER BY reg_date DESC");

            DefaultTableModel t = (DefaultTableModel) productTable.getModel();
            t.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("hotline"));
                v.add(rs.getString("name"));
                v.add(rs.getString("email"));
                v.add(rs.getString("owner"));

                v.add(rs.getString("reg_date"));

                t.addRow(v);

            }

        } catch (Exception e) {
        }

    }

    private void load_province_company() {

        try {
            ResultSet rs = Sql.search("SELECT * FROM `province`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                String name = rs.getString("pname");
                Integer id = rs.getInt("id");
                v.add(name);

                province.put(name, id);

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            province_c.setModel(m);
        } catch (Exception e) {
        }

    }

    private void updateTable() {
        JTableHeader tableHeader = productTable.getTableHeader();
        tableHeader.setFont(new Font("Fredoka", Font.PLAIN, 12));
        tableHeader.setBackground(new Color(39, 101, 159));

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Fredoka", Font.BOLD, 13));
                c.setBackground(new Color(39, 101, 159));
                c.setForeground(Color.WHITE);
                return c;
            }
        });
        SwingUtilities.updateComponentTreeUI(productTable);
    }

    String filename = null;
    byte[] person_image = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        supplierRegistration1 = new com.stockmaster.main.panel.SupplierRegistration();
        jPanel2 = new javax.swing.JPanel();
        branchRegistration1 = new com.stockmaster.main.panel.BranchRegistration();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        background11 = new com.stockmaster.assets.components.Background1();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        city_c = new javax.swing.JComboBox<>();
        hotlin = new com.stockmaster.components.SMTextField();
        address_no_t = new com.stockmaster.components.SMTextField();
        district_c = new javax.swing.JComboBox<>();
        name = new com.stockmaster.components.SMTextField();
        mail = new com.stockmaster.components.SMTextField();
        province_c = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        owner = new com.stockmaster.components.SMTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        zip = new com.stockmaster.components.SMTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        line2_t = new com.stockmaster.components.SMTextField();
        jLabel20 = new javax.swing.JLabel();
        line1_t = new com.stockmaster.components.SMTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        ImageLabel = new javax.swing.JLabel();
        sMButton11 = new com.stockmaster.assets.components.SMButton1();
        sMButton21 = new com.stockmaster.assets.components.SMButton2();
        sMSearch2 = new com.stockmaster.assets.components.SMSearch();
        jLabel11 = new javax.swing.JLabel();
        search_t = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sMSearch9 = new com.stockmaster.assets.components.SMSearch();
        jLabel27 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplierRegistration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplierRegistration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Supplier", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(branchRegistration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(branchRegistration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Branches", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        productTable.setFont(new java.awt.Font("Fredoka", 0, 12)); // NOI18N
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hotline", "Name", "Email", "Owner", "Reg_Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(productTable);

        jButton2.setBackground(new java.awt.Color(255, 168, 0));
        jButton2.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 133, 255));
        jButton3.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        city_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));

        district_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        district_c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                district_cItemStateChanged(evt);
            }
        });

        province_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        province_c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                province_cItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("it to the system");

        owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fill all the fields and click the SAVE PRODUCT button to resgister");

        jLabel6.setText("Address No.");

        jLabel10.setText("Company Name");

        jLabel12.setText("Owner");

        jLabel13.setText("Province");

        jLabel14.setText("District");

        jLabel15.setText("City");

        jLabel16.setText("ZipCode");

        jLabel17.setText("Company Email");

        jLabel18.setText("Hotline");

        jLabel20.setText("Line 02");

        jLabel21.setText("Line 01");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        sMButton11.setText("Select Company Logo");
        sMButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMButton11ActionPerformed(evt);
            }
        });

        sMButton21.setText("Clear All");
        sMButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background11Layout = new javax.swing.GroupLayout(background11);
        background11.setLayout(background11Layout);
        background11Layout.setHorizontalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(background11Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(22, 22, 22))
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(background11Layout.createSequentialGroup()
                                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(address_no_t, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(background11Layout.createSequentialGroup()
                                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(121, 121, 121))
                                            .addComponent(line1_t, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(background11Layout.createSequentialGroup()
                                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(owner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(16, 16, 16))
                            .addGroup(background11Layout.createSequentialGroup()
                                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(line2_t, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(district_c, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(city_c, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 14, Short.MAX_VALUE))))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(180, 180, 180))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(province_c, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(220, 220, 220))
                            .addGroup(background11Layout.createSequentialGroup()
                                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background11Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(hotlin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(background11Layout.createSequentialGroup()
                                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(170, 170, 170)))))
                                .addGap(16, 16, 16)))))
                .addContainerGap())
            .addGroup(background11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sMButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sMButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );
        background11Layout.setVerticalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(background11Layout.createSequentialGroup()
                        .addComponent(sMButton21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sMButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(address_no_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(line1_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line2_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(province_c, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(district_c, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(city_c, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hotlin, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/stockmaster/assets/icon/search.png"))); // NOI18N

        search_t.setBackground(new java.awt.Color(246, 246, 246));
        search_t.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        search_t.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(246, 246, 246)));

        javax.swing.GroupLayout sMSearch2Layout = new javax.swing.GroupLayout(sMSearch2);
        sMSearch2.setLayout(sMSearch2Layout);
        sMSearch2Layout.setHorizontalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sMSearch2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_t, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );
        sMSearch2Layout.setVerticalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_t)
        );

        jLabel5.setFont(new java.awt.Font("Inter SemiBold", 0, 20)); // NOI18N
        jLabel5.setText("Manage Companies");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Dashboard / Manage Companies");

        jButton1.setBackground(new java.awt.Color(39, 101, 158));
        jButton1.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sMSearch9.setBackground(new java.awt.Color(42, 82, 125));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/stockmaster/assets/icon/building (2).png"))); // NOI18N

        javax.swing.GroupLayout sMSearch9Layout = new javax.swing.GroupLayout(sMSearch9);
        sMSearch9.setLayout(sMSearch9Layout);
        sMSearch9Layout.setHorizontalGroup(
            sMSearch9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sMSearch9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );
        sMSearch9Layout.setVerticalGroup(
            sMSearch9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sMSearch9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sMSearch9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addComponent(background11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sMSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sMSearch9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addComponent(sMSearch2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(background11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Companies", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        jButton3.setEnabled(false);
        hotlin.setEnabled(false);
        Integer row = productTable.getSelectedRow();
        String hotline = (String) productTable.getValueAt(row, 0);
        String name = (String) productTable.getValueAt(row, 1);
        String email = (String) productTable.getValueAt(row, 2);
        String owner = (String) productTable.getValueAt(row, 3);

        String reg = (String) productTable.getValueAt(row, 4);

        try {
            ;

            ResultSet rs = Sql.search("SELECT * FROM supcomapnyaddress "
                    + "INNER JOIN company_logo ON company_logo.hotline= supcomapnyaddress.supplierCompany_hotline "
                    + "INNER JOIN city ON city.id = supcomapnyaddress.city_id "
                    + "INNER JOIN district ON city.district_id = district.id "
                    + "INNER JOIN province ON district.province_id = province.id "
                    + " WHERE supplierCompany_hotline = '" + hotline + "'");

            while (rs.next()) {
                address_no_t.setText(rs.getString("ano"));
                line1_t.setText(rs.getString("line1"));
                line2_t.setText(rs.getString("line2"));
                zip.setText(rs.getString("zipcode"));
                String imagePath = rs.getString("path");
                filename = imagePath;

                String province = rs.getString("pname");
                String distric = rs.getString("dname");
                String city = rs.getString("name");

                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH);

                ImageLabel.setIcon(new ImageIcon(scaledImage));

                province_c.setSelectedItem(province);
                district_c.setSelectedItem(distric);
                city_c.setSelectedItem(city);

            }
        } catch (Exception ex) {
            Logger.getLogger(CompanyRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.name.setText(name);
        hotlin.setText(hotline);
        mail.setText(email);
        this.owner.setText(owner);

    }//GEN-LAST:event_productTableMouseClicked

    private void productTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_productTableMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String name1 = name.getText();
        String owner1 = owner.getText();
        String email = mail.getText();
        String zipcode = zip.getText();
        String hotline = hotlin.getText();
        String address1 = address_no_t.getText();
        String address2 = line1_t.getText();
        String address3 = line2_t.getText();

        String targetFolderPath = "src/resources/company logos";
        File targetDir = new File(targetFolderPath);

        Integer p = province_c.getSelectedIndex();
        Integer d = district_c.getSelectedIndex();
        Integer c = city_c.getSelectedIndex();

        if (filename == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Company Logo");
            return;
        }
        if (name1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Name");
            return;
        }
        if (owner1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Owner Name");
            return;
        }
        if (address1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address No.");
            return;
        }
        if (address2.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address Line 01");
            return;
        }
        if (address3.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address Line 02");
            return;
        }
        if (p == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Province");
            return;
        }
        if (d == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select District");
            return;
        }

        if (zipcode.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Zipcode");
            return;
        }
        if (c == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select City");
            return;
        }
        if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Email");
            return;
        }

        if (hotline.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Hotline");
            return;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            ResultSet rs = Sql.search("SELECT * FROM suppliercompany WHERE `name`= '" + name1 + "' AND `hotline` !='" + hotline + "' ");
            while (rs.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Company Name Already Exists for a Different Company.");
                return;
            }
            ResultSet rs2 = Sql.search("SELECT * FROM suppliercompany WHERE `hotline`= '" + hotline + "'  ");
            if (rs2.next()) {
                String fileExtension = filename.substring(filename.lastIndexOf("."));
                File targetFile = new File(targetDir, name1 + fileExtension);
                Files.copy(new File(filename).toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                String relativePath = targetFile.getPath().replace("\\", "/"); // Use forward slashes for consistency
                System.out.println(relativePath);
                //
                Sql.iud("UPDATE suppliercompany SET "
                        + " `name` = '" + name1 + "' "
                        + ", `owner` = '" + owner1 + "' "
                        + ", `email` = '" + email + "' "
                        + " WHERE `hotline` = '" + hotline + "'");

                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Company Registered Succressfully");
                Sql.iud("UPDATE company_logo SET `path` = '" + relativePath + "' WHERE `hotline` = '" + hotline + "'");

                String combo = (String) city_c.getSelectedItem();
                Integer cit = city.get(combo);
                //
                Sql.iud("UPDATE supcomapnyaddress SET"
                        + " `ano` = '" + address1 + "',"
                        + " `line1` = '" + address2 + "',"
                        + " `line2` = '" + address3 + "',"
                        + " `city_id` = '" + cit + "', "
                        + "`zipcode` = '" + zipcode + "'"
                        + " WHERE `supplierCompany_hotline` = '" + hotline + "'");

                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Company Address Registered Succressfully");

                load_table_company();
            } else if (!rs2.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Company HOtline Doesnot Exist.");
                return;
            }
            //

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        UUID uniqueID = UUID.randomUUID();
        String ID = uniqueID.toString().substring(0, 8);

        String targetFolderPath = "src/resources/company logos";
        File targetDir = new File(targetFolderPath);

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        String name1 = name.getText();
        String owner1 = owner.getText();
        String email = mail.getText();
        String zipcode = zip.getText();
        String hotline = hotlin.getText();
        String address1 = address_no_t.getText();
        String address2 = line1_t.getText();
        String address3 = line2_t.getText();

        Integer p = province_c.getSelectedIndex();
        Integer d = district_c.getSelectedIndex();
        Integer c = city_c.getSelectedIndex();

        if (filename == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Company Logo");
            return;
        }
        if (name1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Name");
            return;
        }
        if (owner1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Owner Name");
            return;
        }
        if (address1.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address No.");
            return;
        }
        if (address2.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address Line 01");
            return;
        }
        if (address3.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Address Line 02");
            return;
        }
        if (p == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Province");
            return;
        }
        if (d == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select District");
            return;
        }

        if (zipcode.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Zipcode");
            return;
        }
        if (c == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select City");
            return;
        }
        if (email.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Email");
            return;
        }
        if (!email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Valid Email Address");
            return;
        }

        if (hotline.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Company Hotline");
            return;
        }
        if (!hotline.matches("0((11)|(2(1|[3-7]))|(3[1-8])|(4(1|5|7))|(5(1|2|4|5|7))|(6(3|[5-7]))|([8-9]1))[0-9]{7}")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Proper Hotline");
            return;
        }

        try {

            ResultSet rs = Sql.search("SELECT * FROM suppliercompany WHERE `name`= '" + name1 + "'  ");
            while (rs.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Company Name Already Exists.");
                return;
            }
            ResultSet rs2 = Sql.search("SELECT * FROM suppliercompany WHERE `hotline`= '" + hotline + "'  ");
            while (rs2.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Company HOtline Already Exists.");
                return;
            }

            String fileExtension = filename.substring(filename.lastIndexOf("."));
            File targetFile = new File(targetDir, name1 + fileExtension);
            Files.copy(new File(filename).toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String relativePath = targetFile.getPath().replace("\\", "/"); // Use forward slashes for consistency
            System.out.println(relativePath);

            Sql.iud("INSERT INTO suppliercompany (`name`,`owner`,`hotline`,`email`,`reg_date`) VALUES( '" + name1 + "', '" + owner1 + "', '" + hotline + "', '" + email + "', CURRENT_DATE)  ");
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Company Registered Succressfully");
            Sql.iud("INSERT INTO company_logo (path, hotline) VALUES ('" + relativePath + "', '" + hotline + "')");
            String combo = (String) city_c.getSelectedItem();
            Integer cit = city.get(combo);

            Sql.iud("INSERT INTO supcomapnyaddress (`ano`,`line1`,`line2`,`supplierCompany_hotline`,`city_id`,`zipcode`) VALUES('" + address1 + "', '" + address2 + "', '" + address3 + "', '" + hotline + "', '" + cit + "','" + zipcode + "')  ");
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Company Address Registered Succressfully");

            load_table_company();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void district_cItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_district_cItemStateChanged

        try {
            String combo = (String) district_c.getSelectedItem();
            Integer id = district.get(combo);

            ResultSet rs = Sql.search("SELECT * FROM city where district_id = '" + id + "'  ");

            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                String name = rs.getString("name");
                Integer idd = rs.getInt("id");
                v.add(name);
                city.put(name, idd);

            }
            DefaultComboBoxModel c = new DefaultComboBoxModel(v);
            city_c.setModel(c);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_district_cItemStateChanged

    private void province_cItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_province_cItemStateChanged

        try {
            String combo = (String) province_c.getSelectedItem();
            Integer id = province.get(combo);

            ResultSet rs = Sql.search("SELECT * FROM district where province_id = '" + id + "'  ");

            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                String name = rs.getString("dname");
                Integer idd = rs.getInt("id");
                v.add(name);

                district.put(name, idd);

            }
            DefaultComboBoxModel c = new DefaultComboBoxModel(v);
            district_c.setModel(c);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_province_cItemStateChanged

    private void ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ownerActionPerformed

    private void sMButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton11ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File file = chooser.getSelectedFile();
        if (file != null) {
            filename = file.getAbsolutePath();
            ImageIcon originalIcon = new ImageIcon(filename);
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(ImageLabel.getWidth(), ImageLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageLabel = new ImageIcon(scaledImage);
            ImageLabel.setIcon(imageLabel);

            try ( FileInputStream fis = new FileInputStream(file)) {
                person_image = new byte[(int) file.length()];
                fis.read(person_image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_sMButton11ActionPerformed

    private void sMButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton21ActionPerformed

        jButton3.setEnabled(true);
        hotlin.setEnabled(true);

        ImageLabel.setIcon(null);
        filename = null;

        province_c.setSelectedItem("Select");
        district_c.setSelectedItem("Select");
        city_c.setSelectedItem("Select");
        search_t.setText("");
        this.zip.setText("");
        this.name.setText("");
        address_no_t.setText("");
        this.line1_t.setText("");
        this.line2_t.setText("");
        hotlin.setText("");
        mail.setText("");
        this.owner.setText("");
        load_table_company();
    }//GEN-LAST:event_sMButton21ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String search = search_t.getText();

        if (search.matches("\\d+")) {
            try {
                boolean hasresults = false;
                ResultSet rs = Sql.search("SELECT * FROM `suppliercompany` WHERE  hotline LIKE '%" + search + "%' ");
                DefaultTableModel table = (DefaultTableModel) productTable.getModel();
                table.setRowCount(0);
                while (rs.next()) {
                    hasresults = true;

                    Vector v = new Vector();
                    v.add(rs.getString("hotline"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("owner"));

                    v.add(rs.getString("reg_date"));

                    table.addRow(v);

                }
                if (!hasresults) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Searched hotline does not Exist.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                boolean hasresults = false;
                ResultSet rs = Sql.search("SELECT * FROM `suppliercompany` WHERE name LIKE '%" + search + "%' ");
                DefaultTableModel table = (DefaultTableModel) productTable.getModel();
                table.setRowCount(0);
                while (rs.next()) {
                    hasresults = true;

                    Vector v = new Vector();
                    v.add(rs.getString("hotline"));
                    v.add(rs.getString("name"));
                    v.add(rs.getString("email"));
                    v.add(rs.getString("owner"));

                    v.add(rs.getString("reg_date"));

                    table.addRow(v);
                }
                if (!hasresults) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Searched Name does not Exist.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ImageLabel;
    private com.stockmaster.components.SMTextField address_no_t;
    private com.stockmaster.assets.components.Background1 background11;
    private com.stockmaster.main.panel.BranchRegistration branchRegistration1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> city_c;
    private javax.swing.JComboBox<String> district_c;
    private com.stockmaster.components.SMTextField hotlin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.stockmaster.components.SMTextField line1_t;
    private com.stockmaster.components.SMTextField line2_t;
    private com.stockmaster.components.SMTextField mail;
    private com.stockmaster.components.SMTextField name;
    private com.stockmaster.components.SMTextField owner;
    private javax.swing.JTable productTable;
    private javax.swing.JComboBox<String> province_c;
    private com.stockmaster.assets.components.SMButton1 sMButton11;
    private com.stockmaster.assets.components.SMButton2 sMButton21;
    private com.stockmaster.assets.components.SMSearch sMSearch2;
    private com.stockmaster.assets.components.SMSearch sMSearch9;
    private javax.swing.JTextField search_t;
    private com.stockmaster.main.panel.SupplierRegistration supplierRegistration1;
    private com.stockmaster.components.SMTextField zip;
    // End of variables declaration//GEN-END:variables
}

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
import java.sql.SQLException;
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
public class SupplierRegistration extends javax.swing.JPanel {

    /**
     * Creates new form CompanyRegistration
     */
    private static HashMap<String, String> branch;
    private static HashMap<String, String> company;

    public SupplierRegistration() {

        branch = new HashMap<>();
        company = new HashMap<>();

        initComponents();

        placeholders();
        updateTable();

        load_table();
        load_company();

    }

    private void placeholders() {
        search_t.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Mobile or Email");
        mobile.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mobile Number");
        fn.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        ln.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");

        mail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email Address");

    }
    String firstname;
    String lastname;

    private void load_table() {
        try {

            ResultSet rs = Sql.search("SELECT supplier.*, supplierbranch.name AS branch, "
                    + "suppliercompany.name AS companyname "
                    + " FROM supplier  "
                    + "INNER JOIN supplierbranch ON supplierbranch.hotline = supplierBranch_hotline "
                    + "INNER JOIN suppliercompany ON suppliercompany.hotline = supplierbranch.supplierCompany_hotline");

            DefaultTableModel t = (DefaultTableModel) productTable.getModel();
            t.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("mobile"));
                String first = rs.getString("fname");
                String last = rs.getString("lname");
                v.add(first + " " + last);
                v.add(rs.getString("email"));
                v.add(rs.getString("branch"));

                v.add(rs.getString("companyname"));

                t.addRow(v);

            }

        } catch (Exception e) {
        }

    }

    private void load_company() {

        try {
            ResultSet rs = Sql.search("SELECT * FROM `suppliercompany`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                String name = rs.getString("name");
                String id = rs.getString("hotline");
                v.add(name);

                company.put(name, id);

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            company_c.setModel(m);
        } catch (Exception e) {
        }

    }

    private void loadbranch() {

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        background11 = new com.stockmaster.assets.components.Background1();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        fn = new com.stockmaster.components.SMTextField();
        mobile = new com.stockmaster.components.SMTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ln = new com.stockmaster.components.SMTextField();
        jLabel20 = new javax.swing.JLabel();
        mail = new com.stockmaster.components.SMTextField();
        jLabel9 = new javax.swing.JLabel();
        company_c = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        branch_c = new javax.swing.JComboBox<>();
        sMButton21 = new com.stockmaster.assets.components.SMButton2();
        sMSearch2 = new com.stockmaster.assets.components.SMSearch();
        jLabel11 = new javax.swing.JLabel();
        search_t = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sMSearch9 = new com.stockmaster.assets.components.SMSearch();
        jLabel27 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(36, 93, 146));

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
                "Mobile", "Name", "Email", "Branch", "Company"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
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

        jLabel8.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("to resgister it to the system");

        jLabel7.setFont(new java.awt.Font("Inter", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Fill all the fields and click the SAVE PRODUCT button ");

        jLabel6.setText("First Name");

        jLabel10.setText("Supplier Mobile");

        jLabel20.setText("Last Name");

        jLabel9.setText("Email");

        company_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        company_c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                company_cItemStateChanged(evt);
            }
        });

        jLabel12.setText("Select Company");

        jLabel22.setText("Select Branch");

        branch_c.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        branch_c.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                branch_cItemStateChanged(evt);
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
            .addGroup(background11Layout.createSequentialGroup()
                .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(16, 16, 16))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(background11Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ln, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(branch_c, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(company_c, javax.swing.GroupLayout.Alignment.LEADING, 0, 174, Short.MAX_VALUE))
                            .addComponent(sMButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        background11Layout.setVerticalGroup(
            background11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background11Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(sMButton21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(company_c, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(branch_c, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(search_t)
                .addContainerGap())
        );
        sMSearch2Layout.setVerticalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_t)
        );

        jLabel5.setFont(new java.awt.Font("Inter SemiBold", 0, 20)); // NOI18N
        jLabel5.setText("Manage Suppliers");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Dashboard / Manage Suppliers");

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
                    .addComponent(background11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(sMSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sMButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sMButton3ActionPerformed

    private void sMButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sMButton2ActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        jButton3.setEnabled(false);
        mobile.setEnabled(false);

        Integer row = productTable.getSelectedRow();
        String mobile = (String) productTable.getValueAt(row, 0);
        String name = (String) productTable.getValueAt(row, 1);
        String email = (String) productTable.getValueAt(row, 2);

        this.mobile.setText(mobile);
        this.mail.setText(email);

        try {

            ResultSet rs = Sql.search("SELECT supplier.*,supplierbranch.name AS branch, "
                    + "suppliercompany.name AS companyn "
                    + " FROM supplier "
                    + "INNER JOIN supplierbranch ON supplierbranch.hotline = supplierBranch_hotline "
                    + "INNER JOIN suppliercompany ON suppliercompany.hotline=company "
                    + " WHERE mobile = '" + mobile + "'"
            );

            while (rs.next()) {

                String fn = rs.getString("fname");
                String ln = rs.getString("lname");
                this.fn.setText(fn);
                this.ln.setText(fn);
                String cm = rs.getString("companyn");
                String br = rs.getString("branch");

                company_c.setSelectedItem(cm);
                branch_c.setSelectedItem(br);

            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_productTableMouseClicked

    private void productTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_productTableMouseEntered

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String m = mobile.getText();
        String f = fn.getText();
        String l = ln.getText();
        String e = mail.getText();

        Integer b = branch_c.getSelectedIndex();

        Integer co = company_c.getSelectedIndex();

        if (m.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Mobile Number");
            return;
        }
        if (!m.matches("^(0{1})(7{1})([0|1|2|4|5|6|7|8]{1})([0-9]{7})")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Valid Mobile Number");
            return;
        }

        if (f.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter First Name");
            return;
        }
        if (l.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Last Name");
            return;
        }
        if (e.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Email Address");
            return;
        }
        if (!e.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Valid Email Address");
            return;
        }
        if (co == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Company");
            return;
        }
        if (b == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Branch");
            return;
        }

        try {

            ResultSet rs = Sql.search("SELECT * FROM supplier WHERE `mobile`= '" + m + "'  ");
            while (rs.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Supplier Mobile Number Already Exists.");
                return;
            }
            ResultSet rs2 = Sql.search("SELECT * FROM supplier WHERE `email`= '" + e + "'  ");
            while (rs2.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Supplier Email Already Exists.");
                return;
            }

            String combo1 = (String) company_c.getSelectedItem();
            String com = company.get(combo1);

            String combo = (String) branch_c.getSelectedItem();
            String cit = branch.get(combo);
            System.out.println(com + " " + cit);
            Sql.iud("INSERT INTO supplier (`mobile`,`fname`,`lname`,`email`,`supplierBranch_hotline`,`company`) "
                    + " VALUES( '" + m + "', '" + f + "', '" + l + "', '" + e + "', '" + cit + "','" + com + "' )  ");

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Supplier Registered Succressfully");

            load_table();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String m = mobile.getText();
        String f = fn.getText();
        String l = ln.getText();
        String e = mail.getText();

        Integer b = branch_c.getSelectedIndex();

        Integer co = company_c.getSelectedIndex();

        if (m.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Mobile Number");
            return;
        }

        if (f.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter First Name");
            return;
        }
        if (l.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Last Name");
            return;
        }
        if (e.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Enter Email Address");
            return;
        }
        if (co == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Company");
            return;
        }
        if (b == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Select Branch");
            return;
        }
        try {

            ResultSet rs = Sql.search("SELECT * FROM supplier WHERE `email`= '" + e + "' AND `mobile` !='" + m + "' ");
            while (rs.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Email Already Exists for a Different Supplier.");
                return;
            }
            ResultSet rs2 = Sql.search("SELECT * FROM supplier WHERE `mobile`= '" + m + "'  ");
            if (rs2.next()) {
                String combo1 = (String) company_c.getSelectedItem();
                String com = company.get(combo1);
                String combo = (String) branch_c.getSelectedItem();
                String cit = branch.get(combo);
                //
                Sql.iud("UPDATE supplier SET"
                        + " `fname` = '" + f + "',"
                        + " `lname` = '" + l + "',"
                        + " `email` = '" + e + "',"
                        + " `supplierBranch_hotline` = '" + cit + "',"
                        + " `company` = '" + com + "'"
                        + " WHERE `mobile` = '" + m + "'");

                //
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Supplier  Updated Succressfully");

                load_table();
            } else if (!rs2.next()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Supplier Mobile Doesnot Exist.");
                return;
            }
            //

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void company_cItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_company_cItemStateChanged
        try {

            String combo = (String) company_c.getSelectedItem();
            String id = company.get(combo);
            ResultSet rs = Sql.search("SELECT *,supplierbranch.hotline AS sp_hot FROM `supplierbranch` WHERE supplierCompany_hotline='" + id + "'  ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                String name = rs.getString("name");
                Integer n = rs.getInt("sp_hot");
                String idd = rs.getString("sp_hot");

                v.add(name);

                branch.put(name, idd);

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            branch_c.setModel(m);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_company_cItemStateChanged

    private void branch_cItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_branch_cItemStateChanged

    }//GEN-LAST:event_branch_cItemStateChanged

    private void sMButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton21ActionPerformed

        jButton3.setEnabled(true);
        mobile.setEnabled(true);

        company_c.setSelectedItem("Select");
        branch_c.setSelectedItem("Select");

        search_t.setText("");
        this.mobile.setText("");
        this.fn.setText("");
        ln.setText("");
        this.mail.setText("");

        load_table();
    }//GEN-LAST:event_sMButton21ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String search = search_t.getText();

        if (search.matches("\\d+")) {
            try {
                boolean hasresults = false;
                ResultSet rs = Sql.search("SELECT supplier.*,supplierbranch.name AS branch, "
                        + "suppliercompany.name AS companyn "
                        + " FROM supplier "
                        + "INNER JOIN supplierbranch ON supplierbranch.hotline = supplierBranch_hotline "
                        + "INNER JOIN suppliercompany ON suppliercompany.hotline=company "
                        + "WHERE  mobile LIKE '%" + search + "%' "
                );
                DefaultTableModel table = (DefaultTableModel) productTable.getModel();
                table.setRowCount(0);
                while (rs.next()) {
                    hasresults = true;

                    Vector v = new Vector();

                    v.add(rs.getString("mobile"));
                    String first = rs.getString("fname");
                    String last = rs.getString("lname");
                    v.add(first + " " + last);
                    v.add(rs.getString("email"));
                    v.add(rs.getString("branch"));

                    v.add(rs.getString("companyn"));

                    table.addRow(v);

                }
                if (!hasresults) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Searched Mobile does not Exist.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                boolean hasresults = false;
                ResultSet rs = Sql.search("SELECT supplier.*,supplierbranch.name AS branch, "
                        + "suppliercompany.name AS companyn "
                        + " FROM supplier "
                        + "INNER JOIN supplierbranch ON supplierbranch.hotline = supplierBranch_hotline "
                        + "INNER JOIN suppliercompany ON suppliercompany.hotline=company "
                        + "WHERE supplier.email LIKE '%" + search + "%' ");
                DefaultTableModel table = (DefaultTableModel) productTable.getModel();
                table.setRowCount(0);
                while (rs.next()) {
                    hasresults = true;

                    Vector v = new Vector();

                    v.add(rs.getString("mobile"));
                    String first = rs.getString("fname");
                    String last = rs.getString("lname");
                    v.add(first + " " + last);
                    v.add(rs.getString("email"));
                    v.add(rs.getString("branch"));

                    v.add(rs.getString("companyn"));

                    table.addRow(v);
                }
                if (!hasresults) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Searched Email does not Exist.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    String filename = null;
    byte[] person_image = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.stockmaster.assets.components.Background1 background11;
    private javax.swing.JComboBox<String> branch_c;
    private javax.swing.JComboBox<String> company_c;
    private com.stockmaster.components.SMTextField fn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.stockmaster.components.SMTextField ln;
    private com.stockmaster.components.SMTextField mail;
    private com.stockmaster.components.SMTextField mobile;
    private javax.swing.JTable productTable;
    private com.stockmaster.assets.components.SMButton2 sMButton21;
    private com.stockmaster.assets.components.SMSearch sMSearch2;
    private com.stockmaster.assets.components.SMSearch sMSearch9;
    private javax.swing.JTextField search_t;
    // End of variables declaration//GEN-END:variables
}

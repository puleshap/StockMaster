/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stockmaster.main.panel;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.stockmaster.main.Dashboard;
import com.stockmaster.assets.components.jDialogs.UpdateStockPrice;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;
import com.stockmaster.main.Dashboard;

import connection.Sql;

/**
 *
 * @author Vinudhi
 */
public class ManageStock extends javax.swing.JPanel {

    

    public ManageStock() {
        initComponents();
        

    }

    private void searchStockProducts(String searchProduct) {
        try {
            String query = "SELECT * FROM `stock` "
                    + "INNER JOIN `products` ON `stock`.`products_id` = `products`.`id` "
                    + "INNER JOIN `category` ON `products`.`category_id` = `category`.`id` "
                    + "INNER JOIN `sub_category` ON `products`.`sub_category_id` = `sub_category`.`id` "
                    + "WHERE `products`.`product` LIKE '%" + searchProduct + "%' "
                    + "OR `category`.`category` LIKE '%" + searchProduct + "%' "
                    + "OR `stock`.`id` LIKE '%" + searchProduct + "%' "
                    + "OR `products`.`id` LIKE '%" + searchProduct + "%' "
                    + "OR `sub_category`.`sub_category` LIKE '%" + searchProduct + "%'";

            ResultSet resultset = Sql.search(query);
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock.id"));
                stocklist.add(resultset.getString("products_id"));
                stocklist.add(resultset.getString("products.product"));
                stocklist.add(resultset.getString("category.category"));
                stocklist.add(resultset.getString("sub_category.sub_category"));
                stocklist.add(resultset.getString("qty"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("buying_price"));
                stocklist.add(resultset.getString("mfd"));
                stocklist.add(resultset.getString("exp"));

                modal.addRow(stocklist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTable() {
        JTableHeader tableHeader = checkStockTable.getTableHeader();
        tableHeader.setFont(new Font("Fredoka", Font.PLAIN, 12));
        tableHeader.setBackground(new Color(131, 198, 148));

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Fredoka", Font.PLAIN, 13));
                c.setBackground(new Color(131, 198, 148));
                c.setForeground(Color.WHITE);
                return c;
            }
        });

        SwingUtilities.updateComponentTreeUI(checkStockTable);
    }

    private void loadTable() {
        try {
            ResultSet resultset = Sql.search("SELECT * FROM `stock` "
                    + "INNER JOIN `products` ON `stock`.`products_id`=`products`.`id` "
                    + "INNER JOIN `category` ON `products`.`category_id`=`category`.`id` "
                    + "INNER JOIN `sub_category` ON `products`.`sub_category_id`=`sub_category`.`id`");
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock.id"));
                stocklist.add(resultset.getString("products_id"));
                stocklist.add(resultset.getString("products.product"));
                stocklist.add(resultset.getString("category.category"));
                stocklist.add(resultset.getString("sub_category.sub_category"));
                stocklist.add(resultset.getString("qty"));
                stocklist.add(resultset.getString("buying_price"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("mfd"));
                stocklist.add(resultset.getString("exp"));

                modal.addRow(stocklist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading stock data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void checkStock() {

        int row = checkStockTable.getSelectedRow();
        String query = "SELECT `stock`.`id`, `products`.`id` AS product_id,"
                + " `products`.`product`, `stock`.`selling_price`, `stock`.`qty`,"
                + " `stock`.`mfd`, `stock`.`exp`, `category`.`category` AS category,"
                + " `sub_category`.`sub_category` AS `sub_category`, `stock`.`buying_price` AS `buying_price`"
                + "FROM `stock` "
                + "INNER JOIN `products` ON `stock`.`products_id` = `products`.`id` "
                + "INNER JOIN `category` ON `products`.`category_id` = `category`.`id` "
                + "INNER JOIN `sub_category` ON `products`.`sub_category_id`=`sub_category`.`id`";

        if (row != -1) {
            String pid = String.valueOf(checkStockTable.getValueAt(row, 1));
            query += "WHERE `stock`.`products_id`='" + pid + "' ";
        }

        boolean hasWhere = query.contains("WHERE");

        double min_price = 0;
        double max_price = 0;

        try {
            if (!spicyFormattedField1.getText().isEmpty()) {
                min_price = Double.parseDouble(spicyFormattedField1.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid minimum price.");
            return;
        }

        try {
            if (!spicyFormattedField2.getText().isEmpty()) {
                max_price = Double.parseDouble(spicyFormattedField2.getText());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid maximum price.");
            return;
        }

        if (min_price > 0) {
            if (!hasWhere) {
                query += "WHERE ";
                hasWhere = true;
            } else {
                query += "AND ";
            }
            query += "`stock`.`selling_price` >= " + min_price + " ";
        }

        if (max_price > 0) {
            if (!hasWhere) {
                query += "WHERE ";
                hasWhere = true;
            } else {
                query += "AND ";
            }
            query += "`stock`.`selling_price` <= " + max_price + " ";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (expfrom.getDate() != null) {
            Date start = expfrom.getDate();
            if (!hasWhere) {
                query += "WHERE";
                hasWhere = true;
            } else {
                query += "AND ";
            }
            query += "`stock`.`exp` >= '" + format.format(start) + "' ";
        }

        if (expto.getDate() != null) {
            Date end = expto.getDate();
            if (!hasWhere) {
                query += "WHERE ";
                hasWhere = true;
            } else {
                query += "AND ";
            }
            query += "`stock`.`exp` <= '" + format.format(end) + "' ";
        }

        String sort = String.valueOf(sortCombo.getSelectedItem());
        query += "ORDER BY ";

        if (sort.equals("Category ASC")) {
            query += "`category`.`category` ASC";
        } else if (sort.equals("Category DESC")) {
            query += "`category`.`category` DESC";
        } else if (sort.equals("Name ASC")) {
            query += "`products`.`product` ASC";
        } else if (sort.equals("Name DESC")) {
            query += "`products`.`product` DESC";
        } else if (sort.equals("Selling Price ASC")) {
            query += "`stock`.`selling_price` ASC";
        } else if (sort.equals("Selling Price DESC")) {
            query += "`stock`.`selling_price` DESC";
        } else if (sort.equals("Quantity ASC")) {
            query += "`stock`.`qty` ASC";
        } else if (sort.equals("Quantity DESC")) {
            query += "`stock`.`qty` DESC";
        } else {
            query += "`stock`.`id` ASC";
        }

        System.out.println("Executing query: " + query);

        try {
            ResultSet rs = Sql.search(query);

            if (rs == null) {
                JOptionPane.showMessageDialog(null, "No results found.");
                return;
            }

            DefaultTableModel tableModel = (DefaultTableModel) checkStockTable.getModel();
            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("stock.id"));
                vector.add(rs.getString("product_id"));
                vector.add(rs.getString("products.product"));
                vector.add(rs.getString("category"));
                vector.add(rs.getString("sub_category"));
                vector.add(rs.getString("qty"));
                vector.add(rs.getString("buying_price"));
                vector.add(rs.getString("selling_price"));
                vector.add(rs.getString("mfd"));
                vector.add(rs.getString("exp"));

                tableModel.addRow(vector);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageStock.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        checkStockTable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        sortCombo = new javax.swing.JComboBox<>();
        expfrom = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        expto = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        spicyFormattedField1 = new javax.swing.JTextField();
        spicyFormattedField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buying_price = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        selling_price = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        product_name = new javax.swing.JTextField();
        stock_id = new javax.swing.JTextField();
        sMSearch2 = new com.stockmaster.assets.components.SMSearch();
        jLabel15 = new javax.swing.JLabel();
        search_t = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Fredoka", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Manage Product Stock");

        jLabel5.setFont(new java.awt.Font("Fredoka", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Dashboard / Products / Manage Product Stock");

        jToggleButton1.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton1.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Print Stock");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        checkStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stock Id", "Product Id", "Product", "Category", "Sub Category", "Stock Quantity", "Buying Price", "Selling Price", "MFD", "EXP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        checkStockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkStockTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(checkStockTable);
        if (checkStockTable.getColumnModel().getColumnCount() > 0) {
            checkStockTable.getColumnModel().getColumn(0).setResizable(false);
            checkStockTable.getColumnModel().getColumn(1).setResizable(false);
            checkStockTable.getColumnModel().getColumn(2).setResizable(false);
            checkStockTable.getColumnModel().getColumn(3).setResizable(false);
            checkStockTable.getColumnModel().getColumn(4).setResizable(false);
            checkStockTable.getColumnModel().getColumn(5).setResizable(false);
            checkStockTable.getColumnModel().getColumn(6).setResizable(false);
            checkStockTable.getColumnModel().getColumn(7).setResizable(false);
            checkStockTable.getColumnModel().getColumn(8).setResizable(false);
            checkStockTable.getColumnModel().getColumn(9).setResizable(false);
        }

        jButton4.setBackground(new java.awt.Color(238, 238, 238));
        jButton4.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 51));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/jiat/medicom/icons/reload (2).png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));

        sortCombo.setForeground(new java.awt.Color(0, 0, 0));
        sortCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Catgory ASC", "Catgory DESC", "Name ASC", "Name DESC", "Selling Price ASC", "Selling Price DESC", "Quantity ASC", "Quantity DESC" }));
        sortCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortComboItemStateChanged(evt);
            }
        });

        expfrom.setForeground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Max");

        jLabel12.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Expire Date");

        jLabel3.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Selling Price");

        jLabel4.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Min");

        expto.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sort Products By");

        jLabel14.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("To");

        jButton2.setBackground(new java.awt.Color(131, 198, 144));
        jButton2.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(131, 198, 144));
        jButton3.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Find");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        spicyFormattedField1.setEditable(false);

        spicyFormattedField2.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spicyFormattedField1))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sortCombo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(expfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(expto, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spicyFormattedField2)
                                .addGap(107, 107, 107)
                                .addComponent(jButton2)))))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spicyFormattedField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spicyFormattedField2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(expfrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sortCombo)
                            .addComponent(expto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(232, 239, 234));

        jLabel9.setFont(new java.awt.Font("Fredoka Medium", 0, 14)); // NOI18N
        jLabel9.setText("Update Stock Price ");

        jLabel11.setFont(new java.awt.Font("Fredoka", 0, 12)); // NOI18N
        jLabel11.setText("Buying Price");

        buying_price.setEditable(false);
        buying_price.setFont(new java.awt.Font("Fredoka", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Fredoka", 0, 12)); // NOI18N
        jLabel13.setText("Selling Price");

        selling_price.setFont(new java.awt.Font("Fredoka", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(143, 189, 156));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("✅");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        product_name.setEditable(false);

        stock_id.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buying_price, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stock_id))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(selling_price, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(product_name))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stock_id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buying_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selling_price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/stockmaster/assets/icon/search.png"))); // NOI18N

        search_t.setBackground(new java.awt.Color(246, 246, 246));
        search_t.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        search_t.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(246, 246, 246)));

        javax.swing.GroupLayout sMSearch2Layout = new javax.swing.GroupLayout(sMSearch2);
        sMSearch2.setLayout(sMSearch2Layout);
        sMSearch2Layout.setHorizontalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sMSearch2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_t, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );
        sMSearch2Layout.setVerticalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_t, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(157, 157, 157)
                        .addComponent(jButton5)
                        .addGap(69, 69, 69)
                        .addComponent(sMSearch2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jButton5))))
                    .addComponent(sMSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sortComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortComboItemStateChanged
        checkStock();
    }//GEN-LAST:event_sortComboItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String price_min = spicyFormattedField1.getText();
        String price_max = spicyFormattedField2.getText();

        if (price_min.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter the minimun price!");
        } else if (price_max.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter the maximum price!");
        } else if (Double.parseDouble(price_min) > Double.parseDouble(price_max)) {
            JOptionPane.showMessageDialog(this, "'Min' Price Greater than 'Max' Price", "Warning", JOptionPane.INFORMATION_MESSAGE);
            spicyFormattedField1.setText("");
            checkStock();
        } else {
            checkStock();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Date date1 = expfrom.getDate();
        Date date2 = expto.getDate();

        if (date1 == null) {
            JOptionPane.showMessageDialog(this, "Please select starting date", "Date", JOptionPane.INFORMATION_MESSAGE);
        } else if (date2 == null) {
            JOptionPane.showMessageDialog(this, "Please select ending date", "Date", JOptionPane.INFORMATION_MESSAGE);
        } else if (date1.after(date2)) {
            JOptionPane.showMessageDialog(this, "Starting date is greater-than the Ending date", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            checkStock();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        sortCombo.setSelectedIndex(0);
        spicyFormattedField1.setText("");
        spicyFormattedField2.setText("");
        expfrom.setDate(null);
        expto.setDate(null);
        loadTable();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void checkStockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkStockTableMouseClicked
        int row = checkStockTable.getSelectedRow();
        if (evt.getClickCount() == 2) {
            stock_id.setText(String.valueOf(checkStockTable.getValueAt(row, 0)));
            product_name.setText(String.valueOf(checkStockTable.getValueAt(row, 2)));
            buying_price.setText(String.valueOf(checkStockTable.getValueAt(row, 6)));
            selling_price.setText(String.valueOf(checkStockTable.getValueAt(row, 7)));
        }
    }//GEN-LAST:event_checkStockTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String stockid = stock_id.getText();
        String pname = product_name.getText();
        String buyingPrice = buying_price.getText();
        String sellingPrice = this.selling_price.getText();

        if (stockid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Select A Stock Product", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (buyingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Buying Price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (sellingPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Selling Price", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = Sql.search("SELECT * FROM stock WHERE id='" + stockid + "' AND `buying_price`='" + buyingPrice + "' AND `selling_price`='" + sellingPrice + "'");

                boolean canUpdate = true;

                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "You havent Updated Anything");
                    canUpdate = false;
                }
                if (canUpdate) {
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the stock's pricing details?",
                            "Confirm Update", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.OK_OPTION) {
                        Sql.iud("UPDATE stock SET "
                                + "selling_price='" + sellingPrice + "' "
                                + "WHERE id='" + stockid + "'");

                        loadTable();

                        stock_id.setText("");
                        product_name.setText("");
                        buying_price.setText("");
                        selling_price.setText("");

                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Stock details updated successfully!");
                    }
                }
            } catch (SQLException ex) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, ex.toString());
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String path = "src\\lk\\jiat\\medicom\\reports\\Medicom_Stock.jasper";
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, Sql.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            System.err.println("Error loading the report: " + e.getMessage());
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buying_price;
    private javax.swing.JTable checkStockTable;
    private com.toedter.calendar.JDateChooser expfrom;
    private com.toedter.calendar.JDateChooser expto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField product_name;
    private com.stockmaster.assets.components.SMSearch sMSearch2;
    private javax.swing.JTextField search_t;
    private javax.swing.JTextField selling_price;
    private javax.swing.JComboBox<String> sortCombo;
    private javax.swing.JTextField spicyFormattedField1;
    private javax.swing.JTextField spicyFormattedField2;
    private javax.swing.JTextField stock_id;
    // End of variables declaration//GEN-END:variables
}

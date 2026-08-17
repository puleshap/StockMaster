/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stockmaster.main.panel;

import com.stockmaster.assets.components.jDialogs.UpdateStockPrice;
import com.stockmaster.assets.components.jDialogs.UpdateStockDiscount;
import com.stockmaster.assets.components.jDialogs.ExpiredProducts;
import com.stockmaster.main.Dashboard;
import connection.Sql;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author SinuraWahalathanthri
 */
public class StockCheck extends javax.swing.JPanel {

    private static Dashboard dashboard;

    /**
     * Creates new form CompanyRegistration
     */
    public StockCheck(Dashboard dashboard) {
        initComponents();
        this.dashboard = dashboard;
        updateTable();
        loadTable();
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

    private void searchStockProducts() {
        String searchProduct = search_t.getText();

        try {
            String query = "SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id "
                    + "WHERE `product`.`name` LIKE '%" + searchProduct + "%' "
                    + "OR `category`.`name` LIKE '%" + searchProduct + "%' "
                    + "OR `stock`.`stock_id` LIKE '%" + searchProduct + "%' "
                    + "OR `product`.`pid` LIKE '%" + searchProduct + "%' "
                    + "OR `status`.`type`= '" + searchProduct + "' "
                    + "OR `brand`.`name` LIKE '%" + searchProduct + "%'";

            ResultSet resultset = Sql.search(query);
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product_pid"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));
                stocklist.add(resultset.getString("bprice"));

                stocklist.add(resultset.getString("quantity"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("discount"));
                double sellingPrice = Double.parseDouble(resultset.getString("selling_price"));
                double discount = Double.parseDouble(resultset.getString("discount"));

                if (discount == 0) {
                    stocklist.add("0");
                } else {
                    double dis_price = sellingPrice - (sellingPrice * (discount / 100));

                    String formattedDisPrice = String.format("%.2f", dis_price);
                    stocklist.add(formattedDisPrice);
                }
                stocklist.add(resultset.getString("type"));
                modal.addRow(stocklist);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadTable() {
        try {
            ResultSet resultset = Sql.search("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id");
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product_pid"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));
                stocklist.add(resultset.getString("bprice"));

                stocklist.add(resultset.getString("quantity"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("discount"));
                double sellingPrice = Double.parseDouble(resultset.getString("selling_price"));
                double discount = Double.parseDouble(resultset.getString("discount"));

                double dis_price = sellingPrice - (sellingPrice * (discount / 100));

                String formattedDisPrice = String.format("%.2f", dis_price);
                stocklist.add(formattedDisPrice);

                stocklist.add(resultset.getString("type"));
                modal.addRow(stocklist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading stock data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        checkStockTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        expfrom = new com.toedter.calendar.JDateChooser();
        expto = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        spicyTextField12 = new com.stockmaster.assets.components.SMTextField1();
        jLabel6 = new javax.swing.JLabel();
        spicyTextField11 = new com.stockmaster.assets.components.SMTextField1();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        expfrom1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        expto1 = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        sMSearch2 = new com.stockmaster.assets.components.SMSearch();
        jLabel15 = new javax.swing.JLabel();
        search_t = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        sMSearch9 = new com.stockmaster.assets.components.SMSearch();
        jLabel27 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sMButton1 = new com.stockmaster.assets.components.SMButton();

        setBackground(new java.awt.Color(36, 93, 146));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        checkStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stock Id", "Product Id", "Product", "Category", "Brand", "MFD", "EXP", "Buying Price", "Qty", "Selling Price", "Discount", "Discount Price", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false, false, false, true, true
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

        jPanel2.setBackground(new java.awt.Color(248, 248, 248));

        expfrom.setForeground(new java.awt.Color(0, 0, 0));

        expto.setForeground(new java.awt.Color(0, 0, 0));

        jLabel14.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("To");

        jLabel11.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jLabel11.setText("Filter By Expire Date Range");

        jButton5.setBackground(new java.awt.Color(0, 122, 255));
        jButton5.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Filter");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        spicyTextField12.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        jLabel6.setText("Max");

        spicyTextField11.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N

        jLabel3.setText("Min");

        jLabel12.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jLabel12.setText("Filter By Selling Price Range");

        expfrom1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel13.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        jLabel13.setText("Filter By Manufacture Date Range");

        jLabel16.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("To");

        expto1.setForeground(new java.awt.Color(0, 0, 0));

        jButton6.setBackground(new java.awt.Color(0, 122, 255));
        jButton6.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Filter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 122, 255));
        jButton7.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Filter");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(214, 214, 214));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/stockmaster/assets/icon/rotate-right.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton1.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Print Stock");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spicyTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spicyTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(expfrom1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(expto1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(expfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(expto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)))))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(expto1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expfrom1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(6, 6, 6))))
                            .addComponent(expto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(spicyTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(spicyTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
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
                .addComponent(search_t, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        sMSearch2Layout.setVerticalGroup(
            sMSearch2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(search_t, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sMSearch9.setBackground(new java.awt.Color(42, 82, 125));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/stockmaster/assets/icon/cubes (1).png"))); // NOI18N

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

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Dashboard / Stock Management");

        jLabel5.setFont(new java.awt.Font("Inter SemiBold", 0, 20)); // NOI18N
        jLabel5.setText("Stock Management");

        sMButton1.setBackground(new java.awt.Color(226, 127, 39));
        sMButton1.setForeground(new java.awt.Color(255, 255, 255));
        sMButton1.setText("View Products that Expire Soon");
        sMButton1.setFont(new java.awt.Font("Inter ExtraBold", 0, 12)); // NOI18N
        sMButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(sMSearch9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(sMButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sMSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMSearch9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(sMButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sMSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sMButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sMButton3ActionPerformed

    private void sMButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sMButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
//        String path = "src\\lk\\jiat\\medicom\\reports\\Medicom_Stock.jasper";
        try {
            ResultSet rs = Sql.search("SELECT * FROM shop "
                    + "INNER JOIN shopaddress ON shopaddress.shop_shopid = shop.shopid");
            String path = "src//reports//StockMasterEmployee.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            if (rs.next()) {
                String name = rs.getString("name");
                String no = rs.getString("ano");
                String l1 = rs.getString("line1");
                String l2 = rs.getString("line2");
                parameters.put("Parameter1", no+",");
                parameters.put("Parameter2", l1+",");
                parameters.put("Parameter3", l2+".");
                
                JRTableModelDataSource dataSource = new JRTableModelDataSource(checkStockTable.getModel());

                JasperPrint jasperPrint = JasperFillManager.fillReport(path, parameters, dataSource);

                JasperViewer.viewReport(jasperPrint, false);
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, Sql.getConnection());
//            JasperViewer.viewReport(jasperPrint, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println("Error loading the report: " + e.getMessage());
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void checkStockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkStockTableMouseClicked
        if (evt.getClickCount() == 1) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();
            String id = target.getValueAt(row, 0).toString();

            String name = target.getValueAt(row, 2).toString();
            String status = target.getValueAt(row, 12).toString();
            if (column == 9) {

                UpdateStockPrice cD = new UpdateStockPrice(dashboard, true, id, name, status);
                cD.setVisible(true);
            } else if (column == 10) {
                UpdateStockDiscount cD = new UpdateStockDiscount(dashboard, true, id, name, status);
                cD.setVisible(true);
            }
        }
    }//GEN-LAST:event_checkStockTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        searchStockProducts();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Date date1 = expfrom.getDate();
        Date date2 = expto.getDate();

        if (date1 == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Select Starting Date.");
            return;
        } else if (date2 == null) {

            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Select Ending Date.");
            return;
        } else if (date1.after(date2)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Starting date is greater-than the Ending date");
            return;
        }

        try {
            String query = ("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id  ");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            query += "WHERE stock.exp >= '" + format.format(date1) + "' AND stock.exp <= '" + format.format(date2) + "'";
            ResultSet resultset = Sql.search(query);
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product_pid"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));
                stocklist.add(resultset.getString("bprice"));

                stocklist.add(resultset.getString("quantity"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("discount"));
                stocklist.add(resultset.getString("selling_price"));
                double sellingPrice = Double.parseDouble(resultset.getString("selling_price"));
                double discount = Double.parseDouble(resultset.getString("discount"));

                double dis_price = sellingPrice - (sellingPrice * (discount / 100));

                String formattedDisPrice = String.format("%.2f", dis_price);
                stocklist.add(formattedDisPrice);

                stocklist.add(resultset.getString("type"));
                modal.addRow(stocklist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        Date date1 = expfrom1.getDate();
        Date date2 = expto1.getDate();

        if (date1 == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Select Starting Date.");
            return;
        } else if (date2 == null) {

            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Please Select Ending Date.");
            return;
        } else if (date1.after(date2)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Starting date is greater-than the Ending date");
            return;
        }
        try {
            String query = ("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id  ");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            query += "WHERE stock.mfg >= '" + format.format(date1) + "' AND stock.mfg <= '" + format.format(date2) + "'";
            ResultSet resultset = Sql.search(query);
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product_pid"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));
                stocklist.add(resultset.getString("bprice"));

                stocklist.add(resultset.getString("quantity"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("discount"));
                double sellingPrice = Double.parseDouble(resultset.getString("selling_price"));
                double discount = Double.parseDouble(resultset.getString("discount"));

                if (discount == 0) {
                    stocklist.add("0");
                } else {
                    double dis_price = sellingPrice - (sellingPrice * (discount / 100));

                    String formattedDisPrice = String.format("%.2f", dis_price);
                    stocklist.add(formattedDisPrice);
                }
                stocklist.add(resultset.getString("type"));
                modal.addRow(stocklist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String price1 = spicyTextField11.getText();
        String price2 = spicyTextField12.getText();

        if (price1.isEmpty() || price2.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Both price fields must be filled out");
            return;

        }
        try {
            double price1Value = Double.parseDouble(price1);
            double price2Value = Double.parseDouble(price2);
            if (price2Value <= price1Value) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Max price must be greater than Min price");
                return;
            }
            String query = ("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id  ");

            query += "WHERE stock.selling_price >= '" + price1Value + "' AND stock.selling_price <= '" + price2Value + "'";
            ResultSet resultset = Sql.search(query);
            DefaultTableModel modal = (DefaultTableModel) checkStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product_pid"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));
                stocklist.add(resultset.getString("bprice"));
                stocklist.add(resultset.getString("quantity"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("discount"));

                int sellingPrice = (int) Double.parseDouble(resultset.getString("selling_price"));
                int discount = (int) Double.parseDouble(resultset.getString("discount"));

// Check if the discount is 0
                if (discount == 0) {
                    stocklist.add(resultset.getString("selling_price"));
                } else {
                    int dis_price = sellingPrice - (sellingPrice * discount / 100);
                    stocklist.add(String.valueOf(dis_price));
                }

                stocklist.add(resultset.getString("type"));
                modal.addRow(stocklist);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ((JTextField) expfrom.getDateEditor().getUiComponent()).setText("");
        ((JTextField) expfrom1.getDateEditor().getUiComponent()).setText("");
        ((JTextField) expto.getDateEditor().getUiComponent()).setText("");
        ((JTextField) expto1.getDateEditor().getUiComponent()).setText("");

        search_t.setText("");
        spicyTextField11.setText("");
        spicyTextField12.setText("");
        loadTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sMButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMButton1ActionPerformed
        ExpiredProducts cD = new ExpiredProducts(dashboard, true);
        cD.setVisible(true);
    }//GEN-LAST:event_sMButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable checkStockTable;
    private com.toedter.calendar.JDateChooser expfrom;
    private com.toedter.calendar.JDateChooser expfrom1;
    private com.toedter.calendar.JDateChooser expto;
    private com.toedter.calendar.JDateChooser expto1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private com.stockmaster.assets.components.SMButton sMButton1;
    private com.stockmaster.assets.components.SMSearch sMSearch2;
    private com.stockmaster.assets.components.SMSearch sMSearch9;
    private javax.swing.JTextField search_t;
    private com.stockmaster.assets.components.SMTextField1 spicyTextField11;
    private com.stockmaster.assets.components.SMTextField1 spicyTextField12;
    // End of variables declaration//GEN-END:variables
}

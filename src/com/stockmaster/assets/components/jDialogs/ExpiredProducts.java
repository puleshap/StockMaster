/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.stockmaster.assets.components.jDialogs;

import static com.stockmaster.main.panel.StockCheck.checkStockTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import raven.toast.Notifications;
import connection.Sql;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinudhi
 */
public class ExpiredProducts extends javax.swing.JDialog {

    /**
     * Creates new form ExpiredProducts
     */
    public ExpiredProducts(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        updateTable();

        updateTable2();
        loadTable();
        loadTable2();
    }

    private void updateTable() {
        JTableHeader tableHeader = ExpireTable.getTableHeader();
        tableHeader.setFont(new Font("Fredoka", Font.PLAIN, 12));
        tableHeader.setBackground(new Color(255, 102, 102));

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Fredoka", Font.PLAIN, 13));
                c.setBackground(new Color(255, 102, 102));
                c.setForeground(Color.WHITE);
                return c;
            }
        });

        SwingUtilities.updateComponentTreeUI(ExpireTable);
    }

    private void updateTable2() {
        JTableHeader tableHeader = OutStockTable.getTableHeader();
        tableHeader.setFont(new Font("Fredoka", Font.PLAIN, 12));
        tableHeader.setBackground(new Color(255, 153, 0));

        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Fredoka", Font.PLAIN, 13));
                c.setBackground(new Color(255, 153, 0));
                c.setForeground(Color.WHITE);
                return c;
            }
        });

        SwingUtilities.updateComponentTreeUI(OutStockTable);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ExpireTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OutStockTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Fredoka", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Products That Expire Soon & Out Of Stock Products");

        jLabel5.setFont(new java.awt.Font("Fredoka", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Dashboard / Products / Manage Product Stock");

        jTabbedPane1.setFont(new java.awt.Font("Fredoka Medium", 0, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        ExpireTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stock ID", "Product", "Category", "Brand", "Quantity", "Buying Price", "Selling Price", "MFG. Date", "EXP. Date", "Days Left"
            }
        ));
        jScrollPane1.setViewportView(ExpireTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane1.addTab("Products That Expire Soon", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        OutStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Stock ID", "Product", "Category", "Brand", "Buying Price", "Selling Price", "Profit per Item"
            }
        ));
        jScrollPane2.setViewportView(OutStockTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jTabbedPane1.addTab("Out Of Stock Products", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void loadTable() {
        try {
            ResultSet resultset = Sql.search("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id"
                    + "  WHERE exp BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH)"
                    + "ORDER BY stock_id");

            DefaultTableModel modal = (DefaultTableModel) ExpireTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));
                stocklist.add(resultset.getString("quantity"));

                stocklist.add(resultset.getString("bprice"));
                stocklist.add(resultset.getString("selling_price"));
                stocklist.add(resultset.getString("mfg"));
                stocklist.add(resultset.getString("exp"));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateTimeString = resultset.getString("exp");
                Date expirationDate = sdf.parse(dateTimeString);
                Date currentDate = new Date(); // Current date

                long differenceInMillies = expirationDate.getTime() - currentDate.getTime();
                long daysLeft = TimeUnit.DAYS.convert(differenceInMillies, TimeUnit.MILLISECONDS);

                stocklist.add(daysLeft);
                modal.addRow(stocklist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadTable2() {
        try {
            ResultSet resultset = Sql.search("SELECT * , category.name AS categoryname, "
                    + "brand.name AS brandname "
                    + "FROM stock "
                    + "LEFT JOIN grnitem ON grnitem.stock_stock_id = stock.stock_id "
                    + "INNER JOIN status ON status.id = stock.status_id "
                    + "INNER JOIN product ON product.pid = stock.product_pid "
                    + "INNER JOIN category_has_brand ON category_has_brand.id = product.category_has_brand_id "
                    + "INNER JOIN category ON category.id = category_has_brand.category_id "
                    + "INNER JOIN brand ON brand.id = category_has_brand.brand_id"
                    + "  WHERE stock.quantity ='0' "
                    + "ORDER BY stock_id");

            DefaultTableModel modal = (DefaultTableModel) OutStockTable.getModel();
            modal.setRowCount(0);

            while (resultset.next()) {
                Vector<Object> stocklist = new Vector<>();
                stocklist.add(resultset.getString("stock_id"));
                stocklist.add(resultset.getString("product.name"));
                stocklist.add(resultset.getString("categoryname"));
                stocklist.add(resultset.getString("brandname"));

                stocklist.add(resultset.getString("bprice"));
                stocklist.add(resultset.getString("selling_price"));

                String bpriceString = resultset.getString("bprice");
                String sellingPriceString = resultset.getString("selling_price");

                double bprice = Double.parseDouble(bpriceString);
                double sellingPrice = Double.parseDouble(sellingPriceString);

                double profit = sellingPrice - bprice;

                stocklist.add(String.valueOf(profit));

                modal.addRow(stocklist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExpiredProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExpiredProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExpiredProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExpiredProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ExpiredProducts dialog = new ExpiredProducts(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ExpireTable;
    private javax.swing.JTable OutStockTable;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.math.dbexplorer.view;

import com.math.dbexplorer.controller.QueryPanelController;
import com.math.dbexplorer.database.ConnectionProvider;
import javax.swing.table.TableModel;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author matheuskunnen
 */
public class QueryPanelView extends javax.swing.JPanel {

    private final QueryPanelController controller;

    /**
     * Creates new form QueryPanelView
     */
    public QueryPanelView(ConnectionProvider connProvider) {
        initComponents();
        this.controller = new QueryPanelController(this, connProvider);
        this.controller.init();

    }

    public QueryPanelView(ConnectionProvider connProvider, final String defaultQuery) {
        initComponents();
        this.controller = new QueryPanelController(this, connProvider, defaultQuery);
        this.controller.init();

    }

    public void setTableDataModel(TableModel model) {
        this.tableData.setModel(model);
    }

    public void setQueryTxt(final String query) {
        this.txtQuery.setText(query);
    }

    public void focusDataTable() {
        this.tabPanelOutputs.setSelectedIndex(0);
    }

    public void focusOutput() {
        this.tabPanelOutputs.setSelectedIndex(1);
    }

    public void hydrateOutputText() {
        this.txtOutput.setText(this.controller.getQueryPanel().getOutput());
    }

    public void hydrateLimitText() {
        this.txtLimit.setText(String.valueOf(this.controller.getQueryPanel().getLimit()));
    }

    public void enableExport() {
        this.btnExport.setEnabled(true);
    }

    public void disableExport() {
        this.btnExport.setEnabled(false);
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
        jPanel2 = new javax.swing.JPanel();
        btnRunQuery = new javax.swing.JButton();
        txtLimit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        tabPanelOutputs = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        RSyntaxTextArea stextArea = new RSyntaxTextArea();
        stextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        stextArea.setCodeFoldingEnabled(true);
        txtQuery = stextArea;

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnRunQuery.setText("Executar");
        btnRunQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunQueryActionPerformed(evt);
            }
        });

        txtLimit.setText("1000");
        txtLimit.setMinimumSize(new java.awt.Dimension(45, 26));
        txtLimit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLimitFocusLost(evt);
            }
        });

        jLabel1.setText("Limit");

        btnExport.setText("Exportar");
        btnExport.setEnabled(false);
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRunQuery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRunQuery)
                    .addComponent(txtLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnExport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableData.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tableData);

        tabPanelOutputs.addTab("Dados", jScrollPane1);

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane4.setViewportView(txtOutput);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        tabPanelOutputs.addTab("Output", jPanel3);

        txtQuery.setColumns(20);
        txtQuery.setRows(5);
        jScrollPane3.setViewportView(txtQuery);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanelOutputs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPanelOutputs, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRunQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunQueryActionPerformed
        // TODO add your handling code here:
        this.controller.runQuery(this.txtQuery.getText());
    }//GEN-LAST:event_btnRunQueryActionPerformed

    private void txtLimitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLimitFocusLost
        // TODO add your handling code here:
        this.controller.updateLimit(this.txtLimit.getText());
    }//GEN-LAST:event_txtLimitFocusLost

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        // TODO add your handling code here:
        this.controller.onExport();
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnRunQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane tabPanelOutputs;
    private javax.swing.JTable tableData;
    private javax.swing.JTextField txtLimit;
    private javax.swing.JTextArea txtOutput;
    private javax.swing.JTextArea txtQuery;
    // End of variables declaration//GEN-END:variables
}

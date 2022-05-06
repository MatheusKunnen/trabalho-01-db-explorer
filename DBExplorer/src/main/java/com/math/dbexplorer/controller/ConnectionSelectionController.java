/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.beans.NamedConnection;
import com.math.dbexplorer.database.ConnectionProvider;
import com.math.dbexplorer.model.ConnectionSelection;
import com.math.dbexplorer.view.ConnectionExplorerView;
import com.math.dbexplorer.view.ConnectionSelectionView;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionSelectionController {

    ConnectionSelection connSelection = new ConnectionSelection();
    ConnectionSelectionView connSelectionView;

    public ConnectionSelectionController(ConnectionSelectionView connSelectionView) {
        this.connSelectionView = connSelectionView;
        this.connSelectionView.setConnList(this.connSelection.getConnList());
    }

    public void updateSelectedConnection(final int pos) {
        NamedConnection conn = this.connSelection.getConnections().get(pos);
        if (conn != this.connSelection.getSelectedConn()) {
            this.connSelection.setSelectedConn(conn);
            this.connSelectionView.hydrateSelectedConn();
        }

    }

    public ConnectionSelection getConnSelection() {
        return connSelection;
    }

    public void setConnSelection(ConnectionSelection connSelection) {
        this.connSelection = connSelection;
    }

    public void onConnect() {

        try {
            ConnectionProvider connProvider = new ConnectionProvider(this.connSelection.getSelectedConn());
            connProvider.connect();
            JPanel panel = new ConnectionExplorerView(connProvider);
            JOptionPane jop = new JOptionPane();
            JDialog dialog = jop.createDialog("Conexão " + this.connSelection.getSelectedConn().getDatabaseUrl());
            dialog.setSize(720, 500);
            dialog.setContentPane(panel);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.connSelectionView, ex.getMessage());
        }
    }

}

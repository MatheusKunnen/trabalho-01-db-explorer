/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.beans.NamedConnection;
import com.math.dbexplorer.model.ConnectionSelection;
import com.math.dbexplorer.view.ConnectionSelectionView;

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
    
    

}

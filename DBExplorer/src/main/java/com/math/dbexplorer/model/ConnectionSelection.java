/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.model;

import com.math.dbexplorer.beans.NamedConnection;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionSelection {

    private NamedConnection selectedConn = new NamedConnection();
    private ArrayList<NamedConnection> connections = new ArrayList<>();
    private String connectionsFilePath = "";

    public ConnectionSelection() {
        this.connectionsFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "dbexplorer-connections.json";
    }

    public DefaultListModel getConnList() {
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < this.connections.size(); i++) {
            model.addElement(this.connections.get(i).getAlias());
        }
        return model;
    }

    public NamedConnection getSelectedConn() {
        return selectedConn;
    }

    public void setSelectedConn(NamedConnection selectedConn) {
        this.selectedConn = selectedConn;
    }

    public ArrayList<NamedConnection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<NamedConnection> connections) {
        this.connections = connections;
    }

    public String getConnectionsFilePath() {
        return connectionsFilePath;
    }

    public void setConnectionsFilePath(String connectionsFilePath) {
        this.connectionsFilePath = connectionsFilePath;
    }

}

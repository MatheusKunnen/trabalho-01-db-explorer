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

    NamedConnection selectedConn = new NamedConnection();
    ArrayList<NamedConnection> connections = new ArrayList<>();

    public ConnectionSelection() {
        this.connections.add(new NamedConnection("Pg1", "localhost", 5432, "test", "123456789", "postgres", "postgresql"));
        this.connections.add(new NamedConnection("Pg2", "localhost", 5432, "test", "123456789", "livraria0", "postgresql"));
        this.connections.add(new NamedConnection("Pg3", "127.0.0.1", 5433, "test2", "1234567890", "postgres", "postgresql"));
        this.connections.add(new NamedConnection("MySQL", "127.0.0.1", 5433, "root", "1234567890", "mysql", "mysql"));
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
    
    

}

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionSelectionController {

    private ConnectionSelection connSelection = new ConnectionSelection();
    private ConnectionSelectionView connSelectionView;

    public ConnectionSelectionController(ConnectionSelectionView connSelectionView) {
        this.connSelectionView = connSelectionView;
    }

    public void init() {
        this.loadConnections();
        this.onUpdateConnections();
    }

    public void updateSelectedConnection(final int pos) {
        if (pos >= 0){
        NamedConnection conn = this.connSelection.getConnections().get(pos);
        if (conn != this.connSelection.getSelectedConn()) {
            this.connSelection.setSelectedConn(conn);
            this.connSelectionView.hydrateSelectedConn();
        }
        } else {
            this.connSelection.setSelectedConn(new NamedConnection());
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
            ConnectionProvider connProvider = new ConnectionProvider(this.connSelectionView.getCurrentConnection());//this.connSelection.getSelectedConn());
            connProvider.connect();
            JPanel panel = new ConnectionExplorerView(connProvider);
            JOptionPane jop = new JOptionPane();
            JDialog dialog = jop.createDialog("Conexão " + this.connSelection.getSelectedConn().getDatabaseUrl());
            dialog.setResizable(true);
            dialog.setSize(720, 500);
            dialog.setContentPane(panel);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.connSelectionView, ex.getMessage());
        }
    }

    public void onUpdateConnections() {
        this.connSelectionView.setConnList(this.connSelection.getConnList());
    }

    public void loadConnections() {
        this.connSelection.getConnections().clear();
        InputStream is = null;
        try {
            System.out.println(this.connSelection.getConnectionsFilePath());
            File file = new File(this.connSelection.getConnectionsFilePath());
            if (file.exists()) {
                is = new FileInputStream(file);

                JSONTokener tokener = new JSONTokener(is);
                JSONObject jObj = new JSONObject(tokener);

                JSONArray connections = jObj.getJSONArray("connections");
                for (int i = 0; i < connections.length(); i++) {
                    this.connSelection.getConnections().add(NamedConnection.GetFromJson(connections.getJSONObject(i)));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ConnectionSelectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void persistConnections() {
        JSONObject root = new JSONObject();
        JSONArray connections = new JSONArray();

        for (int i = 0; i < this.connSelection.getConnections().size(); i++) {
            connections.put(this.connSelection.getConnections().get(i).getJSON());
        }

        root.put("connections", connections);
        try ( PrintStream out = new PrintStream(new FileOutputStream(this.connSelection.getConnectionsFilePath()))) {
            out.print(root.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportQueryResultController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.connSelectionView, ex.getMessage());
        }

    }

    public void updateConnection(NamedConnection connection) {
        int i = 0;
        while (!this.connSelection.getConnections().get(i).getAlias().equals(connection.getAlias())
                && i < this.connSelection.getConnections().size()) {
            i++;
        }
        if (i < this.connSelection.getConnections().size()) {
            this.connSelection.getConnections().set(i, connection);
            this.persistConnections();
        } else {
            this.addConnection(connection);
        }
    }

    public void addConnection(NamedConnection connection) {
        if (connection.getAlias().length() <= 0) {
            connection.setAlias(connection.getDatabaseUrl());
        }
        this.connSelection.getConnections().add(connection);
        this.persistConnections();
        this.onUpdateConnections();
    }

    public void deleteConnection(NamedConnection connection) {
        int i = 0;
        while (!this.connSelection.getConnections().get(i).getAlias().equals(connection.getAlias())
                && i < this.connSelection.getConnections().size()) {
            i++;
        }
        if (i < this.connSelection.getConnections().size()) {
            this.connSelection.getConnections().remove(i);
        }
        this.persistConnections();
        this.onUpdateConnections();
    }
}

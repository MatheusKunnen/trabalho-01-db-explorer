/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.model;

import com.math.dbexplorer.beans.ConnMetaData;
import com.math.dbexplorer.database.ConnectionProvider;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionExplorer {
    private ConnMetaData connMeta;
    private ConnectionProvider connProvider;

    public ConnectionExplorer(ConnectionProvider connProvider, ConnMetaData connMeta) {
        this.connMeta = connMeta;
        this.connProvider = connProvider;
    }

    public ConnMetaData getConnMeta() {
        return connMeta;
    }

    public void setConnMeta(ConnMetaData connMeta) {
        this.connMeta = connMeta;
    }

    public ConnectionProvider getConnProvider() {
        return connProvider;
    }

    public void setConnProvider(ConnectionProvider connProvider) {
        this.connProvider = connProvider;
    }
    
    
}

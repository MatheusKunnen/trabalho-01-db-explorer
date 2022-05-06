/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.database;

import com.math.dbexplorer.beans.ConnectionParameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionProvider {
    
    private final ConnectionParameters connParams;
    private Connection dbConn;

    public ConnectionProvider(ConnectionParameters connParams) {
        this.connParams = connParams;
    }

    public void connect() throws SQLException {
        if (this.dbConn != null) {
            this.dbConn.close();
        }
        try {
            this.dbConn = DriverManager.getConnection(this.connParams.getDatabaseUrl(), this.connParams.getUser(), this.connParams.getPassword());
        } catch (SQLException e) {
            throw e;
        }
    }

    public Connection getDbConn() {
        return dbConn;
    }

    public void setDbConn(Connection dbConn) {
        this.dbConn = dbConn;
    }
    
    
}

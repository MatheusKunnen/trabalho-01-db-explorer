/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionParameters {
    public static final String PROVIDER_POSTGRES = "postgres";
    public static final String PROVIDER_MYSQL = "mysql";
    
    private String host;
    private int port;
    private String user;
    private String password;
    private String database;
    private String provider;

    public ConnectionParameters(String host, int port, String user, String password, String database, String provider) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
        this.provider = provider;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    public String getDatabaseUrl() {
        return "jdbc:" + this.provider + "://" + host + ":" + String.valueOf(this.port) + "/" + this.database;
    }
    
}

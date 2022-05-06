/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

/**
 *
 * @author matheuskunnen
 */
public class NamedConnection extends ConnectionParameters {
    
    private String alias = "";

    public NamedConnection() {
        super("", 0, "", "", "", ConnectionParameters.PROVIDER_POSTGRES);
    }

    public NamedConnection(String alias, String host, int port, String user, String password, String database, String provider) {
        super(host, port, user, password, database, provider);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
    

}

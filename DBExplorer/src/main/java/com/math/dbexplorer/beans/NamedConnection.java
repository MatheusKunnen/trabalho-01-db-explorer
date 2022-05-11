/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import org.json.JSONObject;

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

    public JSONObject getJSON() {
        JSONObject jObj = new JSONObject();
        jObj.put("alias", this.getAlias());
        jObj.put("host", this.getHost());
        jObj.put("port", this.getPort());
        jObj.put("user", this.getUser());
        jObj.put("password", this.getPassword());
        jObj.put("database", this.getDatabase());
        jObj.put("provider", this.getProvider());
        return jObj;
    }

    public static NamedConnection GetFromJson(JSONObject jObj) {
        return new NamedConnection(jObj.getString("alias"),
                jObj.getString("host"),
                jObj.getInt("port"),
                jObj.getString("user"),
                jObj.getString("password"),
                jObj.getString("database"),
                jObj.getString("provider"));
    }

}

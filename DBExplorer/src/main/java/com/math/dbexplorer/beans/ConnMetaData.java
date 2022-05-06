/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matheuskunnen
 */
public class ConnMetaData {
    ArrayList<Catalog> catalogs;

    public ConnMetaData() {
        this.catalogs = new ArrayList<>();
    }
        
    public ConnMetaData(ArrayList<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public ArrayList<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(ArrayList<Catalog> catalogs) {
        this.catalogs = catalogs;
    }
    
    
    public static ConnMetaData GetFromConnection(Connection conn) throws SQLException{
        ConnMetaData connMetaData = new ConnMetaData();
        DatabaseMetaData connMeta = conn.getMetaData();
        ResultSet rs = connMeta.getCatalogs();
        while(rs.next()){
            connMetaData.getCatalogs().add(Catalog.GetFromMetaData(connMeta, rs.getString(1)));
        }
        return connMetaData;
    }
    
}

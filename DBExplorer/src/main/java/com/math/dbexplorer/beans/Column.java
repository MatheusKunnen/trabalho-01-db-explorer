/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author matheuskunnen
 */
public class Column {

    private String name;
    private int type;
    private boolean primaryKey;

    public Column(String name, int type, boolean primaryKey) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setIsPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    public String getTreeName() {
        String diferentiator = this.isPrimaryKey() ? "(pk) " : "";
        return diferentiator + this.getName() + " : " + Column.GetTypeName(this.getType());
            
    }
    
    public static String GetTypeName(int type) {
        return switch (type) {
            default -> "Unknown";
        };
    }

    public static Column GetFromResultSet(ResultSet rs) throws SQLException {
        return new Column(rs.getString(4), rs.getInt(5), false);
    }
}

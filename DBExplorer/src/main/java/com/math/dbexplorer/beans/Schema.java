/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author matheuskunnen
 */
public class Schema {

    private final Catalog catalog;
    private final String name;
    private ArrayList<Table> tables;
    private ArrayList<Table> views;

    public Schema(Catalog catalog, String name) {
        this.catalog = catalog;
        this.name = name;
        this.tables = new ArrayList<>();
        this.views = new ArrayList<>();

    }

    public Schema(Catalog catalog, String name, ArrayList<Table> tables, ArrayList<Table> views) {
        this.catalog = catalog;
        this.name = name;
        this.tables = tables;
        this.views = views;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Table> getViews() {
        return views;
    }

    public void setViews(ArrayList<Table> views) {
        this.views = views;
    }

    
    public static Schema GetFromCatalog(DatabaseMetaData dbMeta, Catalog catalog, String name) throws SQLException {
        Schema schema = new Schema(catalog, name);
        ResultSet table = dbMeta.getTables(catalog.getName(), name, null, null);
        while (table.next()) {
            Table tab = Table.GetFromSchema(dbMeta, schema, table.getString(3), table.getString(4));
            if (tab.getType().toLowerCase().contains("table")) {
                schema.getTables().add(tab);
            } else if (tab.getType().toLowerCase().contains("view")) {
                schema.getViews().add(tab);
            }
        }
        return schema;
    }

}

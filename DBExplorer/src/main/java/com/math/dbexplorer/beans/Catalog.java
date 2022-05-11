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
public class Catalog {

    private final String name;
    private ArrayList<Schema> schemas;

    public Catalog(String name) {
        this.name = name;
        this.schemas = new ArrayList<>();
    }

    public Catalog(String name, ArrayList<Schema> schemas) {
        this.name = name;
        this.schemas = schemas;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(ArrayList<Schema> schemas) {
        this.schemas = schemas;
    }

    public static Catalog GetFromMetaData(DatabaseMetaData dbMeta, final String name) throws SQLException {
        Catalog catalog = new Catalog(name);
        ResultSet schema = dbMeta.getSchemas(name, null);
        while (schema.next()) {
            catalog.getSchemas().add(Schema.GetFromCatalog(dbMeta, catalog, schema.getString(1)));
        }
        if (catalog.getSchemas().size() <=0){
            catalog.getSchemas().add(Schema.GetDefaultFromCatalog(dbMeta, catalog));
        }
        return catalog;
    }
}

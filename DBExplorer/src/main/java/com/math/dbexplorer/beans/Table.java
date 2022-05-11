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
public class Table {

    private final Schema schema;
    private final String name;
    private String type;

    ArrayList<Column> columns;

    public Table(Schema schema, String name, String type) {
        this.schema = schema;
        this.name = name;
        this.type = type;
        this.columns = new ArrayList<>();
    }

    public Table(Schema schema, String name, String catalog, String type, ArrayList<Column> columns) {
        this.schema = schema;
        this.name = name;
        this.type = type;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public String getSelectQuery() {
        final String domain = this.schema.getName() != null ? this.schema.getName() : this.schema.getCatalog().getName();
        return "SELECT * FROM " + domain + "." + this.getName();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public static Table GetFromSchema(final DatabaseMetaData dbMeta, final Schema schema, final String name, final String type) throws SQLException {
        Table table = new Table(schema, name, type);
        ResultSet column = dbMeta.getColumns(schema.getCatalog().getName(), schema.getName(), name, null);
        ResultSet rsPk = dbMeta.getPrimaryKeys(schema.getCatalog().getName(), schema.getName(), name);
        ArrayList<String> primaryKeys = new ArrayList<>();

        while (rsPk.next()) {
            primaryKeys.add(rsPk.getString(4));
        }

        while (column.next()) {
            Column col = Column.GetFromResultSet(column);
            for (int i = 0; i < primaryKeys.size() && !col.isPrimaryKey(); i++) {
                if (col.getName().equals(primaryKeys.get(i))) {
                    col.setIsPrimaryKey(true);
                }
            }
            table.getColumns().add(col);
        }

        return table;
    }
    
    public static Table GetFromCatalog(final DatabaseMetaData dbMeta, final Schema schema, final String name, final String type) throws SQLException {
        Table table = new Table(schema, name, type);
        ResultSet column = dbMeta.getColumns(schema.getCatalog().getName(), schema.getName(), name, null);
        ResultSet rsPk = dbMeta.getPrimaryKeys(schema.getCatalog().getName(), schema.getName(), name);
        ArrayList<String> primaryKeys = new ArrayList<>();

        while (rsPk.next()) {
            primaryKeys.add(rsPk.getString(4));
        }

        while (column.next()) {
            Column col = Column.GetFromResultSet(column);
            for (int i = 0; i < primaryKeys.size() && !col.isPrimaryKey(); i++) {
                if (col.getName().equals(primaryKeys.get(i))) {
                    col.setIsPrimaryKey(true);
                }
            }
            table.getColumns().add(col);
        }

        return table;
    }
}

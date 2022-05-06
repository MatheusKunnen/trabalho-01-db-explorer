/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author matheuskunnen
 */
public class QueryResult {
    
    final ResultSetMetaData metaData;
    final ArrayList<Column> columns;
    final ArrayList<String[]> data;

    public QueryResult(ResultSetMetaData metaData,ArrayList<Column> columns, ArrayList<String[]> data) {
        this.metaData = metaData;
        this.columns = columns;
        this.data = data;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public ResultSetMetaData getMetaData() {
        return metaData;
    }
    
    public Object[] getTableColumnsObject() {
        ArrayList<String> cols = new ArrayList<>();
        for (int i = 0; i < this.columns.size(); i++) {
            cols.add(this.columns.get(i).getTreeName());
        }
        return cols.toArray();
    }

    public Object[][] getTableValuesObject() {
        return this.data.toArray(Object[][]::new);
    }
    
    public TableModel getTableModel() {
        return new DefaultTableModel(this.getTableValuesObject(), this.getTableColumnsObject());
    }

    public static QueryResult GetFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<String[]> data = new ArrayList<>();

        ResultSetMetaData mData = rs.getMetaData();
        for (int i = 1; i <= mData.getColumnCount(); i++) {
            Column col = new Column(mData.getColumnName(i), mData.getColumnType(i), false);
            columns.add(col);
        }

        ArrayList<String> values = new ArrayList<>();
        while (rs.next()) {
            values.clear();
            for (int i = 1; i <= mData.getColumnCount(); i++) {
                values.add(rs.getString(i));
            }
            data.add(values.toArray(new String[]{}));
        }

        return new QueryResult(mData, columns, data);
    }

}

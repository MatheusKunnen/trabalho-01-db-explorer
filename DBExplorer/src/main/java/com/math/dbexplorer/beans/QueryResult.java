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
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author matheuskunnen
 */
public class QueryResult {

    public static enum JSONType {
        OBJECT_MODE, ROW_MODE
    };

    private final ResultSetMetaData metaData;
    private final ArrayList<Column> columns;
    private final ArrayList<String[]> data;

    public QueryResult(ResultSetMetaData metaData, ArrayList<Column> columns, ArrayList<String[]> data) {
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

    public String getCSV() {
        return this.getCSV(",");
    }

    public String getCSV(final String separator) {
        StringBuilder csv = new StringBuilder("");
        for (int i = 0; i < this.columns.size(); i++) {
            csv.append(StringEscapeUtils.escapeCsv(this.columns.get(i).getName()));
                csv.append(separator);
        }
        csv.deleteCharAt(csv.length()-1);
        csv.append("\n");
        for (int i = 0; i < this.data.size(); i++) {
            String[] mData = this.data.get(i);
            for (int j = 0; j < this.columns.size(); j++) {
                csv.append(this.columns.get(j).getCSVSafeValue(mData[j]));
                    csv.append(separator);
            }
            csv.deleteCharAt(csv.length()-1);
                csv.append("\n");
        }
        csv.deleteCharAt(csv.length()-1);
        return csv.toString();
    }

    public JSONObject getJsonObject(QueryResult.JSONType type) {
        JSONObject jObj = new JSONObject();

        if (type == QueryResult.JSONType.ROW_MODE) {
            jObj.put("columns", this.getColumnsJsonArray());
            jObj.put("rows", this.getDataJsonArray());
        } else {
            return this.getJsonObjectInternal();
        }

        return jObj;
    }

    private JSONArray getColumnsJsonArray() {
        JSONArray jArray = new JSONArray();
        for (int i = 0; i < this.columns.size(); i++) {
            jArray.put(this.columns.get(i).getName());
        }
        return jArray;
    }

    private JSONArray getDataJsonArray() {
        JSONArray jArray = new JSONArray();
        for (int i = 0; i < this.data.size(); i++) {
            jArray.put(this.data.get(i));
        }
        return jArray;
    }

    private JSONObject getJsonObjectInternal() {
        JSONObject rootObj = new JSONObject();
        JSONArray rows = new JSONArray();

        for (int i = 0; i < this.data.size(); i++) {
            JSONObject row = new JSONObject();
            for (int j = 0; j < this.columns.size(); j++) {
                row.put(this.columns.get(i).getName(), this.data.get(i));
            }
            rows.put(row);
        }
        rootObj.put("data", rows);
        return rootObj;
    }

    public static QueryResult GetFromResultSet(ResultSet rs, int limit) throws SQLException {
        ArrayList<Column> columns = new ArrayList<>();
        ArrayList<String[]> data = new ArrayList<>();

        ResultSetMetaData mData = rs.getMetaData();
        for (int i = 1; i <= mData.getColumnCount(); i++) {
            Column col = new Column(mData.getColumnName(i), mData.getColumnType(i), false);
            columns.add(col);
        }

        ArrayList<String> values = new ArrayList<>();
        while (rs.next() && (data.size() < limit || limit <= 0)) {
            values.clear();
            for (int i = 1; i <= mData.getColumnCount(); i++) {
                values.add(rs.getString(i));
            }
            data.add(values.toArray(String[]::new));
        }

        return new QueryResult(mData, columns, data);
    }
}

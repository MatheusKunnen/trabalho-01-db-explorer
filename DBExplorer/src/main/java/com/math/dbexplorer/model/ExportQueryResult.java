/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.model;

import com.math.dbexplorer.beans.QueryResult;

/**
 *
 * @author matheuskunnen
 */
public class ExportQueryResult {
    
    public static final String FORMAT_CSV = "CSV";
    public static final String FORMAT_JSON_OBJ = "JSON-OBJECT";
    public static final String FORMAT_JSON_ARRAY = "JSON-ARRAY";
    
    private final QueryResult queryResult;
    private String filePath = "";
    private String format = ExportQueryResult.FORMAT_JSON_OBJ;

    public ExportQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    
    
    
}

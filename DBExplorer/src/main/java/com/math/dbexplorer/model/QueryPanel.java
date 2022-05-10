/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.model;

import com.math.dbexplorer.beans.QueryResult;
import com.math.dbexplorer.database.ConnectionProvider;
import java.sql.ResultSet;

/**
 *
 * @author matheuskunnen
 */
public class QueryPanel {
    private final ConnectionProvider connProvider;
    private String executedQuery = "";
    private String output = "";
    private QueryResult queryResult;

    public QueryPanel(ConnectionProvider connProvider) {
        this.connProvider = connProvider;
    }

    public QueryPanel(ConnectionProvider connProvider, String query) {
        this.connProvider = connProvider;
        this.executedQuery = query == null ? "" : query;
    }

    public ConnectionProvider getConnProvider() {
        return connProvider;
    }
    
    public String getExecutedQuery() {
        return executedQuery;
    }

    public void setExecutedQuery(String query) {
        this.executedQuery = query;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }
    
    
}

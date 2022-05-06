/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.beans.QueryResult;
import com.math.dbexplorer.database.ConnectionProvider;
import com.math.dbexplorer.model.QueryPanel;
import com.math.dbexplorer.view.QueryPanelView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheuskunnen
 */
public class QueryPanelController {
    QueryPanelView view;
    QueryPanel queryPanel;
    
    public QueryPanelController(QueryPanelView view, ConnectionProvider connProvider) {
        this.view = view;
        this.queryPanel = new QueryPanel(connProvider);
    }
    
    public void runQuery(final String query){
        try {
            Connection conn = this.queryPanel.getConnProvider().getDbConn();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            QueryResult result = QueryResult.GetFromResultSet(rs);
            this.queryPanel.setQueryResult(result);
            this.view.setTableDataModel(result.getTableModel());
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryPanelController.class.getName()).log(Level.SEVERE, null, ex);
            this.onQueryError(ex);
        }
    }
    
    private void onQueryError(SQLException ex){
        
    }
    
}

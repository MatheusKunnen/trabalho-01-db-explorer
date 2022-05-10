/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.Main;
import com.math.dbexplorer.beans.QueryResult;
import com.math.dbexplorer.database.ConnectionProvider;
import com.math.dbexplorer.model.QueryPanel;
import com.math.dbexplorer.view.ExportQueryResultDialogView;
import com.math.dbexplorer.view.QueryPanelView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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

    public QueryPanelController(QueryPanelView view, ConnectionProvider connProvider, final String defaultQuery) {
        this.view = view;
        this.queryPanel = new QueryPanel(connProvider, defaultQuery);
    }

    public void init() {
        if (this.queryPanel.getExecutedQuery().length() > 0) {
            System.out.println(this.queryPanel.getExecutedQuery());
            this.view.setQueryTxt(this.queryPanel.getExecutedQuery());
            this.runQuery();
        }
    }

    public void runQuery() {
        this.runQuery(this.queryPanel.getExecutedQuery());
    }

    public void runQuery(final String query) {
        try {
            if (!query.equals(this.queryPanel.getExecutedQuery())) {
                this.queryPanel.setExecutedQuery(query);
            }
            Connection conn = this.queryPanel.getConnProvider().getDbConn();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            QueryResult result = QueryResult.GetFromResultSet(rs, this.queryPanel.getLimit());
            rs.close();
            this.queryPanel.setQueryResult(result);
            this.view.setTableDataModel(result.getTableModel());

            this.onQuerySuccess();
        } catch (SQLException ex) {
//            Logger.getLogger(QueryPanelController.class.getName()).log(Level.SEVERE, null, ex);
            this.onQueryError(ex);
        }
    }

    private void onQuerySuccess() {
        this.view.focusDataTable();
        this.queryPanel.setOutput("Query Executed !!!");
        this.view.hydrateOutputText();
        this.view.enableExport();
    }

    private void onQueryError(SQLException ex) {
        this.view.focusOutput();
        this.queryPanel.setOutput(ex.getMessage());
        this.view.hydrateOutputText();
        this.view.disableExport();
    }

    public void onExport() {
        ExportQueryResultDialogView exportDialog = new ExportQueryResultDialogView(Main.GetDefaultMain(), true, this.queryPanel.getQueryResult());
        exportDialog.setVisible(true);
    }

    public void updateLimit(final String limit) {
        int newLimit = this.queryPanel.getLimit();
        try {
            newLimit = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.view, "Limite " + limit + " inválido.");
        }
        this.updateLimit(newLimit);

    }

    public void updateLimit(final int limit) {
        this.queryPanel.setLimit(limit);
        this.view.hydrateLimitText();
    }

    public QueryPanelView getView() {
        return view;
    }

    public QueryPanel getQueryPanel() {
        return queryPanel;
    }

}

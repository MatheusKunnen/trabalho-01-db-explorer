/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.beans.Catalog;
import com.math.dbexplorer.beans.ConnMetaData;
import com.math.dbexplorer.beans.Schema;
import com.math.dbexplorer.beans.Table;
import com.math.dbexplorer.database.ConnectionProvider;
import com.math.dbexplorer.model.ConnectionExplorer;
import com.math.dbexplorer.view.ConnectionExplorerView;
import com.math.dbexplorer.view.QueryPanelView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author matheuskunnen
 */
public class ConnectionExplorerController {

    private ConnectionExplorerView connExplorerView;
    private ConnectionExplorer connExplorer;

    public ConnectionExplorerController(ConnectionExplorerView connExplorerView, ConnectionProvider connProvider) {
        this.connExplorerView = connExplorerView;
        this.connExplorer = new ConnectionExplorer(connProvider, null);

    }

    public void init() {
        this.updateConnMetaData();
        this.createDefaultQuery();
    }

    public void createDefaultQuery() {
        this.addNewQueryPanel("Query", "");
    }

    public void updateConnMetaData() {
        try {
            this.connExplorer.setConnMeta(ConnMetaData.GetFromConnection(this.connExplorer.getConnProvider().getDbConn()));
            this.connExplorerView.hydrateConnectionTree();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionExplorerController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.connExplorerView, ex.getMessage());
        }
    }

    public void onTableSelected(final Table table) {
        this.addNewQueryPanel(table.getName(), table.getSelectQuery());
    }
    
    public void addNewQueryPanel(final String name, final String query){
        QueryPanelView queryView = new QueryPanelView(this.connExplorer.getConnProvider(), query);
        this.connExplorerView.addToTabQueries(name, queryView);
    }

    public DefaultMutableTreeNode getConnTree() {
//        DefaultMutableTreeNode conn = new DefaultMutableTreeNode("Connection");
//        conn.add(this.getCatalogsTree());
//        return conn;
        return this.getCatalogsTree();
    }

    private DefaultMutableTreeNode getCatalogsTree() {
        DefaultMutableTreeNode catalogs = new DefaultMutableTreeNode("Catalogs");
        for (int i = 0; i < this.connExplorer.getConnMeta().getCatalogs().size(); i++) {
            DefaultMutableTreeNode catalog = new DefaultMutableTreeNode(this.connExplorer.getConnMeta().getCatalogs().get(i).getName());
            catalog.add(this.getSchemasTree(this.connExplorer.getConnMeta().getCatalogs().get(i)));
            catalogs.add(catalog);
        }
        return catalogs;
    }

    private DefaultMutableTreeNode getSchemasTree(Catalog catalog) {
        DefaultMutableTreeNode schemas = new DefaultMutableTreeNode("Schemas");
        for (int i = 0; i < catalog.getSchemas().size(); i++) {
            DefaultMutableTreeNode schema = new DefaultMutableTreeNode(catalog.getSchemas().get(i).getName());
            Schema mSchema = catalog.getSchemas().get(i);
            schema.add(this.getTablesTree(mSchema));
            schema.add(this.getViewsTree(mSchema));
            schemas.add(schema);
        }
        return schemas;
    }

    private DefaultMutableTreeNode getTablesTree(Schema schema) {
        DefaultMutableTreeNode tables = new DefaultMutableTreeNode("Tables");
        for (int i = 0; i < schema.getTables().size(); i++) {
            DefaultMutableTreeNode table = new DefaultMutableTreeNode(schema.getTables().get(i).getName());
            table.setUserObject(schema.getTables().get(i));
            table.add(this.getColumnsTree(schema.getTables().get(i)));
            tables.add(table);
        }
        return tables;
    }

    private DefaultMutableTreeNode getViewsTree(Schema schema) {
        DefaultMutableTreeNode views = new DefaultMutableTreeNode("Views");
        for (int i = 0; i < schema.getViews().size(); i++) {
            DefaultMutableTreeNode view = new DefaultMutableTreeNode(schema.getViews().get(i).getName());
            view.setUserObject(schema.getViews().get(i));
            view.add(this.getColumnsTree(schema.getViews().get(i)));
            views.add(view);
        }
        return views;
    }

    private DefaultMutableTreeNode getColumnsTree(Table table) {
        DefaultMutableTreeNode columns = new DefaultMutableTreeNode("Columns");
        for (int i = 0; i < table.getColumns().size(); i++) {
            columns.add(new DefaultMutableTreeNode(table.getColumns().get(i).getTreeName()));
        }
        return columns;
    }
}

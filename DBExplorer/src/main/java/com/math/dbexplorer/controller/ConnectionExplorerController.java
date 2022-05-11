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

    private final ConnectionExplorerView connExplorerView;
    private final ConnectionExplorer connExplorer;

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

    public void addNewQueryPanel(final String name, final String query) {
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
        if (this.connExplorer.getConnMeta().getCatalogs().isEmpty() || this.connExplorer.getConnMeta().getCatalogs().size() > 1) {
            DefaultMutableTreeNode catalogs = new DefaultMutableTreeNode("Catalogs");
            for (int i = 0; i < this.connExplorer.getConnMeta().getCatalogs().size(); i++) {
                DefaultMutableTreeNode catalog = new DefaultMutableTreeNode(this.connExplorer.getConnMeta().getCatalogs().get(i).getName());
                DefaultMutableTreeNode schemas = this.getSchemasTree(this.connExplorer.getConnMeta().getCatalogs().get(i), catalog);
                if (schemas != null) {
                    catalog.add(schemas);
                }
                catalogs.add(catalog);
            }
            return catalogs;
        } else {
            DefaultMutableTreeNode catalog = new DefaultMutableTreeNode(this.connExplorer.getConnMeta().getCatalogs().get(0).getName());
            DefaultMutableTreeNode schemas = this.getSchemasTree(this.connExplorer.getConnMeta().getCatalogs().get(0), catalog);
            if (schemas != null) {
                catalog.add(schemas);
            }
            return catalog;
        }
    }

    private DefaultMutableTreeNode getSchemasTree(Catalog catalog, DefaultMutableTreeNode nCatalog) {
        if (catalog.getSchemas().isEmpty() || catalog.getSchemas().size() > 1) {
            DefaultMutableTreeNode schemas = new DefaultMutableTreeNode("Schemas");
            for (int i = 0; i < catalog.getSchemas().size(); i++) {
                Schema mSchema = catalog.getSchemas().get(i);
                DefaultMutableTreeNode schema = new DefaultMutableTreeNode(mSchema.getName());
                schema.add(this.getTablesTree(mSchema));
                schema.add(this.getViewsTree(mSchema));
                schemas.add(schema);
            }
            return schemas;
        } else {
            Schema mSchema = catalog.getSchemas().get(0);
            nCatalog.add(this.getTablesTree(mSchema));
            nCatalog.add(this.getViewsTree(mSchema));
            return null;

        }
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

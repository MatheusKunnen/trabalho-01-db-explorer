/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.controller;

import com.math.dbexplorer.beans.QueryResult;
import com.math.dbexplorer.model.ExportQueryResult;
import com.math.dbexplorer.view.ExportQueryResultDialogView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author matheuskunnen
 */
public class ExportQueryResultController {

    private final ExportQueryResultDialogView view;
    private final ExportQueryResult exportQueryResult;

    public ExportQueryResultController(final ExportQueryResultDialogView view, final QueryResult queryResult) {
        this.view = view;
        this.exportQueryResult = new ExportQueryResult(queryResult);
    }

    public void init() {
        this.exportQueryResult.setFilePath(System.getProperty("user.home") + System.getProperty("file.separator") + "export");
        this.view.hydrateExportFormat();
        this.view.hydrateFilePath();
    }

    public ExportQueryResultDialogView getView() {
        return view;
    }

    public ExportQueryResult getExportQueryResult() {
        return exportQueryResult;
    }

    public void onExport() {
        this.view.enableExport(false);
        String data;

        switch (this.exportQueryResult.getFormat()) {
            case ExportQueryResult.FORMAT_CSV:
                data = this.exportQueryResult.getQueryResult().getCSV();
                break;
            case ExportQueryResult.FORMAT_JSON_ARRAY:
                data = this.exportQueryResult.getQueryResult().getJsonObject(QueryResult.JSONType.ROW_MODE).toString();
                break;
            case ExportQueryResult.FORMAT_JSON_OBJ:
            default:
                data = this.exportQueryResult.getQueryResult().getJsonObject(QueryResult.JSONType.OBJECT_MODE).toString();
        }

        boolean status = false;
        final String filePath = this.getSanitizedFilePath();

        try ( PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            out.print(data);
            status = true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExportQueryResultController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this.view, ex.getMessage());
        } finally {
            this.view.enableExport(true);
        }

        if (status) {
            JOptionPane.showMessageDialog(this.view, "Dados exportados em " + this.exportQueryResult.getFormat() + " ao arquivo " + filePath);
        }

    }

    private String getSanitizedFilePath() {
        String sanitized = this.exportQueryResult.getFilePath();
        File file = new File(this.exportQueryResult.getFilePath());
        String extension = this.exportQueryResult.getFormat().equals(ExportQueryResult.FORMAT_CSV) ? "csv" : "json";

        if (file.isDirectory()) {
            sanitized += System.getProperty("file.separator") + "export." + extension;
        } else {
            if (!sanitized.contains(".json")) {
                sanitized += "." + extension;
            }
        }
        return sanitized;
    }

    public void showFileChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showDialog(this.view, "Selecionar");
        if (fc.getSelectedFile().getAbsolutePath() != null) {
            this.exportQueryResult.setFilePath(fc.getSelectedFile().getAbsolutePath());
            this.view.hydrateFilePath();
        }
    }

    public void updateFilePath(final String filePath) {
        this.exportQueryResult.setFilePath(filePath);
    }

    public void updateFormat(final String format) {
        this.exportQueryResult.setFormat(format);
    }
}

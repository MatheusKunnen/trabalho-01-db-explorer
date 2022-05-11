/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.math.dbexplorer.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author matheuskunnen
 */
public class Column {

    private String name;
    private int type;
    private boolean primaryKey;

    public Column(String name, int type, boolean primaryKey) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setIsPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTreeName() {
        String diferentiator = this.isPrimaryKey() ? "(pk) " : "";
        return diferentiator + this.getName() + " : " + Column.GetTypeName(this);

    }

    public static String GetTypeName(Column col) {
        return switch (col.getType()) {
            case java.sql.Types.ARRAY ->
                "ARRAY";
            case java.sql.Types.BIGINT ->
                "BIGINT";
            case java.sql.Types.BINARY ->
                "BINARY";
            case java.sql.Types.BIT ->
                "BIT";
            case java.sql.Types.BLOB ->
                "BLOB";
            case java.sql.Types.BOOLEAN ->
                "BOOLEAN";
            case java.sql.Types.CHAR ->
                "CHAR";
            case java.sql.Types.CLOB ->
                "CLOB";
            case java.sql.Types.DATALINK ->
                "DATALINK";
            case java.sql.Types.DATE ->
                "DATE";
            case java.sql.Types.DECIMAL ->
                "DECIMAL";
            case java.sql.Types.DISTINCT ->
                "DISTINCT";
            case java.sql.Types.DOUBLE ->
                "DOUBLE";
            case java.sql.Types.FLOAT ->
                "FLOAT";
            case java.sql.Types.INTEGER ->
                "INTEGER";
            case java.sql.Types.JAVA_OBJECT ->
                "JAVA_OBJECT";
            case java.sql.Types.LONGNVARCHAR ->
                "LONGNVARCHAR";
            case java.sql.Types.LONGVARBINARY ->
                "LONGVARBINARY";
            case java.sql.Types.LONGVARCHAR ->
                "LONGVARCHAR";
            case java.sql.Types.NCHAR ->
                "NCHAR";
            case java.sql.Types.NCLOB ->
                "NCLOB";
            case java.sql.Types.NULL ->
                "NULL";
            case java.sql.Types.NUMERIC ->
                "NUMERIC";
            case java.sql.Types.NVARCHAR ->
                "NVARCHAR";
            case java.sql.Types.OTHER ->
                "OTHER";
            case java.sql.Types.REAL ->
                "REAL";
            case java.sql.Types.REF ->
                "REF";
            case java.sql.Types.REF_CURSOR ->
                "REF_CURSOR";
            case java.sql.Types.ROWID ->
                "ROWID";
            case java.sql.Types.SMALLINT ->
                "SMALLINT";
            case java.sql.Types.SQLXML ->
                "SQLXML";
            case java.sql.Types.STRUCT ->
                "STRUCT";
            case java.sql.Types.TIME ->
                "TIME";
            case java.sql.Types.TIME_WITH_TIMEZONE ->
                "TIME_WITH_TIMEZONE";
            case java.sql.Types.TIMESTAMP ->
                "TIMESTAMP";
            case java.sql.Types.TIMESTAMP_WITH_TIMEZONE ->
                "TIMESTAMP_WITH_TIMEZONE";
            case java.sql.Types.TINYINT ->
                "TINYINT";
            case java.sql.Types.VARBINARY ->
                "VARBINARY";
            case java.sql.Types.VARCHAR ->
                "VARCHAR";
            default ->
                "Unknown";
        };
    }
    
    public String getCSVSafeValue(final String value){
        return Column.GetCSVSafeValue(this, value);
    }

    public static String GetCSVSafeValue(final Column column, final String value) {
        return switch (column.getType()) {
            case java.sql.Types.DECIMAL, java.sql.Types.DOUBLE, java.sql.Types.FLOAT, java.sql.Types.NUMERIC, java.sql.Types.REAL, java.sql.Types.BIGINT, java.sql.Types.INTEGER, java.sql.Types.SMALLINT, java.sql.Types.TINYINT -> value;
            default -> StringEscapeUtils.escapeCsv(value);
        };
    }

    public static Column GetFromResultSet(ResultSet rs) throws SQLException {
        return new Column(rs.getString(4), rs.getInt(5), false);
    }
}

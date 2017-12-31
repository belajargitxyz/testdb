package alfianyusufabdullah.ezqlite.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class TableConfiguration {

    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_VARCHAR = "VARCHAR";
    public static final String TYPE_REAL = "REAL";
    public static final String TYPE_BOOL = "BOOL";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String TYPE_BLOB = "BLOB";

    private static List<String> Column = new ArrayList<>();
    private static String TableName;

    public TableConfiguration(String tableName) {
        TableName = tableName;
    }

    public String getTableName() {
        return TableName;
    }

    public void addColumn(String ColumnName, String DataType) {
        Column.add(ColumnName + " " + DataType);
    }

    public void addColumn(String ColumnName, String DataType, String ColumnProperties) {
        Column.add(ColumnName + " " + DataType + " " + ColumnProperties);
    }

    public String getQueryCreate() {
        StringBuilder queryBuilder = new StringBuilder().append("CREATE TABLE IF NOT EXISTS ").append(TableName).append(" (");
        for (String column : Column) {
            queryBuilder.append(column).append(",");
        }
        queryBuilder.append(")");
        return queryBuilder.toString().replace(",)", ")");
    }
}
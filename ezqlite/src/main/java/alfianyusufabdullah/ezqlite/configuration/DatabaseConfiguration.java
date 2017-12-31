package alfianyusufabdullah.ezqlite.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class DatabaseConfiguration {

    private static String DATABASE_NAME = "databasename";
    private static String DATABASE_VERSION = "databaseversion";
    private static String QUERYS = "querys";
    private static String TABLES = "tables";

    private static SharedPreferences preferences;


    public DatabaseConfiguration(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getDatabaseName() {
        return preferences.getString(DATABASE_NAME, "db_name");
    }

    public void setDatabaseName(String databaseName) {
        preferences.edit().putString(DATABASE_NAME, databaseName).apply();
    }

    public int getDatabaseVersion() {
        return preferences.getInt(DATABASE_VERSION, 1);
    }

    public void setDatabaseVersion(int databaseVersion) {
        preferences.edit().putInt(DATABASE_VERSION, databaseVersion).apply();
    }

    public String[] getTableQuery() {
        return  preferences.getString(QUERYS, "query").split(",");
    }

    public void setTableQuery(List<String> tableQuery) {
        StringBuilder tableBuilder = new StringBuilder();
        for (String query : tableQuery) {
            tableBuilder.append(query).append(",");
        }

        String Query = tableBuilder.toString().substring(0, tableBuilder.toString().length() - 1);
        preferences.edit().putString(QUERYS, Query).apply();
    }

    public String[] getTableNames() {
        return preferences.getString(TABLES, "query").split(",");
    }

    public void setTableNames(List<String> tableNames) {
        StringBuilder tableBuilder = new StringBuilder();
        for (String table : tableNames) {
            tableBuilder.append(table).append(",");
        }

        String Tables = tableBuilder.toString().substring(0, tableBuilder.toString().length() - 1);
        preferences.edit().putString(TABLES, Tables).apply();
    }

}

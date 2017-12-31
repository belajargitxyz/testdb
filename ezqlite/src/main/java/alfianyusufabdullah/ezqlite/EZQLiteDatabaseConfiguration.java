package alfianyusufabdullah.ezqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import alfianyusufabdullah.ezqlite.configuration.DatabaseConfiguration;
import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.configuration.TableConfiguration;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class EZQLiteDatabaseConfiguration {

    private static String DatabaseName;
    private static int DatabaseVersion;
    private static List<TableConfiguration> tableConfigurations = new ArrayList<>();
    private static List<String> Query = new ArrayList<>();
    private static List<String> Tables = new ArrayList<>();

    private static EZQLiteDatabaseConfiguration configuration;

    private EZQLiteDatabaseConfiguration() {
    }

    public static EZQLiteDatabaseConfiguration getInstance() {
        configuration = new EZQLiteDatabaseConfiguration();
        return configuration;
    }

    public EZQLiteDatabaseConfiguration setDatabase(String DatabaseNames) {
        DatabaseName = DatabaseNames;
        return configuration;
    }

    public EZQLiteDatabaseConfiguration setDatabaseVersion(int DatabaseVersions) {
        DatabaseVersion = DatabaseVersions;
        return configuration;
    }

    public EZQLiteDatabaseConfiguration addTableConfiguration(TableConfiguration tableConfiguration) {
        tableConfigurations.add(tableConfiguration);
        return configuration;
    }

    public void build(Context context) {
        for (TableConfiguration tableConfiguration : tableConfigurations) {
            Query.add(tableConfiguration.getQueryCreate());
            Tables.add(tableConfiguration.getTableName());
        }

        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context.getApplicationContext());
        databaseConfiguration.setDatabaseName(DatabaseName);
        databaseConfiguration.setDatabaseVersion(DatabaseVersion);
        databaseConfiguration.setTableQuery(Query);
        databaseConfiguration.setTableNames(Tables);
    }
}

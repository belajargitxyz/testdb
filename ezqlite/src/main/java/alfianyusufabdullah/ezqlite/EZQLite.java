package alfianyusufabdullah.ezqlite;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import alfianyusufabdullah.ezqlite.configuration.DatabaseConfiguration;
import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.perform.Insert;
import alfianyusufabdullah.ezqlite.perform.Load;
import alfianyusufabdullah.ezqlite.perform.LoadWithRawQuery;
import alfianyusufabdullah.ezqlite.perform.Remove;
import alfianyusufabdullah.ezqlite.perform.Update;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class EZQLite {

    private DatabaseHelperConfiguration databaseHelperConfiguration;

    private EZQLite(Context context) {
        DatabaseConfiguration configuration = new DatabaseConfiguration(context);
        databaseHelperConfiguration = new DatabaseHelperConfiguration(context , configuration.getDatabaseName() , configuration.getDatabaseVersion() , configuration.getTableNames() , configuration.getTableQuery());
    }

    public static EZQLite getInstance(Activity activity) {
        return new EZQLite(activity.getApplicationContext());
    }

    public static EZQLite getInstance(Fragment fragment) {
        return new EZQLite(fragment.getActivity().getApplicationContext());
    }

    public Insert doInsert(String TableName) {
        return Insert.get(TableName, databaseHelperConfiguration);
    }

    public Load doLoad(String TableName) {
        return Load.init(TableName, databaseHelperConfiguration);
    }

    public Remove doRemove(String TableName) {
        return Remove.init(TableName, databaseHelperConfiguration);
    }

    public Update doUpdate(String TableName) {
        return Update.init(TableName, databaseHelperConfiguration);
    }

    public LoadWithRawQuery loadWithRawQuery(String Query) {
        return LoadWithRawQuery.init(Query, databaseHelperConfiguration);
    }

}

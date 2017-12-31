package alfianyusufabdullah.ezqlite.perform;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import alfianyusufabdullah.ezqlite.EZQLiteDatabaseConfiguration;
import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;

/**
 * Created by jonesrandom on 12/29/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class Remove {

    private String TableName;
    private String whereClause;
    private String[] whereArgs;
    private DatabaseHelperConfiguration databaseConfiguration;

    private static Remove remove;

    private Remove(String TableName, DatabaseHelperConfiguration configuration) {
        this.TableName = TableName;
        this.databaseConfiguration = configuration;
    }

    public static Remove init(String TableName, DatabaseHelperConfiguration configuration) {
        remove = new Remove(TableName, configuration);
        return remove;
    }

    public Remove whereClause(String whereClause) {
        remove.whereClause = whereClause;
        return remove;
    }

    public Remove whereArgs(String[] whereArgs) {
        remove.whereArgs = whereArgs;
        return remove;
    }

    public void remove(OnDatabaseCallback callback) {
        SQLiteDatabase database = databaseConfiguration.getWritableDatabase();
        try {
            long stat = database.delete(TableName, whereClause, whereArgs);
            if (stat > 0) {
                callback.Success();
            } else {
                callback.Failed("Something Wrong! , Check Your Configuration");
            }

        } catch (SQLiteException e) {
            callback.Failed(e.getMessage());
        }
    }
}

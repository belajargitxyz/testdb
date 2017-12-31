package alfianyusufabdullah.ezqlite.perform;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.CancellationSignal;

import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCursorCallback;

/**
 * Created by jonesrandom on 12/29/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class LoadWithRawQuery {

    private String Query;
    private DatabaseHelperConfiguration configuration;
    private String[] selectionArgs;
    private CancellationSignal cancellationSignal;

    private static LoadWithRawQuery loadWithRawQuery;

    private LoadWithRawQuery(String Query, DatabaseHelperConfiguration configuration) {
        this.Query = Query;
        this.configuration = configuration;
    }

    public static LoadWithRawQuery init(String Query, DatabaseHelperConfiguration configuration) {
        loadWithRawQuery = new LoadWithRawQuery(Query, configuration);
        return loadWithRawQuery;
    }

    public LoadWithRawQuery selectionArgs(String[] selectionArgs){
        loadWithRawQuery.selectionArgs = selectionArgs;
        return loadWithRawQuery;
    }

    public LoadWithRawQuery cancellationSignal(CancellationSignal cancellationSignal){
        loadWithRawQuery.cancellationSignal = cancellationSignal;
        return loadWithRawQuery;
    }

    public void load(OnDatabaseCursorCallback callback) {
        SQLiteDatabase database = configuration.getWritableDatabase();
        Cursor cursor = null;
        try {
            if (cancellationSignal != null) {
                cursor = database.rawQuery(Query, selectionArgs, cancellationSignal);
            } else {
                cursor = database.rawQuery(Query, selectionArgs);
            }

            if (cursor != null) {
                callback.Success(cursor);
            } else {
                callback.Failed("Something Wrong! , Check Your Configuration");
            }
        } catch (SQLiteException e) {
            callback.Failed(e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}

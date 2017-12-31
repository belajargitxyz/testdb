package alfianyusufabdullah.ezqlite.perform;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.CancellationSignal;

import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCursorCallback;

/**
 * Created by jonesrandom on 12/28/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class Load {

    private static Load load;
    private String TableName;
    private DatabaseHelperConfiguration databaseHelperConfiguration;

    private boolean isDistinct = false;
    private String[] columnn = null;
    private String selection = null;
    private String[] selectionArgs = null;
    private String groupBy = null;
    private String having = null;
    private String orderBy = null;
    private String limit = null;
    private CancellationSignal cancellationSignal = null;

    private Load(String TableName, DatabaseHelperConfiguration databaseHelperConfiguration) {
        this.TableName = TableName;
        this.databaseHelperConfiguration = databaseHelperConfiguration;

    }

    public static Load init(String TableName, DatabaseHelperConfiguration databaseHelperConfiguration) {
        load = new Load(TableName, databaseHelperConfiguration);
        return load;
    }

    public Load isDistinct() {
        load.isDistinct = true;
        return load;
    }

    public Load setColumn(String[] columns) {
        load.columnn = columns;
        return load;
    }

    public Load setSelection(String selection) {
        load.selection = selection;
        return load;
    }

    public Load setSelectionArgs(String[] selectionArgs) {
        load.selectionArgs = selectionArgs;
        return load;
    }

    public Load groupBy(String groupBy) {
        load.groupBy = groupBy;
        return load;
    }

    public Load having(String having) {
        load.having = having;
        return load;
    }

    public Load orderBy(String orderBy) {
        load.orderBy = orderBy;
        return load;
    }

    public Load setCancellationSigna(CancellationSignal cancellationSignal) {
        load.cancellationSignal = cancellationSignal;
        return load;
    }

    public Load limit(int limit) {
        load.limit = String.valueOf(limit);
        return load;
    }

    public void load(OnDatabaseCursorCallback callback) {
        SQLiteDatabase database = databaseHelperConfiguration.getWritableDatabase();
        Cursor cursor = null;
        try {
            if (isDistinct && cancellationSignal != null) {
                cursor = database.query(true, TableName, columnn, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
            } else {
                cursor = database.query(TableName, columnn, selection, selectionArgs, groupBy, having, orderBy, limit);
            }

            if (cursor != null) {
                callback.Success(cursor);
            }

        } catch (SQLiteException e) {
            callback.Failed(e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();

            }
        }
    }

    public Cursor load() {

        SQLiteDatabase database = databaseHelperConfiguration.getWritableDatabase();
        Cursor cursor = null;

        try {

            if (isDistinct && cancellationSignal != null) {
                cursor = database.query(true, TableName, columnn, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal);
            } else {
                cursor = database.query(TableName, columnn, selection, selectionArgs, groupBy, having, orderBy, limit);
            }

            if (cursor != null) {
                return cursor;
            } else {
                return null;
            }

        } catch (SQLiteException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}

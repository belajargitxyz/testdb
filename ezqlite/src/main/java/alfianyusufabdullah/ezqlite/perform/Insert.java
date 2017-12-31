package alfianyusufabdullah.ezqlite.perform;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class Insert {

    private String TableName;
    private String columnHack;
    private DatabaseHelperConfiguration DatabaseHelperConfiguration;
    private ContentValues values;

    private static Insert insert;

    private Insert(String tableName, DatabaseHelperConfiguration databaseHelperConfiguration) {
        TableName = tableName;
        DatabaseHelperConfiguration = databaseHelperConfiguration;

        values = new ContentValues();
    }

    public static Insert get(String tableName, DatabaseHelperConfiguration databaseHelperConfiguration) {
        insert = new Insert(tableName, databaseHelperConfiguration);
        return insert;
    }

    public Insert putValue(String Column, Object value) {

        if (value instanceof Short) {
            Short shorts = (Short) value;
            values.put(Column, shorts);
        } else if (value instanceof Long) {
            Long longs = (Long) value;
            values.put(Column, longs);
        } else if (value instanceof Integer) {
            Integer ints = (Integer) value;
            values.put(Column, ints);
        } else if (value instanceof Double) {
            Double doubles = (Double) value;
            values.put(Column, doubles);
        } else if (value instanceof String) {
            String str = (String) value;
            values.put(Column, str);
        } else if (value instanceof Boolean) {
            Boolean bool = (Boolean) value;
            values.put(Column, bool);
        } else if (value instanceof Float) {
            Float flot = (Float) value;
            values.put(Column, flot);
        } else if (value instanceof byte[]) {
            byte[] byt = (byte[]) value;
            values.put(Column, byt);
        } else if (value instanceof Byte) {
            Byte bytes = (Byte) value;
            values.put(Column, bytes);
        }

        return insert;
    }

    public Insert setColumnHack(String columnHack) {
        insert.columnHack = columnHack;
        return insert;
    }

    public void insert(OnDatabaseCallback callback) {
        SQLiteDatabase database = DatabaseHelperConfiguration.getWritableDatabase();
        try {
            long stat = database.insertOrThrow(TableName, columnHack, values);

            if (stat > 0){
                callback.Success();
            } else {
                callback.Failed("Something Wrong! , Check Your Configuration");
            }

        } catch (SQLiteException e) {
            callback.Failed(e.getMessage());
        }
    }

    public void insert() {
        SQLiteDatabase database = DatabaseHelperConfiguration.getWritableDatabase();
        try {
            database.insertOrThrow(TableName, columnHack, values);
        } catch (SQLiteException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

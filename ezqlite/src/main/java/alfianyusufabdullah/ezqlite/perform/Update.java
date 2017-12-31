package alfianyusufabdullah.ezqlite.perform;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import alfianyusufabdullah.ezqlite.configuration.DatabaseHelperConfiguration;
import alfianyusufabdullah.ezqlite.listener.OnDatabaseCallback;

/**
 * Created by jonesrandom on 12/29/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class Update {

    private String TableName;
    private String whereclause;
    private String[] whereArgs;
    private ContentValues values;
    private DatabaseHelperConfiguration configuration;

    private static Update update;

    private Update(String TableName , DatabaseHelperConfiguration configuration){
        this.TableName = TableName;
        this.configuration = configuration;

        values = new ContentValues();
    }

    public static Update init(String TableName , DatabaseHelperConfiguration configuration){
        update = new Update(TableName, configuration);
        return update;
    }

    public Update whereClause(String whereClause) {
        update.whereclause = whereClause;
        return update;
    }

    public Update whereArgs(String[] whereArgs) {
        update.whereArgs = whereArgs;
        return update;
    }

    public Update putValue(String Column, Object value) {

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

        return update;
    }


    public void update(OnDatabaseCallback callback){
        SQLiteDatabase database = configuration.getWritableDatabase();
        try {
            long stat = database.update(TableName , values, whereclause , whereArgs);
            if (stat > 0) {
                callback.Success();
            } else {
                callback.Failed("Something Wrong! , Check Your Configuration");
            }

        } catch (SQLiteException e){
            callback.Failed(e.getMessage());
        }
    }
}

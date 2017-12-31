package alfianyusufabdullah.ezqlite.configuration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by jonesrandom on 12/27/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public class DatabaseHelperConfiguration extends SQLiteOpenHelper {

    private String[] tables;
    private String[] querys;

    public DatabaseHelperConfiguration(Context context, String DatabaseName, int DatabaseVersion, String[] tables, String[] querys) {
        super(context, DatabaseName, null, DatabaseVersion);

        this.tables = tables;
        this.querys = querys;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (String Query : querys) {
            sqLiteDatabase.execSQL(Query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        for (String Tables : tables) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tables);
            onCreate(sqLiteDatabase);
        }
    }
}

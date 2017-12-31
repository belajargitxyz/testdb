package alfianyusufabdullah.ezqlite.listener;

import android.database.Cursor;

/**
 * Created by jonesrandom on 12/28/17.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 */

public interface OnDatabaseCursorCallback {

    void Success(Cursor cursor);

    void Failed(String Error);
}

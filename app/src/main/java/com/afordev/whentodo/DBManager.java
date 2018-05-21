package com.afordev.whentodo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pengu on 2018-02-11.
 */

public class DBManager extends SQLiteOpenHelper {

    private static DBManager instance;
    SQLiteDatabase db;
    Context context;

    private DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public static DBManager getInstance(Context mContext) {
        if (instance == null) {
            instance = new DBManager(mContext, "TodoWhen.db", null, 1);
        } else {
            instance.setContext(mContext);
        }
        return instance;
    }

    public void resetDB() {
        db = getWritableDatabase();
        db.execSQL("DROP TABLE TodoWhen;");
        onCreate(db);
        db.close();
        Toast.makeText(context, "DB가 초기화되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TodoWhen ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Title TEXT not null DEFAULT '', " +
                "AddDate TEXT not null, " +
                "RecentDate TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private DataWhen getTodoWhenWithCursor(Cursor cursor) {
        DataWhen data = new DataWhen(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return data;
    }

    public void insertWhen(DataWhen data) {
        db = getWritableDatabase();
        db.execSQL(" INSERT INTO TodoWhen VALUES ( " +
                " null, " +
                "'" + data.getTitle() + "', " +
                "datetime('now', 'localtime'), " +
                "datetime('now', 'localtime')); ");
//        db.execSQL(" INSERT INTO TodoWhen VALUES ( " +
//                " null, " +
//                "'" + data.getTitle() + "', " +
//                "'" + data.getAddDate() + "', " +
//                "'" + data.getRecentDate() + "'); ");
        db.close();
    }

    public void updateTodo(DataWhen data) {
        db = getWritableDatabase();
        db.execSQL(" UPDATE TodoWhen SET " +
                "Title = '" + data.getTitle() + "', " +
                "AddDate '" + data.getAddDate() + "', " +
                "RecentDate '" + data.getRecentDate() + "' " +
                "WHERE _id = " + data.getId() + " ; ");
        db.close();
    }

    public void checkTodo(DataWhen data) {
        db = getWritableDatabase();
        db.execSQL(" UPDATE TodoWhen SET " +
                "RecentDate = datetime('now', 'localtime') " +
                "WHERE _id = " + data.getId() + " ; ");
        db.close();
    }

    public void deleteTodo(int id) {
        db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoWhen WHERE _id = " + id + ";");
        db.close();
    }

    public DataWhen getTodoWhen(int id) {
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoWhen WHERE _id = " + id + ";", null);
        cursor.moveToLast();
        DataWhen data = getTodoWhenWithCursor(cursor);
        cursor.close();
        return data;
    }

    public ArrayList<DataWhen> getTodoWhenList() {
        db = getReadableDatabase();
        ArrayList<DataWhen> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoWhen;", null);
        while (cursor.moveToNext()) {
            list.add(getTodoWhenWithCursor(cursor));
        }
        cursor.close();
        return list;
    }
}
package com.bawei6.basemodule.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlite extends SQLiteOpenHelper {
    private Context context;
    private String name;
    private SQLiteDatabase.CursorFactory factory;
    private int version;
    public MySqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
        this.name=name;
        this.factory=factory;
        this.version=version;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table user(id integer primary key autoincrement," +
                    "toname varchar(20)," +
                    "fromname varchar(20)," +
                    "msgtype varchar(20)," +
                    "usercode varchar(20)," +
                    "mag varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

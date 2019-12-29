package com.rxjavademo.livedatademo2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FavouritesDbHelper extends SQLiteOpenHelper {

    public FavouritesDbHelper(@Nullable Context context) {
        super(context, DbSettings.DB_NAME, null, DbSettings.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + DbSettings.DBEntry.TABLE + " ( " +
                DbSettings.DBEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSettings.DBEntry.COL_FAV_URL + " TEXT NOT NULL, " +
                DbSettings.DBEntry.COL_FAV_DATE + " INTEGER NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbSettings.DBEntry.TABLE);
        onCreate(db);
    }
}

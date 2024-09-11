package com.melrs.mingle.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mingle.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MINGLE_ITEMS = "mingle_items";
    public static final String TABLE_USER_BALANCE = "user_balance";

    private static final String CREATE_TABLE_MINGLE_ITEMS =
            "CREATE TABLE " + TABLE_MINGLE_ITEMS + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "userId TEXT, " +
                    "friendId TEXT, " +
                    "amount FLOAT, " +
                    "currencyCode CHAR(3), " +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "description TEXT, " +
                    "type CHAR(2), " +
                    "status CHAR(2), " +
                    "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String TRIGGER_MINGLE_ITEMS_UPDATED_AT =
            "CREATE TRIGGER update_mingle_items_updated_at " +
                    "AFTER UPDATE ON " + TABLE_MINGLE_ITEMS + " " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "UPDATE " + TABLE_MINGLE_ITEMS + " " +
                    "SET updated_at = CURRENT_TIMESTAMP " +
                    "WHERE id = OLD.id; " +
                    "END;";

    private static final String CREATE_TABLE_USER_BALANCE =
            "CREATE TABLE " + TABLE_USER_BALANCE + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "userId TEXT, " +
                    "balance FLOAT, " +
                    "currencyCode CHAR(3), " +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String TRIGGER_USER_BALANCE_UPDATED_AT =
            "CREATE TRIGGER update_user_balance_updated_at " +
                    "AFTER UPDATE ON " + TABLE_USER_BALANCE + " " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                    "UPDATE " + TABLE_USER_BALANCE + " " +
                    "SET updated_at = CURRENT_TIMESTAMP " +
                    "WHERE id = OLD.id; " +
                    "END;";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MINGLE_ITEMS);
        db.execSQL(TRIGGER_MINGLE_ITEMS_UPDATED_AT);
        db.execSQL(CREATE_TABLE_USER_BALANCE);
        db.execSQL(TRIGGER_USER_BALANCE_UPDATED_AT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MINGLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_BALANCE);
        onCreate(db);
    }
}
package com.melrs.mingle.data.repositories.mingleItem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.database.DatabaseHelper;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.util.MingleContentValues;

import java.util.ArrayList;
import java.util.List;

public class MingleItemDatabaseRepository implements MingleItemRepository {

    List<MingleItem> mingleActivities;
    private final DatabaseHelper dbHelper;

    public MingleItemDatabaseRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        mingleActivities = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public MingleItem getMingleItemById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            MingleItem mingleItem = cursorToMingleItem(cursor);
            cursor.close();
            db.close();
            return mingleItem;
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<MingleItem> getUserMingleItems(String userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                "userId = ?",
                new String[]{userId},
                null,
                null,
                null
        );

        List<MingleItem> mingleItems = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mingleItems.add(cursorToMingleItem(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return mingleItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<MingleItem> getUserMingleItemsByType(String userId, MingleType type) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                "userId = ? AND type = ?",
                new String[]{userId, type.getCode()},
                null,
                null,
                null
        );

        List<MingleItem> mingleItems = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mingleItems.add(cursorToMingleItem(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return mingleItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<MingleItem> getUserMingleItemsByStatus(String userId, MingleStatus status) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                "userId = ? AND status = ?",
                new String[]{userId, status.getCode()},
                null,
                null,
                null
        );

        List<MingleItem> mingleItems = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mingleItems.add(cursorToMingleItem(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return mingleItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<MingleItem> getUserMingleItemsWithFriend(String userId, String friendId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                "userId = ? AND friendId = ?",
                new String[]{userId, friendId},
                null,
                null,
                null
        );

        List<MingleItem> mingleItems = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mingleItems.add(cursorToMingleItem(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return mingleItems;
    }

    @Override
    public void saveMingleItem(MingleItem mingleItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                null,
                MingleContentValues.from(mingleItem)
        );
        db.close();
    }

    @Override
    public void deleteMingleItem(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_MINGLE_ITEMS, "id = ?", new String[]{String.valueOf(id)});
        db.close();    }

    @Override
    public void updateMingleItem(MingleItem mingleItem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update(
                DatabaseHelper.TABLE_MINGLE_ITEMS,
                MingleContentValues.from(mingleItem),
                "id = ?",
                new String[]{String.valueOf(mingleItem.getId())}
        );
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private MingleItem cursorToMingleItem(Cursor cursor) {
       return MingleItem.create(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("userId")),
                cursor.getString(cursor.getColumnIndexOrThrow("friendId")),
                cursor.getString(cursor.getColumnIndexOrThrow("amount")),
                cursor.getString(cursor.getColumnIndexOrThrow("currencyCode")),
                cursor.getString(cursor.getColumnIndexOrThrow("created_at")),
                cursor.getString(cursor.getColumnIndexOrThrow("description")),
                cursor.getString(cursor.getColumnIndexOrThrow("type")),
                cursor.getString(cursor.getColumnIndexOrThrow("status")),
                cursor.getString(cursor.getColumnIndexOrThrow("updated_at"))
        );
    }
}

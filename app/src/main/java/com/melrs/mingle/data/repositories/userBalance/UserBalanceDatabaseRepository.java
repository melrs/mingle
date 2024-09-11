package com.melrs.mingle.data.repositories.userBalance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.melrs.mingle.data.database.DatabaseHelper;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.util.UserBalanceContentValues;

public class UserBalanceDatabaseRepository implements UserBalanceRepository {

    private final DatabaseHelper dbHelper;

    public UserBalanceDatabaseRepository(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    public UserBalance getUserBalanceByUserId(String userId) {
        return queryUserBalance("userId = ?", new String[]{userId});
    }

    @Override
    public void saveUserBalance(UserBalance userBalance) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.insert(
                    DatabaseHelper.TABLE_USER_BALANCE,
                    null,
                    UserBalanceContentValues.from(userBalance)
            );
        }
    }

    @Override
    public void deleteUserBalance(String id) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.delete(
                    DatabaseHelper.TABLE_USER_BALANCE,
                    "userId = ?",
                    new String[]{id}
            );
        } catch (Exception ignored) {

        }

    }

    @Override
    public void updateUserBalance(UserBalance userBalance) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {
            db.update(
                    DatabaseHelper.TABLE_USER_BALANCE,
                    UserBalanceContentValues.from(userBalance),
                    "userId = ?",
                    new String[]{userBalance.getUserId()}
            );
        }
    }

    private UserBalance queryUserBalance(String selection, String[] selectionArgs) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase();
             Cursor cursor = db.query(
                     DatabaseHelper.TABLE_USER_BALANCE,
                     null,
                     selection,
                     selectionArgs,
                     null,
                     null,
                     null
             )) {
            if (cursor != null && cursor.moveToFirst()) {
                return cursorToUserBalance(cursor);
            }
        }
        return null;
    }

    private UserBalance cursorToUserBalance(Cursor cursor) {
        int userIdIndex = cursor.getColumnIndexOrThrow("userId");int balanceIndex = cursor.getColumnIndexOrThrow("balance");
        int currencyCodeIndex = cursor.getColumnIndexOrThrow("currencyCode");

        return UserBalance.create(
                cursor.getString(userIdIndex),
                cursor.getString(balanceIndex),
                cursor.getString(currencyCodeIndex)
        );
    }
}
package com.melrs.mingle.util;

import android.content.ContentValues;

import com.melrs.mingle.data.model.UserBalance;

public class UserBalanceContentValues {
    public static ContentValues from(UserBalance userBalance) {
        ContentValues values = new ContentValues();
        values.put("userId", userBalance.getUserId());
        values.put("balance", userBalance.getBalance().getNumber().toString());
        values.put("currencyCode", userBalance.getBalance().getCurrency().getCurrencyCode());
        return values;
    }
}
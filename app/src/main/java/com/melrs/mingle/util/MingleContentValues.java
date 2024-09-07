package com.melrs.mingle.util;

import android.content.ContentValues;

import com.melrs.mingle.data.model.MingleItem;

public class MingleContentValues {

    public static ContentValues from(MingleItem mingleItem) {
        ContentValues values = new ContentValues();
        values.put("userId", mingleItem.getUserId());
        values.put("friendId", mingleItem.getFriendId());
        values.put("amount", mingleItem.getAmount().getNumber().toString());
        values.put("currencyCode", mingleItem.getAmount().getCurrency().getCurrencyCode());
        values.put("description", mingleItem.getDescription());
        values.put("type", mingleItem.getType().getCode());
        values.put("status", mingleItem.getStatus().getCode());
        return values;
    }

}
package com.melrs.mingle.data.model;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;

import java.util.Currency;

public class MingleItemBuilder {

    private String description;
    private String amount;
    private MingleType type;
    private String userId;
    private String friendId;
    private MingleStatus status;

    public static MingleItemBuilder create() {
        return new MingleItemBuilder();
    }

    public MingleItemBuilder friend(String friendId) {
        this.friendId = friendId;
        return this;
    }

    public MingleItemBuilder user(String userId) {
        this.userId = userId;
        return this;
    }

    public MingleItemBuilder amount(String amount) {
        this.amount = amount;
        return this;
    }

    public MingleItemBuilder type(MingleType type) {
        this.type = type;
        return this;
    }

    public MingleItemBuilder description(String description) {
        this.description = description;
        return this;
    }

    public MingleItemBuilder status(MingleStatus status) {
        this.status = status;
        return this;
    }

    public MingleItem build() {
        return MingleItem.newItem(
                userId,
                friendId,
                amount,
                Currency.getInstance("BRL").toString(),
                description,
                type.getCode(),
                status.getCode()
        );
    }

}

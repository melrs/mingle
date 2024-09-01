package com.melrs.mingle.list;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;

public class MingleItem {

    private String name;
    private String amount;
    private MingleStatus status;
    private MingleType type;

    public MingleItem(String name, String amount, MingleStatus status, MingleType type) {
        this.name = name;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public MingleStatus getStatus() {
        return status;
    }

    public MingleType getType() {
        return type;
    }

}

package com.melrs.mingle.data.model;

public class MingleUser {

    private final int userId;
    private final String displayName;

    public MingleUser(int userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
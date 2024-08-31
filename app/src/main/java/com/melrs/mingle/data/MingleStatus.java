package com.melrs.mingle.data;

public enum MingleStatus {
    PE("PE", "Pending"),
    PA("PA", "Paid"),
    CA("CA", "Cancelled"),
    MI("MI", "Mingled");

    private final String code;
    private final String description;

    MingleStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
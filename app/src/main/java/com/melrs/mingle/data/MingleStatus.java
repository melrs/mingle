package com.melrs.mingle.data;

public enum MingleStatus {
    OP("OP", "Open"),
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

    public static String[] cases() {
        MingleStatus[] statuses = MingleStatus.values();
        String[] values = new String[statuses.length];
        for (int i = 0; i < statuses.length; i++) {
            values[i] = statuses[i].getCode();
        }

        return values;
    }

}
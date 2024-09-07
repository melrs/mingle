package com.melrs.mingle.data;

public enum MingleType {
    MI("MI", "Mingle In"),
    MO("MO", "Mingle Out");

    private final String code;
    private final String description;

    MingleType(String code, String description) {
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
        MingleType[] statuses = MingleType.values();
        String[] values = new String[statuses.length];
        for (int i = 0; i < statuses.length; i++) {
            values[i] = statuses[i].getCode();
        }

        return values;
    }
}
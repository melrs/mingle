package com.melrs.mingle.utils;

public enum FirestoreCollection {

    USER("user");

    private final String name;

    FirestoreCollection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
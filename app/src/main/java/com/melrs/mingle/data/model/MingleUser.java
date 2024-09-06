package com.melrs.mingle.data.model;

import androidx.annotation.Nullable;

public class MingleUser {

    public @Nullable String userId;
    public @Nullable String displayName;
    public @Nullable String username;
    public @Nullable String birthDate;
    public @Nullable String pronouns;
    public @Nullable String email;
    public @Nullable String balance;
    public @Nullable String minglerSince;

    public MingleUser(
            @Nullable String userId,
            @Nullable String displayName,
            @Nullable String username,
            @Nullable String birthDate,
            @Nullable String pronouns,
            @Nullable String email,
            @Nullable String balance,
            @Nullable String minglerSince
    ) {
        this.userId = userId;
        this.displayName = displayName;
        this.username = username;
        this.birthDate = birthDate;
        this.pronouns = pronouns;
        this.email = email;
        this.balance = balance;
        this.minglerSince = minglerSince;
    }

    public MingleUser() {
        this.userId = "";
        this.displayName = "";
        this.username = "";
        this.birthDate = "";
        this.pronouns = "";
        this.email = "";
        this.balance = "";
        this.minglerSince = "";
    }

    public MingleUser(
            @Nullable String userId,
            @Nullable String displayName
    ) {
        this.userId = userId;
        this.displayName = displayName;
        this.username = "someUser";
        this.birthDate = "someDate";
        this.pronouns = "pronouns";
        this.email = "email";
        this.balance = "balance";
        this.minglerSince = "11/12/1990";
    }

    public static MingleUser empty() {
        return new MingleUser();
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    @Nullable
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public String getUsername() {
        return username;
    }

    @Nullable
    public String getBirthDate() {
        return birthDate;
    }

    @Nullable
    public String getPronouns() {
        return pronouns;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getBalance() {
        return balance;
    }

    @Nullable
    public String getMinglerSince() {
        return minglerSince;
    }

    public void setUserId(@Nullable String userId) {
        this.userId = userId;
    }

    public void setDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public void setBirthDate(@Nullable String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPronouns(@Nullable String pronouns) {
        this.pronouns = pronouns;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setBalance(@Nullable String balance) {
        this.balance = balance;
    }

    public void setMinglerSince(@Nullable String minglerSince) {
        this.minglerSince = minglerSince;
    }

    public boolean isEmpty() {
        return userId.isEmpty() && displayName.isEmpty() && username.isEmpty() && birthDate.isEmpty() && pronouns.isEmpty() && email.isEmpty() && balance.isEmpty() && minglerSince.isEmpty();
    }
}
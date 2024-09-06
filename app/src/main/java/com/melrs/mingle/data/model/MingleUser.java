package com.melrs.mingle.data.model;

import androidx.annotation.Nullable;

<<<<<<< HEAD
import java.util.Calendar;

=======
>>>>>>> 182f0d2 (User Profile)
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

<<<<<<< HEAD
    private MingleUser() {
=======
    public MingleUser() {
>>>>>>> 182f0d2 (User Profile)
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

<<<<<<< HEAD
    private MingleUser(
            @Nullable String userId,
            @Nullable String email,
            @Nullable String username
    ) {
        this.userId = userId;
        this.displayName = username;
        this.username = username;
        this.email = email;
        this.minglerSince = Calendar.getInstance().getTime().toString();
    }


=======
>>>>>>> 182f0d2 (User Profile)
    public static MingleUser empty() {
        return new MingleUser();
    }

<<<<<<< HEAD
    public static MingleUser createNew(
            String userId,
            String username,
            String email
    ) {
        return new MingleUser(userId, email, username);
    }

=======
>>>>>>> 182f0d2 (User Profile)
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
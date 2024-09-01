package com.melrs.mingle.data.model;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

public class UserBalance {

    private final int userId;
    private final MonetaryAmount balance;

    private UserBalance(int userId, String balance, String currencyCode) {
        this.userId = userId;
        this.balance = Monetary.getDefaultAmountFactory().setNumber(Double.parseDouble(balance)).setCurrency(currencyCode).create();
    }

    public static UserBalance create(int userId, String balance, String currencyCode) {
        return new UserBalance(userId, balance, currencyCode);
    }

    public int getUserId() {
        return userId;
    }

    public MonetaryAmount getBalance() {
        return balance;
    }
}
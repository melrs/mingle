package com.melrs.mingle.data.repositories.userBalance;

import android.content.Context;

public class UserBalanceRepositoryResolver {
    private static UserBalanceRepository repository;

    public static UserBalanceRepository resolve(Context context) {
        return repository == null ? repository = new UserBalanceDatabaseRepository(context) : repository;
    }

}

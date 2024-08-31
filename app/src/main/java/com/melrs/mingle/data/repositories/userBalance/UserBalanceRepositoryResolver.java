package com.melrs.mingle.data.repositories.userBalance;

public class UserBalanceRepositoryResolver {

    public static UserBalanceRepository resolve() {
        return new UserBalanceInMemoryRepository();
    }

}

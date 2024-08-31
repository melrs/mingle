package com.melrs.mingle.data.repositories.userBalance;

import com.melrs.mingle.data.model.UserBalance;

import java.util.Collections;
import java.util.List;

public class UserBalanceInMemoryRepository implements UserBalanceRepository {

    List<UserBalance> balances;

    public UserBalanceInMemoryRepository() {
        balances = Collections.emptyList();
    }

    @Override
    public UserBalance getUserBalanceByUserId(int id) {
        return this.balances.stream()
            .filter(balance -> balance.getUserId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public void saveUserBalance(UserBalance userBalance) {
        this.balances.add(userBalance);
    }

    @Override
    public void deleteUserBalance(int id) {
        this.balances.removeIf(balance -> balance.getUserId() == id);
    }

    @Override
    public void updateUserBalance(UserBalance userBalance) {
        this.balances
            .stream()
            .filter(balance -> balance.getUserId() == userBalance.getUserId())
            .findFirst()
            .ifPresent(mingleActivity1 -> mingleActivity1 = userBalance);
    }
}

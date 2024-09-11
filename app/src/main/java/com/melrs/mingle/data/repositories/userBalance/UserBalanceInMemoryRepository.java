package com.melrs.mingle.data.repositories.userBalance;

import com.melrs.mingle.data.model.UserBalance;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserBalanceInMemoryRepository implements UserBalanceRepository {

    List<UserBalance> balances;

    public UserBalanceInMemoryRepository() {
        balances = Collections.emptyList();
    }

    @Override
    public UserBalance getUserBalanceByUserId(String id) {
        return this.balances.stream()
            .filter(balance -> Objects.equals(balance.getUserId(), id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void deleteUserBalance(String id) {
        this.balances.removeIf(balance -> Objects.equals(balance.getUserId(), id));
    }

    @Override
    public void upsertUserBalance(UserBalance userBalance) {
        this.balances
            .stream()
            .filter(balance -> Objects.equals(balance.getUserId(), userBalance.getUserId()))
            .findFirst()
            .ifPresent(mingleActivity1 -> mingleActivity1 = userBalance);
    }
}

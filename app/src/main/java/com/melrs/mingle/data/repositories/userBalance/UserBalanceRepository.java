package com.melrs.mingle.data.repositories.userBalance;

import com.melrs.mingle.data.model.UserBalance;

public interface UserBalanceRepository {

    UserBalance getUserBalanceByUserId(String id);

    void deleteUserBalance(String id);
    void upsertUserBalance(UserBalance userBalance);
}

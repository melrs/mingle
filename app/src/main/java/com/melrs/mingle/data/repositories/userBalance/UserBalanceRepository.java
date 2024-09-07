package com.melrs.mingle.data.repositories.userBalance;

import com.melrs.mingle.data.model.UserBalance;

public interface UserBalanceRepository {

    UserBalance getUserBalanceByUserId(String id);
    void saveUserBalance(UserBalance userBalance);
    void deleteUserBalance(String id);
    void updateUserBalance(UserBalance userBalance);

}

package com.melrs.mingle.data.repositories.userBalance;

import com.melrs.mingle.data.model.UserBalance;

public interface UserBalanceRepository {

    UserBalance getUserBalanceByUserId(int id);
    void saveUserBalance(UserBalance userBalance);
    void deleteUserBalance(int id);
    void updateUserBalance(UserBalance userBalance);

}

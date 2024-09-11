package com.melrs.mingle.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.melrs.mingle.data.MingleItemLiveDataManager;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepository;
import com.melrs.mingle.data.repositories.userBalance.UserBalanceRepositoryResolver;

import java.util.List;

public class UserBalanceViewModel extends ViewModel {

    private final MutableLiveData<UserBalance> userBalance = new MutableLiveData<>();
    private final MingleUser mingleUser;

    public UserBalanceViewModel(
            MingleUser mingleUser,
            MingleItemRepository mingleItemRepository
    ) {
        this.mingleUser = mingleUser;
    }

    public LiveData<UserBalance> getUserBalance() {
        return userBalance;
    }


}
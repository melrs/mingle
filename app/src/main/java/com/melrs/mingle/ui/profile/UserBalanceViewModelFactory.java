package com.melrs.mingle.ui.profile;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepository;

public class UserBalanceViewModelFactory implements ViewModelProvider.Factory {

    private final MingleUser mingleUser;
    private final MingleItemRepository mingleItemRepository;

    public UserBalanceViewModelFactory(MingleUser mingleUser, MingleItemRepository mingleItemRepository) {
        this.mingleUser = mingleUser;
        this.mingleItemRepository = mingleItemRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserBalanceViewModel.class)) {
            return (T) new UserBalanceViewModel(mingleUser, mingleItemRepository);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }


}

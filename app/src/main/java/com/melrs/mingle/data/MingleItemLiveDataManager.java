package com.melrs.mingle.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepository;

import java.util.List;

public class MingleItemLiveDataManager {

    private final MingleItemRepository mingleItemRepository;
    private final MutableLiveData<List<MingleItem>> userMingleItemsLiveData = new MutableLiveData<>();

    public MingleItemLiveDataManager(MingleItemRepository mingleItemRepository) {
        this.mingleItemRepository = mingleItemRepository;
    }

    public LiveData<List<MingleItem>> getUserMingleItemsLiveData(String userId) {
        loadUserMingleItems(userId);
        return userMingleItemsLiveData;
    }

    private void loadUserMingleItems(String userId) {
        List<MingleItem> mingleItems = mingleItemRepository.getUserMingleItems(userId);
        userMingleItemsLiveData.setValue(mingleItems);
    }

    public void saveMingleItem(MingleItem mingleItem) {
        mingleItemRepository.saveMingleItem(mingleItem);
        loadUserMingleItems(mingleItem.getUserId());
    }

    public void deleteMingleItem(int id) {
        String userId = mingleItemRepository.getMingleItemById(id).getUserId();
        mingleItemRepository.deleteMingleItem(id);
        loadUserMingleItems(userId);
    }

    public void updateMingleItem(MingleItem mingleItem) {
        mingleItemRepository.updateMingleItem(mingleItem);
        loadUserMingleItems(mingleItem.getUserId());
    }
}
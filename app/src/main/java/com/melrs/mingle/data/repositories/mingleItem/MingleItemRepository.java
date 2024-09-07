package com.melrs.mingle.data.repositories.mingleItem;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;

import java.util.List;

public interface MingleItemRepository {

    MingleItem getMingleItemById(int id);
    List<MingleItem> getUserMingleItems(String userId);
    List<MingleItem> getUserMingleItemsByType(String userId, MingleType type);
    List<MingleItem> getUserMingleItemsByStatus(String userId, MingleStatus status);
    List<MingleItem> getUserMingleItemsWithFriend(String userId, String friendId);
    void saveMingleItem(MingleItem mingleItem);
    void deleteMingleItem(int id);
    void updateMingleItem(MingleItem mingleItem);

}

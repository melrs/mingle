package com.melrs.mingle.data.mingleItem;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;

import java.util.List;

public interface MingleItemRepository {

    MingleItem getMingleItemById(int id);
    List<MingleItem> getUserMingleItems(int userId);
    List<MingleItem> getUserMingleItemsByType(int userId, MingleType type);
    List<MingleItem> getUserMingleItemsByStatus(int userId, MingleStatus status);
    List<MingleItem> getUserMingleItemsWithFriend(int userId, int friendId);
    void saveMingleItem(MingleItem mingleItem);
    void deleteMingleItem(int id);
    void updateMingleItem(MingleItem mingleItem);

}

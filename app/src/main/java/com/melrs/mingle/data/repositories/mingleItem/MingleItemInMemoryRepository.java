package com.melrs.mingle.data.repositories.mingleItem;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MingleItemInMemoryRepository implements MingleItemRepository {

    List<MingleItem> mingleActivities;

    public MingleItemInMemoryRepository() {
        mingleActivities = new ArrayList<>();
    }

    @Override
    public MingleItem getMingleItemById(int id) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<MingleItem> getUserMingleItems(String userId) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> Objects.equals(mingleActivity.getUserId(), userId))
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleItemsByType(String userId, MingleType type) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getType() == type)
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleItemsByStatus(String userId, MingleStatus status) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getStatus() == status)
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleItemsWithFriend(String userId, String friendId) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> Objects.equals(mingleActivity.getFriendId(), friendId))
            .collect(Collectors.toList());
    }

    @Override
    public void saveMingleItem(MingleItem mingleItem) {
        this.mingleActivities.add(mingleItem);
    }

    @Override
    public void deleteMingleItem(int id) {
        this.mingleActivities.removeIf(mingleActivity -> mingleActivity.getId() == id);
    }

    @Override
    public void updateMingleItem(MingleItem mingleItem) {
        this.mingleActivities
            .stream()
            .filter(mingleActivity1 -> mingleActivity1.getId() == mingleItem.getId())
            .findFirst()
            .ifPresent(mingleActivity1 -> mingleActivity1 = mingleItem);
    }
}

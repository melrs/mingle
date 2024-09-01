package com.melrs.mingle.data.repositories.mingleActivity;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MingleActivityInMemoryRepository implements MingleActivityRepository {

    List<MingleItem> mingleActivities;

    public MingleActivityInMemoryRepository() {
        mingleActivities = Collections.emptyList();
    }

    @Override
    public MingleItem getMingleActivityById(int id) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<MingleItem> getUserMingleActivities(int userId) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getUserId() == userId)
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleActivitiesByType(int userId, MingleType type) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getType() == type)
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleActivitiesByStatus(int userId, MingleStatus status) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getStatus() == status)
            .collect(Collectors.toList());
    }

    @Override
    public List<MingleItem> getUserMingleActivitiesWithFriend(int userId, int friendId) {
        return this.mingleActivities
            .stream()
            .filter(mingleActivity -> mingleActivity.getFriendId() == friendId)
            .collect(Collectors.toList());
    }

    @Override
    public void saveMingleActivity(MingleItem mingleItem) {
        this.mingleActivities.add(mingleItem);
    }

    @Override
    public void deleteMingleActivity(int id) {
        this.mingleActivities.removeIf(mingleActivity -> mingleActivity.getId() == id);
    }

    @Override
    public void updateMingleActivity(MingleItem mingleItem) {
        this.mingleActivities
            .stream()
            .filter(mingleActivity1 -> mingleActivity1.getId() == mingleItem.getId())
            .findFirst()
            .ifPresent(mingleActivity1 -> mingleActivity1 = mingleItem);
    }
}

package com.melrs.mingle.data.repositories.mingleActivity;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;

import java.util.List;

public interface MingleActivityRepository {

    MingleItem getMingleActivityById(int id);
    List<MingleItem> getUserMingleActivities(int userId);
    List<MingleItem> getUserMingleActivitiesByType(int userId, MingleType type);
    List<MingleItem> getUserMingleActivitiesByStatus(int userId, MingleStatus status);
    List<MingleItem> getUserMingleActivitiesWithFriend(int userId, int friendId);
    void saveMingleActivity(MingleItem mingleItem);
    void deleteMingleActivity(int id);
    void updateMingleActivity(MingleItem mingleItem);

}

package com.melrs.mingle.data.mingleActivity;

import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleActivity;

import java.util.List;

public interface MingleActivityRepository {

    MingleActivity getMingleActivityById(int id);
    List<MingleActivity> getUserMingleActivities(int userId);
    List<MingleActivity> getUserMingleActivitiesByType(int userId, MingleType type);
    List<MingleActivity> getUserMingleActivitiesByStatus(int userId, MingleStatus status);
    List<MingleActivity> getUserMingleActivitiesWithFriend(int userId, int friendId);
    void saveMingleActivity(MingleActivity mingleActivity);
    void deleteMingleActivity(int id);
    void updateMingleActivity(MingleActivity mingleActivity);

}

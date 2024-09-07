package com.melrs.mingle.data.repositories.user;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.melrs.mingle.data.model.MingleUser;

public interface UserRepository {

    void upsert(MingleUser user, OnCompleteListener<Void> onCompleteListener, OnFailureListener onFailureListener);
    Task<MingleUser> getUserInfoById(String userId);
}

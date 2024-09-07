package com.melrs.mingle.data.repositories.user;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.melrs.mingle.data.model.MingleUser;

public interface UserRepository {

    public void upsert(MingleUser user, OnCompleteListener<Void> onCompleteListener, OnFailureListener onFailureListener);
    public Task<MingleUser> getUserInfoById(String userId);
    public void listenForProfileChanges(String userId, EventListener<DocumentSnapshot> listener);
}

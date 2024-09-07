package com.melrs.mingle.data.repositories.user;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.utils.FirestoreCollection;

public class FirebaseUserRepository implements UserRepository {

    private final FirebaseFirestore db;

    public FirebaseUserRepository() {
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void upsert(
            MingleUser user,
            OnCompleteListener<Void> onCompleteListener,
            OnFailureListener onFailureListener
    ) {
        assert user.getUserId() != null;
        db.collection(FirestoreCollection.USER.getName())
            .document(user.getUserId())
            .set(user)
            .addOnCompleteListener(onCompleteListener)
            .addOnFailureListener(onFailureListener);
    }

    @Override
    public Task<MingleUser> getUserInfoById(String userId) {
        return db.collection(FirestoreCollection.USER.getName())
            .document(userId)
            .get()
            .continueWith(task -> {
                if (task.isSuccessful() && task.getResult() != null && task.getResult().exists()) {
                    return task.getResult().toObject(MingleUser.class);
                } else {
                    return null;
                }
            });
    }

}
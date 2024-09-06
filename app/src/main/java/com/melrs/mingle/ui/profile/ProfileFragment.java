package com.melrs.mingle.ui.profile;

<<<<<<< HEAD
=======
import static android.content.ContentValues.TAG;

>>>>>>> 182f0d2 (User Profile)
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> 182f0d2 (User Profile)
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
<<<<<<< HEAD

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.repositories.user.UserRepository;
import com.melrs.mingle.data.repositories.user.UserRepositoryResolver;
=======
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.utils.FirestoreCollection;

import java.util.Objects;
>>>>>>> 182f0d2 (User Profile)

public class ProfileFragment extends Fragment {

    FragmentManager fragmentManager;
    MingleUser profile;
    TextView name;
    TextView username;
    TextView email;
    TextView birthday;
    TextView pronouns;
    TextView minglerSince;


    public ProfileFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
<<<<<<< HEAD
=======
        this.profile = MingleUser.empty();
>>>>>>> 182f0d2 (User Profile)
    }

    public static ProfileFragment newInstance(FragmentManager fragmentManager) {
        return new ProfileFragment(fragmentManager);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
<<<<<<< HEAD
        setUpUserProfile(view);
=======
        bindUIComponents(view);
>>>>>>> 182f0d2 (User Profile)
        setupEditProfileButtonListener(view);
        return view;
    }

<<<<<<< HEAD
    private void setUpUserProfile(View view) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        UserRepository userRepository = UserRepositoryResolver.resolve();
        userRepository.getUserInfoById(user.getUid()).addOnCompleteListener(u-> handleCompleteTask(view, u, user));
        userRepository.listenForProfileChanges(user.getUid(), (documentSnapshot, e) -> {
            if (documentSnapshot != null && documentSnapshot.exists()) {
                profile = documentSnapshot.toObject(MingleUser.class);
                bindUIComponents(view);
            }
        });
    }

    private void handleCompleteTask(View view, Task<MingleUser> u, FirebaseUser user) {
        if (u.isSuccessful()) {
            this.profile = u.getResult();
        } else {
            this.profile = MingleUser.createNew(user.getUid(), user.getEmail(), user.getEmail());
        }

        bindUIComponents(view);
    }

=======
>>>>>>> 182f0d2 (User Profile)
    private void bindUIComponents(View view) {
        name = view.findViewById(R.id.userNameView);
        username = view.findViewById(R.id.nameTextView);
        email = view.findViewById(R.id.emailTextView);
        birthday = view.findViewById(R.id.birthdayTextView);
        pronouns = view.findViewById(R.id.pronounsTextView);
        minglerSince = view.findViewById(R.id.minglerSinceTextView);

<<<<<<< HEAD
        name.setText(profile.getDisplayName());
        username.setText(profile.getUsername());
        email.setText(profile.getEmail());
        birthday.setText(profile.getBirthDate());
        pronouns.setText(profile.getPronouns());
        minglerSince.setText(profile.getMinglerSince());
=======
        String userEmail = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        assert userEmail != null;
        db.collection(FirestoreCollection.USER.getName()).document(userEmail)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        profile = documentSnapshot.toObject(MingleUser.class);
                        name.setText(profile.getDisplayName());
                        username.setText(profile.getUsername());
                        email.setText(profile.getEmail());
                        birthday.setText(profile.getBirthDate());
                        pronouns.setText(profile.getPronouns());
                    }
                })
                .addOnFailureListener(e -> {
                    String message = "Failed to update profile";
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                });


>>>>>>> 182f0d2 (User Profile)
    }

    private void setupEditProfileButtonListener(View view) {
        Button editProfileButton = view.findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,  EditProfileFragment.newInstance(fragmentManager, profile));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
    }

}
package com.melrs.mingle.ui.profile;

import static android.content.ContentValues.TAG;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
        this.profile = MingleUser.empty();
    }

    public static ProfileFragment newInstance(FragmentManager fragmentManager) {
        return new ProfileFragment(fragmentManager);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        bindUIComponents(view);
        setupEditProfileButtonListener(view);
        return view;
    }

    private void bindUIComponents(View view) {
        name = view.findViewById(R.id.userNameView);
        username = view.findViewById(R.id.nameTextView);
        email = view.findViewById(R.id.emailTextView);
        birthday = view.findViewById(R.id.birthdayTextView);
        pronouns = view.findViewById(R.id.pronounsTextView);
        minglerSince = view.findViewById(R.id.minglerSinceTextView);

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
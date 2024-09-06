package com.melrs.mingle.ui.profile;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.repositories.user.UserRepositoryResolver;
=======
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.utils.FirestoreCollection;
>>>>>>> 182f0d2 (User Profile)

import java.util.Objects;

public class EditProfileFragment extends Fragment {

<<<<<<< HEAD
    FragmentManager fragmentManager;
    MingleUser user;
    TextView username;
=======
    private EditProfileViewModel mViewModel;
    FragmentManager fragmentManager;
    MingleUser profile;
>>>>>>> 182f0d2 (User Profile)
    TextInputEditText nameEditText;
    TextInputEditText usernameEditText;
    TextInputEditText emailEditText;
    TextInputEditText birthDateEditText;
    TextInputEditText pronounsEditText;
    Button editProfileButton;

    public EditProfileFragment(FragmentManager fragmentManager, MingleUser user) {
        super();
        this.fragmentManager = fragmentManager;
<<<<<<< HEAD
        this.user = user;
=======
        this.profile = user;
>>>>>>> 182f0d2 (User Profile)
    }

    public static EditProfileFragment newInstance(FragmentManager fragmentManager, MingleUser user) {
        return new EditProfileFragment(fragmentManager, user);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        bindUIComponents(view);
        setupSaveButtonListener();
        return view;
<<<<<<< HEAD
    }

    private void bindUIComponents(View view) {
        nameEditText = view.findViewById(R.id.displayName);
        usernameEditText = view.findViewById(R.id.username);
        emailEditText = view.findViewById(R.id.emailAddress);
        birthDateEditText = view.findViewById(R.id.birthday);
        pronounsEditText = view.findViewById(R.id.pronouns);
        editProfileButton = view.findViewById(R.id.saveButton);
        username = view.findViewById(R.id.userNameView);

        nameEditText.setText(user.getDisplayName());
        username.setText(user.getDisplayName());
        usernameEditText.setText(user.getUsername());
        emailEditText.setText(user.getEmail());
        birthDateEditText.setText(user.getBirthDate());
        pronounsEditText.setText(user.getPronouns());
    }

    private void setupSaveButtonListener() {
        editProfileButton.setOnClickListener(v -> {
            saveProfile();
        });
    }

    private void saveProfile() {
        this.user.setDisplayName(Objects.requireNonNull(nameEditText.getText()).toString());
        this.user.setUsername(Objects.requireNonNull(usernameEditText.getText()).toString());
        this.user.setEmail(Objects.requireNonNull(emailEditText.getText()).toString());
        this.user.setBirthDate(Objects.requireNonNull(birthDateEditText.getText()).toString());
        this.user.setPronouns(Objects.requireNonNull(pronounsEditText.getText()).toString());

        UserRepositoryResolver.resolve().upsert(
                this.user,
                t -> successResult(),
                t -> Toast.makeText(getContext(),"Failed to update profile", Toast.LENGTH_SHORT).show()
        );
    }

    private void successResult() {
        Toast.makeText(getContext(),"Profile updated successfully", Toast.LENGTH_SHORT).show();
        setUpFragmentTransaction();
    }

    private void setUpFragmentTransaction() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,  ProfileFragment.newInstance(fragmentManager));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
=======
>>>>>>> 182f0d2 (User Profile)
    }

    private void bindUIComponents(View view) {
        nameEditText = view.findViewById(R.id.nameEdit);
        usernameEditText = view.findViewById(R.id.usernameEdit);
        emailEditText = view.findViewById(R.id.emailEdit);
        birthDateEditText = view.findViewById(R.id.birthdayEdit);
        pronounsEditText = view.findViewById(R.id.pronounsEdit);
        editProfileButton = view.findViewById(R.id.saveButton);
        nameEditText.setText(profile.getDisplayName());
        usernameEditText.setText(profile.getUsername());
        emailEditText.setText(profile.getEmail());
        birthDateEditText.setText(profile.getBirthDate());
        pronounsEditText.setText(profile.getPronouns());
    }

    private void setupSaveButtonListener() {
        editProfileButton.setOnClickListener(v -> {
            saveProfile();

        });
    }

    private void saveProfile() {
        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(FirestoreCollection.USER.getName()).document(userId)
                .update(
                        "displayName", Objects.requireNonNull(nameEditText.getText()).toString(),
                        "username", Objects.requireNonNull(usernameEditText.getText()).toString(),
                        "email", Objects.requireNonNull(emailEditText.getText()).toString(),
                        "birthDate", Objects.requireNonNull(birthDateEditText.getText()).toString(),
                        "pronouns", Objects.requireNonNull(pronounsEditText.getText()).toString(),
                        "balance", Objects.requireNonNull(profile.getBalance()),
                        "minglerSince", Objects.requireNonNull(profile.getMinglerSince())
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        String message = "Profile updated successfully";
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = "Failed to update profile";
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
package com.melrs.mingle.ui.profile;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melrs.mingle.MainActivity;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.repositories.user.UserRepository;
import com.melrs.mingle.data.repositories.user.UserRepositoryResolver;
import com.melrs.mingle.ui.auth.AuthenticationViewModel;
import com.melrs.mingle.ui.auth.AuthenticationViewModelFactory;

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
    }

    public static ProfileFragment newInstance(FragmentManager fragmentManager) {
        return new ProfileFragment(fragmentManager);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setUpUserProfile(view);
        setupEditProfileButtonListener(view);
        setupLogoutButtonListener(view);
        return view;
    }

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

    private void bindUIComponents(View view) {
        name = view.findViewById(R.id.userNameView);
        username = view.findViewById(R.id.nameTextView);
        email = view.findViewById(R.id.emailTextView);
        birthday = view.findViewById(R.id.birthdayTextView);
        pronouns = view.findViewById(R.id.pronounsTextView);
        minglerSince = view.findViewById(R.id.minglerSinceTextView);

        name.setText(profile.getDisplayName());
        username.setText(profile.getUsername());
        email.setText(profile.getEmail());
        birthday.setText(profile.getBirthDate());
        pronouns.setText(profile.getPronouns());
        minglerSince.setText(profile.getMinglerSince());
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

    private void setupLogoutButtonListener(View view) {
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Bye =(", Toast.LENGTH_SHORT).show();
            getAuthenticationViewModel().signOut();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
    }

    private @NonNull AuthenticationViewModel getAuthenticationViewModel() {
        return new ViewModelProvider(this, new AuthenticationViewModelFactory())
                .get(AuthenticationViewModel.class);
    }

}
package com.melrs.mingle.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD
import com.melrs.mingle.HomeActivity;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.repositories.user.UserRepositoryResolver;
=======
import com.google.firebase.firestore.FirebaseFirestore;
import com.melrs.mingle.HomeActivity;
import com.melrs.mingle.R;
import com.melrs.mingle.data.model.MingleUser;
>>>>>>> 182f0d2 (User Profile)
import com.melrs.mingle.databinding.ActivitySignUpBinding;
import com.melrs.mingle.utils.FirestoreCollection;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private AuthenticationViewModel authenticationViewModel;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignUpBinding binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        authenticationViewModel = getAuthenticationViewModel();
        bindUIComponents(binding);
        validateUserInput();
        handleSignUpResult();
        setupListeners();
    }

    private @NonNull AuthenticationViewModel getAuthenticationViewModel() {
        return new ViewModelProvider(this, new AuthenticationViewModelFactory())
                .get(AuthenticationViewModel.class);
    }

    private void bindUIComponents(ActivitySignUpBinding binding) {
        usernameEditText = binding.username;
        emailEditText = binding.emailAddress;
        passwordEditText = binding.password;
        confirmPasswordEditText = binding.confirmPassword;
        signUpButton = binding.signUp;
    }

    private void validateUserInput() {
        AuthenticationFormValidator.getInstance().getAuthenticationFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getEmailError() != null) {
                emailEditText.setError(getString(loginFormState.getEmailError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
            if (loginFormState.getConfirmPasswordError() != null) {
                confirmPasswordEditText.setError(getString(loginFormState.getConfirmPasswordError()));
            }
            signUpButton.setEnabled(loginFormState.isDataValid());
        });
    }

    private void handleSignUpResult() {
        authenticationViewModel.getAuthenticationResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            if (loginResult.getError() != null) {
                showSignUpFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                saveUserInfo(loginResult.getSuccess());
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);
        });
    }

    private void updateUiWithUser(AuthenticatedUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(HomeActivity.class);
    }

    private void showSignUpFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void setupListeners() {
        TextWatcher afterTextChangedListener = getSignUpDataTextWatcher();
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        emailEditText.addTextChangedListener(afterTextChangedListener);
        confirmPasswordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        signUpButton.setOnClickListener(v -> doSignUp());
    }

    private void doSignUp() {
        authenticationViewModel.signUp(
                usernameEditText.getText().toString(),
                emailEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
        saveUserInfo();

    }

    private void saveUserInfo() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        MingleUser user = MingleUser.empty();
        user.setUsername(usernameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setDisplayName(usernameEditText.getText().toString());
        assert user.getEmail() != null;
        db.collection(FirestoreCollection.USER.getName())
                .document(user.getEmail())
                .set(user)
                .addOnCompleteListener(task -> {
                    Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Failed to add user", Toast.LENGTH_SHORT).show();
                });
    }

    private void saveUserInfo(AuthenticatedUserView model) {
        UserRepositoryResolver
            .resolve()
            .upsert(
                MingleUser.createNew(model.getUserId(), model.getDisplayName(), model.getEmail()),
                t -> Toast.makeText(getApplicationContext(), "User added successfully", Toast.LENGTH_SHORT).show(),
                e -> Toast.makeText(getApplicationContext(), "Failed to add user", Toast.LENGTH_SHORT).show()
            );
    }

    private @NonNull TextWatcher getSignUpDataTextWatcher() {
        return new AuthenticationTextWatcher(
                authenticationViewModel,
                emailEditText,
                passwordEditText,
                usernameEditText,
                confirmPasswordEditText
        );
    }

    private void startActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
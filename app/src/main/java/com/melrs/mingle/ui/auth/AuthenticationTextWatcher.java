package com.melrs.mingle.ui.auth;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class AuthenticationTextWatcher implements TextWatcher {

    private final AuthenticationViewModel authenticationViewModel;
    private final EditText emailAddress;
    private final EditText password;
    private final @Nullable EditText username;
    private final @Nullable EditText confirmPassword;

    public AuthenticationTextWatcher(
            AuthenticationViewModel authenticationViewModel,
            EditText emailAdressEditText,
            EditText passwordEditText
    ) {
        this.authenticationViewModel = authenticationViewModel;
        this.emailAddress = emailAdressEditText;
        this.password = passwordEditText;
        this.username = null;
        this.confirmPassword = null;
    }

    public AuthenticationTextWatcher(
            AuthenticationViewModel authenticationViewModel,
            EditText emailAdressEditText,
            EditText passwordEditText,
            EditText usernameEditText,
            EditText confirmPasswordEditText
    ) {
        this.authenticationViewModel = authenticationViewModel;
        this.emailAddress = emailAdressEditText;
        this.password = passwordEditText;
        this.username = usernameEditText;
        this.confirmPassword = confirmPasswordEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        AuthenticationFormValidator.getInstance()
            .authDataChanged(
                emailAddress.getText().toString(),
                password.getText().toString(),
                confirmPassword == null ? null : confirmPassword.getText().toString(),
                username == null ? null : username.getText().toString()
            );
    }
}
package com.melrs.mingle.ui.auth;

import android.util.Patterns;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.melrs.mingle.R;

public class AuthenticationFormValidator extends ViewModel {

    private final MutableLiveData<AuthenticationFormState> authenticationFormState = new MutableLiveData<>();
    @Nullable
    static AuthenticationFormValidator instance = null;

    public static AuthenticationFormValidator getInstance() {
        return instance == null ? instance = new AuthenticationFormValidator() : instance;
    }

    public MutableLiveData<AuthenticationFormState> getAuthenticationFormState() {
        return authenticationFormState;
    }

    public void authDataChanged(
            String emailAddress,
            String password,
            @Nullable String confirmPassword,
            @Nullable String username
    ) {
        if (isEmailInvalid(emailAddress)) {
            authenticationFormState.setValue(new AuthenticationFormState(null, null, null, R.string.invalid_username));
            return;
        }

        if (isPasswordInvalid(password)) {
            authenticationFormState.setValue(new AuthenticationFormState(null, R.string.invalid_password, null ,null));
            return;
        }

        if (username != null && isUsernameInvalid(username)) {
            authenticationFormState.setValue(new AuthenticationFormState(R.string.invalid_username, null, null, null));
            return;
        }

        if (confirmPassword != null && !password.equals(confirmPassword)) {
            authenticationFormState.setValue(new AuthenticationFormState(null, null, R.string.passwords_not_match, null));
            return;
        }

        authenticationFormState.setValue(new AuthenticationFormState(true));
    }

    private boolean isUsernameInvalid(String username) {
        return username == null || username.trim().length() <= 5;

    }

    private boolean isEmailInvalid(String email_address) {
        return email_address.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email_address).matches();
    }

    private boolean isPasswordInvalid(String password) {
        return password == null || password.trim().length() <= 5;
    }
}
package com.melrs.mingle.ui.auth;

import androidx.annotation.Nullable;

public class AuthenticationFormState {
    @Nullable
    private final Integer usernameError;
    @Nullable
    private final Integer passwordError;
    @Nullable
    private final Integer confirmPasswordError;
    @Nullable
    private final Integer emailError;

    private final boolean isDataValid;

    AuthenticationFormState(@Nullable Integer emailError, @Nullable Integer passwordError) {
        this.usernameError = null;
        this.passwordError = passwordError;
        this.isDataValid = false;
        this.confirmPasswordError = null;
        this.emailError = emailError;
    }

    AuthenticationFormState(
            @Nullable Integer usernameError,
            @Nullable Integer passwordError,
            @Nullable Integer confirmPasswordError,
            @Nullable Integer emailError
    ) {
        this.confirmPasswordError = confirmPasswordError;
        this.emailError = emailError;
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    AuthenticationFormState(boolean isDataValid) {
        this.isDataValid = isDataValid;
        this.usernameError = null;
        this.passwordError = null;
        this.confirmPasswordError = null;
        this.emailError = null;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getConfirmPasswordError() {
        return confirmPasswordError;
    }

    @Nullable
    Integer getEmailError() {
        return emailError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
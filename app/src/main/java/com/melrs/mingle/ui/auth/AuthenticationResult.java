package  com.melrs.mingle.ui.auth;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class AuthenticationResult {
    @Nullable
    private AuthenticatedUserView success;
    @Nullable
    private String error;

    AuthenticationResult(@Nullable String error) {
        this.error = error;
    }

    AuthenticationResult(@Nullable AuthenticatedUserView success) {
        this.success = success;
    }

    AuthenticationResult() {
        this.success = null;
        this.error = null;
    }

    @Nullable
    AuthenticatedUserView getSuccess() {
        return success;
    }

    @Nullable
    String getError() {
        return error;
    }
}
package  com.melrs.mingle.ui.auth;

class AuthenticatedUserView {
    private final String displayName;
    private final String email;
    private final String userId;

    AuthenticatedUserView(
            String displayName,
            String email,
            String userId
    ) {
        this.displayName = displayName;
        this.email = email;
        this.userId = userId;
    }

    String getDisplayName() {
        return displayName;
    }

    String getEmail() {
        return email;
    }

    String getUserId() {
        return userId;
    }
}
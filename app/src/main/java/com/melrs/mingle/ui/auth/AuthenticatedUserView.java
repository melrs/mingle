package  com.melrs.mingle.ui.auth;

class AuthenticatedUserView {
    private final String displayName;

    AuthenticatedUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
package com.melrs.mingle.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class AuthenticationViewModel extends ViewModel {

    private final MutableLiveData<AuthenticationResult> authenticationResult = new MutableLiveData<>();
    FirebaseAuth auth;

    public AuthenticationViewModel() {
        auth = FirebaseAuth.getInstance();
    }

    LiveData<AuthenticationResult> getAuthenticationResult() {
        return authenticationResult;
    }

    public void signIn(String emailAddress, String password) {
        authenticationResult.setValue(new AuthenticationResult());
        auth.signInWithEmailAndPassword(emailAddress, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    authenticationResult.setValue(new AuthenticationResult(new AuthenticatedUserView(emailAddress)));
                } else {
                    authenticationResult.setValue(new AuthenticationResult(Objects.requireNonNull(task.getException()).getMessage()));
                }
            });
    }

    public void signUp(String username, String emailAddress, String password) {
        authenticationResult.setValue(new AuthenticationResult());
        auth.createUserWithEmailAndPassword(emailAddress, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    authenticationResult.setValue(new AuthenticationResult(new AuthenticatedUserView(username)));
                } else {
                    authenticationResult.setValue(new AuthenticationResult(Objects.requireNonNull(task.getException()).getMessage()));
                }
            });
    }

    public void signOut() {
        auth.signOut();
        authenticationResult.setValue(new AuthenticationResult());
    }

    public boolean isUserAuthenticated() {
        return auth.getCurrentUser() != null;
    }

}
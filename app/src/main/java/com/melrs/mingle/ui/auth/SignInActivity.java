package  com.melrs.mingle.ui.auth;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.melrs.mingle.HomeActivity;
import com.melrs.mingle.R;
import com.melrs.mingle.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private AuthenticationViewModel authenticationViewModel;
    private EditText emailAddressEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignInBinding binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authenticationViewModel = getAuthenticationViewModel();
        bindUIComponents(binding);
        validateUserInput();
        handleLoginResult();
        setupListeners();
    }

    private void bindUIComponents(ActivitySignInBinding binding) {
        emailAddressEditText = binding.emailAddress;
        passwordEditText = binding.password;
        loginButton = binding.login;
        loadingProgressBar = binding.loading;
        createAccountButton = binding.createAccount;
    }

    private void validateUserInput() {
        AuthenticationFormValidator.getInstance().getAuthenticationFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }

            if (loginFormState.getEmailError() != null) {
                emailAddressEditText.setError(getString(loginFormState.getEmailError()));
            }

            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }

            if(loginFormState.isDataValid()){
                createAccountButton.setBackgroundColor(ContextCompat.getColor(this, R.color.primary));
                createAccountButton.setEnabled(true);
                loginButton.setEnabled(true);
            }
        });
    }

    private void handleLoginResult() {
        authenticationViewModel.getAuthenticationResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }

            loadingProgressBar.setVisibility(View.GONE);

            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            } else if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }

            setResult(Activity.RESULT_OK);
        });
    }

    private void setupListeners() {
        TextWatcher afterTextChangedListener = getLoginDataTextWatcher();
        emailAddressEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        loginButton.setOnClickListener(v -> doSignIn());
        createAccountButton.setOnClickListener(v -> startActivity(SignUpActivity.class));
    }

    private void doSignIn() {
        authenticationViewModel.signIn(
                emailAddressEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    private @NonNull TextWatcher getLoginDataTextWatcher() {
        return new AuthenticationTextWatcher(
                authenticationViewModel,
                emailAddressEditText,
                passwordEditText
        );
    }

    private @NonNull AuthenticationViewModel getAuthenticationViewModel() {
        return new ViewModelProvider(this, new AuthenticationViewModelFactory())
                .get(AuthenticationViewModel.class);
    }

    private void updateUiWithUser(AuthenticatedUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(HomeActivity.class);
    }

    private void startActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
package  com.melrs.mingle.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.melrs.mingle.HomeActivity;
import com.melrs.mingle.R;
import com.melrs.mingle.databinding.ActivitySignInBinding;

import java.util.Objects;

public class LogInActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccountButton;
    private ProgressBar loadingProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignInBinding binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = getLoginViewModel();
        if (Objects.requireNonNull(loginViewModel.getLoginResult().getValue()).getSuccess() != null) {
            startActivity(HomeActivity.class);
        }

        bindUIComponents(binding);
        validateUserInput();
        handleLoginResult();
        setupListeners();
    }

    private void bindUIComponents(ActivitySignInBinding binding) {
        usernameEditText = binding.username;
        passwordEditText = binding.password;
        loginButton = binding.login;
        loadingProgressBar = binding.loading;
        createAccountButton = binding.createAccount;
    }

    private void validateUserInput() {
        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            createAccountButton.setEnabled(loginFormState.isDataValid());
            createAccountButton.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_variant));
        });
    }

    private void handleLoginResult() {
        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError());
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }
            loadingProgressBar.setVisibility(View.GONE);
            setResult(Activity.RESULT_OK);
            finish();
        });
    }

    private void setupListeners() {
        TextWatcher afterTextChangedListener = getTextChangedListener();
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                doSignIn();
            }
            return false;
        });

        loginButton.setOnClickListener(v -> doSignIn());

        createAccountButton.setOnClickListener(v -> startActivity(LogInActivity.class));
    }

    private void doSignIn() {
        loginViewModel.login(
                usernameEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    private @NonNull TextWatcher getTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()
                );
            }
        };
    }

    private @NonNull LoginViewModel getLoginViewModel() {
        return new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        startActivity(HomeActivity.class);
    }

    private void startActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
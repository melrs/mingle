package com.melrs.mingle;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.melrs.mingle.data.model.LoggedInUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    private final LoggedInUser user;
    private final UserBalance userBalance;

    public HomeActivity() {
        this.user = new LoggedInUser("1", "John Doe");
        this.userBalance = UserBalance.create(1, "10000.86", "USD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView text_username= findViewById(R.id.userNameView);
        text_username.setText(this.user.getDisplayName());

        TextView text_balance_amount = findViewById(R.id.userBalance);
        String[] balance = this.userBalance.getBalance().getNumber().toString().split("\\.");

        text_balance_amount.setText(balance[0]);

        TextView text_balance_currency = findViewById(R.id.rs);
        text_balance_currency.setText(this.userBalance.getBalance().getCurrency().getCurrencyCode());

        ImageView user_profile_image = findViewById(R.id.imageView);
        user_profile_image.setImageResource(R.drawable.mingle_icon);

        TextView text_balance_cents = findViewById(R.id.cents);
        text_balance_cents.setText(String.format(",%s", balance[1]));

        startActivity(new Intent(this, MingleListActivity.class));

    }
}
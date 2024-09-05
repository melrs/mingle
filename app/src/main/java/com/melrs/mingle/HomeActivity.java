package com.melrs.mingle;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepositoryResolver;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.databinding.ActivityHomeBinding;
import com.melrs.mingle.list.MingleRecyclerViewAdapter;
import com.melrs.mingle.utils.BottomNavHandler;


public class HomeActivity extends AppCompatActivity {

    private final MingleUser user;
    private final UserBalance userBalance;

    public HomeActivity() {
        this.user = new MingleUser(1, "m Doe");
        this.userBalance = UserBalance.create(1, "100.86", "USD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpNavMenu();
        setUpHeader();
        setUpMingleList();

    }

    private void setUpNavMenu() {
        BottomNavHandler.getInstance(this).setupBottomNav(findViewById(R.id.bottomNavigationView));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.nav_profile) {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.nav_camera) {
            Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpHeader() {
        TextView text_username= findViewById(R.id.userNameView);
        text_username.setText(this.user.getDisplayName());

        TextView text_balance_amount = findViewById(R.id.userBalance);
        String[] balance = this.userBalance.getBalance().getNumber().toString().split("\\.");

        text_balance_amount.setText(balance[0]);

        TextView text_balance_currency = findViewById(R.id.rs);
        text_balance_currency.setText(this.userBalance.getBalance().getCurrency().getCurrencyCode());

        ImageView user_profile_image = findViewById(R.id.imageView);
        user_profile_image.setImageResource(R.drawable.ic_user_icon);

        TextView text_balance_cents = findViewById(R.id.cents);
        text_balance_cents.setText(String.format(",%s", balance[1]));
    }

    private void setUpMingleList() {
        RecyclerView recyclerView = findViewById(R.id.mingle_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mockItems();
        }

        MingleRecyclerViewAdapter adapter = new MingleRecyclerViewAdapter(
                MingleItemRepositoryResolver.resolve().getUserMingleItems(this.user.getUserId())
        );
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void mockItems() {
        MingleItem item = MingleItem.create(
                1,
                1,
                2,
                "25.00",
                "BRL",
                "2021-09-01T00:00:00",
                "Just a mingle",
                "MI",
                "PA",
                "2021-09-01T08:00:00"
        );
        MingleItem item2 = MingleItem.create(
                2,
                1,
                2,
                "25.00",
                "BRL",
                "2021-09-01T00:00:00",
                "Other a mingle",
                "MO",
                "CA",
                "2021-09-01T08:00:00"
        );
        MingleItemRepositoryResolver.resolve().saveMingleItem(item);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item2);
        MingleItemRepositoryResolver.resolve().saveMingleItem(item2);

    }
}
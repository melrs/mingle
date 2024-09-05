package com.melrs.mingle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.databinding.ActivityHomeBinding;
import com.melrs.mingle.ui.feed.FeedFragment;
import com.melrs.mingle.ui.profile.Profile;


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
        setUpFragment(new FeedFragment(this.user, this.userBalance));
        setUpNavMenu();
    }

    private void setUpFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
    }

    private void setUpNavMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            Fragment selectedFragment = null;

            if (id == R.id.nav_home) {
                selectedFragment = new FeedFragment(this.user, this.userBalance);
            }

            if (id == R.id.nav_profile) {
                selectedFragment = new Profile();
            }

            if (id == R.id.nav_camera) {
                selectedFragment = new Profile();
            }

            if (selectedFragment != null) {
                setUpFragment(selectedFragment);
                return true;
            }

            return false;
        });
    }

}
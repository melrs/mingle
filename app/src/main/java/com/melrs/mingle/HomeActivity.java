package com.melrs.mingle;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.databinding.ActivityHomeBinding;
import com.melrs.mingle.ui.feed.FeedFragment;
import com.melrs.mingle.ui.mingleitem.AddMingleItemActivity;
import com.melrs.mingle.ui.profile.ProfileFragment;


public class HomeActivity extends AppCompatActivity {

    private final MingleUser user;
    private final UserBalance userBalance;

    public HomeActivity() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        this.user = new MingleUser(user.getUid(), user.getEmail());
        this.userBalance = UserBalance.create(user.getUid(), "100.86", "USD");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpFragment(new FeedFragment(this.user, this.userBalance, getSupportFragmentManager()));
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

            if (id == R.id.nav_home) {
                setUpFragment(new FeedFragment(this.user, this.userBalance, getSupportFragmentManager()));
                return true;
            }

            if (id == R.id.nav_profile) {
                setUpFragment(ProfileFragment.newInstance(getSupportFragmentManager()));
                return true;

            }

            if (id == R.id.nav_camera) {
                startActivity(new Intent(this, AddMingleItemActivity.class));
                return true;
            }

            return false;
        });
    }

}
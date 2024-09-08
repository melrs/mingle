package com.melrs.mingle;

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
<<<<<<< HEAD
import com.melrs.mingle.ui.mingleitem.AddManualMingleItemFragment;
=======
>>>>>>> 182f0d2 (User Profile)
import com.melrs.mingle.ui.profile.ProfileFragment;


public class HomeActivity extends AppCompatActivity {

    private final MingleUser user;
    private final UserBalance userBalance;

    public HomeActivity() {
<<<<<<< HEAD
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        this.user = new MingleUser(user.getUid(), user.getEmail());
        this.userBalance = UserBalance.create(user.getUid(), "100.86", "USD");
=======
        this.user = new MingleUser("1", "m Doe");
        this.userBalance = UserBalance.create(1, "100.86", "USD");
>>>>>>> 182f0d2 (User Profile)
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
            Fragment selectedFragment = null;

            if (id == R.id.nav_home) {
                selectedFragment = new FeedFragment(this.user, this.userBalance, getSupportFragmentManager());
            }

            if (id == R.id.nav_profile) {
                selectedFragment = ProfileFragment.newInstance(getSupportFragmentManager());
            }

            if (id == R.id.nav_camera) {
<<<<<<< HEAD
                selectedFragment = new AddManualMingleItemFragment();
=======
                selectedFragment = new ProfileFragment(getSupportFragmentManager());
>>>>>>> 182f0d2 (User Profile)
            }

            if (selectedFragment != null) {
                setUpFragment(selectedFragment);
                return true;
            }

            return false;
        });
    }

}
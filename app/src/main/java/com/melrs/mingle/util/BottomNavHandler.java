package com.melrs.mingle.util;

import android.content.Context;
import android.content.Intent;
import com.melrs.mingle.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.melrs.mingle.HomeActivity;


public class BottomNavHandler {

    private final Context context;

    private BottomNavHandler(Context context) {
        this.context = context;
    }

    public static BottomNavHandler getInstance(Context context) {
        return new BottomNavHandler(context);
    }

    public void setupBottomNav( BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                starActivity(HomeActivity.class);
                return true;
            }

            if (id == R.id.nav_profile) {
                starActivity(HomeActivity.class);
                return true;
            }

            if (id == R.id.nav_camera) {
                starActivity(HomeActivity.class);
                return true;
            }

            return false;
        });
    }

    private void starActivity(Class<?> activity) {
        if (!this.context.getClass().equals(activity)) {
            Intent intent = new Intent(this.context, activity);
            this.context.startActivity(intent);
        }
    }
}
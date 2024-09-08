package com.melrs.mingle.ui.mingleitem.invoice;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {

    private final Activity activity;

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_IMAGE_PICK = 2;

    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public void checkCameraPermission() {
        if (hasNoPermission(Manifest.permission.CAMERA) || hasNoPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestCameraPermission();
        }
    }

    public static boolean hasPermissionToProcessImage(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }


    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CAPTURE);
    }

    public void checkStoragePermission() {
        if (hasNoPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE_PICK);
    }


    private boolean hasNoPermission(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED;
    }

}
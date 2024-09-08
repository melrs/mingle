package com.melrs.mingle;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.melrs.mingle.data.model.MingleUser;
import com.melrs.mingle.data.model.UserBalance;
import com.melrs.mingle.databinding.ActivityHomeBinding;
import com.melrs.mingle.ui.feed.FeedFragment;
import com.melrs.mingle.ui.mingleitem.invoice.InvoiceProcessor;
import com.melrs.mingle.ui.profile.ProfileFragment;
import com.melrs.mingle.ui.mingleitem.invoice.PermissionManager;

import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private final MingleUser user;
    private final UserBalance userBalance;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private static final String IMAGE_TITLE = "new_invoice";
    private static final String IMAGE_DESCRIPTION = "from_camera";
    Uri selectedImage;

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

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                processInvoice();
            } else {
                Toast.makeText(this, "Failed to take a photo", Toast.LENGTH_SHORT).show();
            }
        });

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                selectedImage = result.getData().getData();
                processInvoice();
            } else {
                Toast.makeText(this, "Failed to upload a photo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Pair<Runnable, String> result = getResult(requestCode);

        if (PermissionManager.hasPermissionToProcessImage(grantResults)){
            result.first.run();
            return;
        }

        Toast.makeText(this, result.second, Toast.LENGTH_SHORT).show();
    }

    private Pair<Runnable, String> getResult(int requestCode) {
        switch (requestCode) {
            case PermissionManager.REQUEST_IMAGE_PICK:
                return new Pair<>(this::setupGalleryLauncher, "Storage permission is required to upload a photo");
            case PermissionManager.REQUEST_IMAGE_CAPTURE:
                return new Pair<>(this::setupCameraLauncher, "Camera permission is required to take a photo");
            default:
                throw new IllegalArgumentException("Invalid request code");
        }
    }

    private void processInvoice() {
        InvoiceProcessor.processInvoice(this, selectedImage, new InvoiceProcessor.OnInvoiceProcessedListener() {
            @Override
            public void onSuccess(List<String> mingleItems) {
                for (String item : mingleItems) {
                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getBaseContext(), "Failed to process invoice", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupGalleryLauncher() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }

    private void setupCameraLauncher() {
        try {
            cameraLauncher.launch(createTakePictureIntent());
        } catch (Exception e) {
            Log.e("CameraLauncher", "Error creating image URI", e);
            Toast.makeText(this, "Error creating image URI", Toast.LENGTH_SHORT).show();
        }
    }

    private @NonNull Intent createTakePictureIntent() {
        selectedImage = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, createImageContentValues());
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
        return takePictureIntent;
    }

    private @NonNull ContentValues createImageContentValues() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, IMAGE_TITLE);
        values.put(MediaStore.Images.Media.DESCRIPTION, IMAGE_DESCRIPTION);
        return values;
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
                new InvoiceModalFragment(this).show(getSupportFragmentManager(), "ModalDialog");
                return true;
            }

            return false;
        });
    }

}
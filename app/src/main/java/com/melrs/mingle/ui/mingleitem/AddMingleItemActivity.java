package com.melrs.mingle.ui.mingleitem;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.melrs.mingle.R;
import com.melrs.mingle.ui.mingleitem.invoice.InvoiceProcessor;

import java.util.List;

public class AddMingleItemActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final String IMAGE_TITLE = "new_invoice";
    private static final String IMAGE_DESCRIPTION = "from_camera";

    Button takePhotoButton;
    Button uploadPhotoButton;

    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_mingle_item);
        bindUIComponents();
        setUpListeners();

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

    private void bindUIComponents() {
        takePhotoButton = findViewById(R.id.takePhotoButton);
        uploadPhotoButton = findViewById(R.id.uploadPhotoButton);
    }

    private void setUpListeners() {
        takePhotoButton.setOnClickListener(v -> {
            if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && checkPermission(Manifest.permission.CAMERA)) {
                setupCameraLauncher();
            } else {
                requestCameraPermission();
            }
        });

        uploadPhotoButton.setOnClickListener(v -> {
            if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                setupGalleryLauncher();
            } else {
                requestStoragePermission();
            }
        });
    }

    private boolean checkPermission(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void setUpFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        /*setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);*/
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
            REQUEST_IMAGE_CAPTURE
        );
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE_PICK);
    }

    private void setupCameraLauncher() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, IMAGE_TITLE);
        values.put(MediaStore.Images.Media.DESCRIPTION, IMAGE_DESCRIPTION);

        try {
            selectedImage = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
            cameraLauncher.launch(takePictureIntent);
        } catch (Exception e) {
            Log.e("CameraLauncher", "Error creating image URI", e);
            Toast.makeText(this, "Error creating image URI", Toast.LENGTH_SHORT).show();
        }
    }


    private void setupGalleryLauncher() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }

    private void processInvoice() {
        InvoiceProcessor.processInvoice(this, selectedImage, new InvoiceProcessor.OnInvoiceProcessedListener() {
            @Override
            public void onSuccess(List<String> mingleItems) {
                for (String item : mingleItems) {
                    Toast.makeText(AddMingleItemActivity.this, item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(AddMingleItemActivity.this, "Failed to process invoice", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                handleCameraPermission(grantResults);
                break;
            case REQUEST_IMAGE_PICK:
                handleStoragePermission(grantResults);
                break;
        }
    }

    private void handleStoragePermission(int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupGalleryLauncher();
        } else {
            Toast.makeText(this, "Storage permission is required to upload a photo", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleCameraPermission(int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupCameraLauncher();
        } else {
            Toast.makeText(this, "Camera permission is required to take a photo", Toast.LENGTH_SHORT).show();
        }
    }

}
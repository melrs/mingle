package com.melrs.mingle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.melrs.mingle.ui.mingleitem.invoice.PermissionManager;

public class InvoiceModalFragment extends DialogFragment {

    Button takePhotoButton;
    Button uploadPhotoButton;
    LinearLayout cameraLayout;
    LinearLayout galleryLayout;
    PermissionManager permissionManager;

    //construct
    public InvoiceModalFragment(Activity activity) {
        permissionManager = new PermissionManager(activity);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_invoice_modal, null);

        bindUIComponents(view);
        setOnClickListener();

        builder.setView(view);
        return builder.create();
    }

    private void bindUIComponents(View view) {
        takePhotoButton = view.findViewById(R.id.cameraButton);
        uploadPhotoButton = view.findViewById(R.id.galleryButton);
        cameraLayout = view.findViewById(R.id.cameraLayout);
        galleryLayout = view.findViewById(R.id.galleryLayout);
    }

    private void handleOrRequestStoragePermission() {
        permissionManager.checkStoragePermission();
    }

    private void setOnClickListener() {
        takePhotoButton.setOnClickListener(v -> handleOrRequestCameraPermission());
        uploadPhotoButton.setOnClickListener(v -> handleOrRequestStoragePermission());
        cameraLayout.setOnClickListener(v -> handleOrRequestCameraPermission());
        galleryLayout.setOnClickListener(v -> handleOrRequestStoragePermission());
    }

    private void handleOrRequestCameraPermission() {
        permissionManager.checkCameraPermission();
    }

}
package com.melrs.mingle.ui.mingleitem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.melrs.mingle.R;
import com.melrs.mingle.data.MingleStatus;
import com.melrs.mingle.data.MingleType;
import com.melrs.mingle.data.model.MingleItem;
import com.melrs.mingle.data.model.MingleItemBuilder;
import com.melrs.mingle.data.repositories.mingleItem.MingleItemRepositoryResolver;

import java.util.Objects;

public class AddManualMingleItemFragment extends Fragment {

    Spinner typeSpinner;
    TextInputEditText emailEditText;
    TextInputEditText amountEditText;
    TextInputEditText descriptionEditText;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_manual_mingle_item, container, false);
        bindUIComponents(view);
        setupSaveButtonListener();
        return view;
    }

    private void bindUIComponents(View view) {
        typeSpinner = view.findViewById(R.id.typeSpinner);
        typeSpinner.setAdapter(getStringArrayAdapter(view));
        emailEditText = view.findViewById(R.id.friendsEmail);
        amountEditText = view.findViewById(R.id.amount);
        descriptionEditText = view.findViewById(R.id.description);
        saveButton = view.findViewById(R.id.saveButton);
    }

    private @NonNull ArrayAdapter<String> getStringArrayAdapter(View view) {
        return new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                MingleType.cases()
        );
    }

    private void setupSaveButtonListener() {
        saveButton.setOnClickListener(this::saveMingleItem);
    }

    private void saveMingleItem(View v) {
        MingleItem mingleItem = MingleItemBuilder.create()
            .friend(Objects.requireNonNull(emailEditText.getText()).toString())
            .status(MingleStatus.OP)
            .user(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
            .type(MingleType.valueOf(typeSpinner.getSelectedItem().toString()))
            .amount(Objects.requireNonNull(amountEditText.getText()).toString())
            .description(Objects.requireNonNull(descriptionEditText.getText()).toString())
            .build();

        MingleItemRepositoryResolver.resolve(v.getContext()).saveMingleItem(mingleItem);
        Toast.makeText(v.getContext(), "Mingle item saved", Toast.LENGTH_SHORT).show();
    }

}
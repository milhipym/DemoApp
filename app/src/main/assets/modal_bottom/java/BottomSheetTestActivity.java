package com.db.demoapp.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_test);

        Button btnTest = findViewById(R.id.btnTestBottomSheet);
        btnTest.setOnClickListener(v -> showCustomBottomSheet());
    }

    private void showCustomBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.modal_custom_bottom_sheet, null);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        btnCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        btnOk.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}

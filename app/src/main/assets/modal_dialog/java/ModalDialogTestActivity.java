package com.db.demoapp.ui.modal.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

public class ModalDialogTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_dialog_test);

        Button btnTest = findViewById(R.id.btnTestDialog);
        btnTest.setOnClickListener(v -> showCustomDialog());

    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.modal_custom_dialog_info);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnDelete = dialog.findViewById(R.id.btnDelete);

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnDelete.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}

package com.db.demoapp.ui.modal.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

public class SnackbarTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_test);

        Button btnTest = findViewById(R.id.btnTestSnackbar);
        btnTest.setOnClickListener(v -> showCustomSnackbar());
    }

    private void showCustomSnackbar() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.modal_custom_snack_bar, null);

        TextView message = layout.findViewById(R.id.tvSnackbarMessage);
        message.setText("자산을 모두 연결했어요.");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 150);
        toast.show();
    }
}

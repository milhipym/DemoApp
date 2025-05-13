package com.db.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAnim = findViewById(R.id.btnAnimation);
        btnAnim.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.AnimationListActivity.class);
            startActivity(intent);
        });
    }
}
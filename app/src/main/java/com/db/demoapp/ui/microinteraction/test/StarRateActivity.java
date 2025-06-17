package com.db.demoapp.ui.microinteraction.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.db.demoapp.R;

public class StarRateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_rate);

        findViewById(R.id.starGroup).setOnClickListener(v ->
                Toast.makeText(this, "별점 애니메이션!", Toast.LENGTH_SHORT).show()
        );
    }
}
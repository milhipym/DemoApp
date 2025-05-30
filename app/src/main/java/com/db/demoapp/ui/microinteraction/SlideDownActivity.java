package com.db.demoapp.ui.microinteraction;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.db.demoapp.R;

public class SlideDownActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_down);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
            imageView.startAnimation(animation);
        });

        ImageButton fab = findViewById(R.id.fabCode);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "slide_down");
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(fab, (v, insets) -> {
            int bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setTranslationY(-bottomInset);
            return insets;
        });

    }
}
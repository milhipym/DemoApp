package com.db.demoapp.ui.microinteraction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;

import java.util.Arrays;

public class MicroRocketRefreshActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView rocket;
    private float startY = 0f;
    private boolean isDragging = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microinteraction_rocket_activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        rocket = findViewById(R.id.rocket);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MicroRocketRefreshCardAdapter(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H")));

        recyclerView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = event.getRawY();
                    return false;
                case MotionEvent.ACTION_MOVE:
                    float dy = event.getRawY() - startY;
                    if (dy > 0) {
                        isDragging = true;
                        recyclerView.setTranslationY(Math.min(dy, 300));
                        rocket.setVisibility(View.VISIBLE);
                        rocket.setTranslationY(Math.min(dy - 200, 150));
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isDragging) {
                        isDragging = false;
                        rocket.animate()
                            .translationY(-300f)
                            .setDuration(600)
                            .withEndAction(() -> {
                                rocket.setVisibility(View.INVISIBLE);
                                rocket.setTranslationY(0f);
                            }).start();

                        recyclerView.animate()
                            .translationY(0)
                            .setInterpolator(new OvershootInterpolator())
                            .setDuration(500)
                            .start();

                        return true;
                    }
                    break;
            }
            return false;
        });
    }
}
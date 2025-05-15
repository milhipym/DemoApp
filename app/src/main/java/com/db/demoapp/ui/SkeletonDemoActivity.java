package com.db.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;


public class SkeletonDemoActivity extends AppCompatActivity {
    private FrameLayout container;
    private View shimmerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_demo);

        container = findViewById(R.id.skeletonDemoContainer);
        shimmerLayout = LayoutInflater.from(this).inflate(R.layout.item_skeleton, container, false);
        container.addView(shimmerLayout);

        // 2초 후 실제 데이터 표시
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                container.removeAllViews();
                TextView textView = new TextView(SkeletonDemoActivity.this);
                textView.setText("실제 데이터가 로드되었습니다!");
                textView.setTextSize(18f);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER
                );
                container.addView(textView, params);
            }
        }, 2000);

        ImageButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(v -> startActivity(new Intent(this, com.db.demoapp.code.SlideDownCodeActivity.class)));
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "skeleton"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }
}

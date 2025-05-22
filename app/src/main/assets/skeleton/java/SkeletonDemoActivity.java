package com.db.demoapp.ui.loading.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
    private ObjectAnimator alphaAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_demo);

        container = findViewById(R.id.skeletonDemoContainer);
        shimmerLayout = LayoutInflater.from(this).inflate(R.layout.item_skeleton, container, false);
        container.addView(shimmerLayout);

        // 스켈레톤에 알파 애니메이션 적용 (1.0 -> 0.5 -> 1.0 반복)
        alphaAnimator = ObjectAnimator.ofFloat(shimmerLayout, "alpha", 1f, 0.5f, 1f);
        alphaAnimator.setDuration(800);
        alphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnimator.setRepeatMode(ValueAnimator.RESTART);
        alphaAnimator.start();

        // 2초 후 실제 데이터 표시 (페이드인 효과 적용)
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // 애니메이션 종료
                if (alphaAnimator != null && alphaAnimator.isRunning()) {
                    alphaAnimator.cancel();
                }

                // 스켈레톤 페이드 아웃
                shimmerLayout.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                container.removeAllViews();

                                // 실제 데이터 페이드 인
                                TextView textView = new TextView(SkeletonDemoActivity.this);
                                textView.setText("실제 데이터가 로드되었습니다!");
                                textView.setTextSize(18f);
                                textView.setAlpha(0f); // 처음에는 투명하게 시작

                                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                                        FrameLayout.LayoutParams.WRAP_CONTENT,
                                        FrameLayout.LayoutParams.WRAP_CONTENT,
                                        Gravity.CENTER
                                );
                                container.addView(textView, params);

                                // 페이드인 애니메이션
                                textView.animate()
                                        .alpha(1f)
                                        .setDuration(500)
                                        .setListener(null);
                            }
                        });
            }
        }, 5000);

        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "skeleton"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티 종료 시 애니메이션 정리
        if (alphaAnimator != null && alphaAnimator.isRunning()) {
            alphaAnimator.cancel();
            alphaAnimator = null;
        }
    }
}

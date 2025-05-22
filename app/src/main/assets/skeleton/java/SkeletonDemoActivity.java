package com.db.demoapp.ui.loading.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.db.demoapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SkeletonDemoActivity extends AppCompatActivity {

    private ShimmerFrameLayout shimmerLayout;
    private LinearLayout dataLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_demo);

        shimmerLayout = findViewById(R.id.shimmerLayout);
        dataLayout = findViewById(R.id.dataLayout);


        // 5초 후 실제 데이터 표시 (Fade 효과)
        new Handler().postDelayed(this::showRealData, 5000);

        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "skeleton"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    private void showRealData() {
        shimmerLayout.stopShimmer();
        shimmerLayout.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction(() -> {
                    shimmerLayout.setVisibility(View.GONE);

                    // 실제 데이터 여러 개 카드로 표시
                    dataLayout.setAlpha(0f);
                    dataLayout.setVisibility(View.VISIBLE);

                    // 예시: 3개 피드 데이터
                    for (int i = 0; i < 3; i++) {
                        View postView = getLayoutInflater().inflate(R.layout.item_facebook_post, dataLayout, false);

                        ImageView imgProfile = postView.findViewById(R.id.imgProfile);
                        TextView tvName = postView.findViewById(R.id.tvName);
                        TextView tvTime = postView.findViewById(R.id.tvTime);
                        TextView tvContent = postView.findViewById(R.id.tvContent);

                        // 실제 데이터 바인딩 (여기선 예시)
                        tvName.setText("홍길동 " + (i + 1));
                        tvTime.setText((i + 1) + "시간 전");
                        tvContent.setText("이것은 스켈레톤 데이터 카드 예제입니다.\n카드 번호: " + (i + 1));
                        // imgProfile.setImageResource(...) // 실제 이미지라면 Glide/Picasso 등으로 로드

                        dataLayout.addView(postView);
                    }

                    dataLayout.animate().alpha(1f).setDuration(500).start();
                }).start();
    }
}

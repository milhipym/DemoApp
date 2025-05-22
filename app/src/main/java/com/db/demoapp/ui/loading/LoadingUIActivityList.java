package com.db.demoapp.ui.loading;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;
import com.db.demoapp.comm.item.CommonItemAdapter;
import com.db.demoapp.comm.item.VerticalSpaceItemDecoration;
import com.db.demoapp.ui.loading.test.SkeletonDemoActivity;

import java.util.Arrays;
import java.util.List;

public class LoadingUIActivityList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_ui);


        // 1. Edge-to-Edge 레이아웃 적용
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        getWindow().setStatusBarColor(Color.parseColor("#F8FAFC")); // 배경색과 맞추기
        getWindow().setNavigationBarColor(Color.TRANSPARENT); // 네비게이션바 투명

        // 2. 시스템 바 영역만큼 padding 적용 (루트 ConstraintLayout의 id가 rootLayout라고 가정)
        View rootView = findViewById(R.id.rootLayout);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sysInsets.left, sysInsets.top, sysInsets.right, sysInsets.bottom);
            return insets;
        });


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "스켈레톤 UI",
                        "콘텐츠가 로딩될 때 화면이 중지된 것처럼 보이지 않게끔 모션을 적용하여 보여주는 화면"
                )
        );

        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, SkeletonDemoActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

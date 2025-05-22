package com.db.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;
import com.db.demoapp.comm.item.CommonItemAdapter;
import com.db.demoapp.comm.item.VerticalSpaceItemDecoration;
import com.db.demoapp.ui.loading.test.InfiniteScrollDemoActivity;
import com.db.demoapp.ui.loading.test.SkeletonDemoActivity;
import com.db.demoapp.ui.microitems.SlideDownActivity;

import java.util.Arrays;
import java.util.List;

public class AnimationListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_ui);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Infinite 스크롤",
                        "스크롤이 페이지의 끝에 도달했을 때 자동으로 다음 데이터를 요청하여 받아오는 UX 기능"
                ),
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
                    intent = new Intent(this, InfiniteScrollDemoActivity.class);
                    break;
                case 1:
                    intent = new Intent(this, SkeletonDemoActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

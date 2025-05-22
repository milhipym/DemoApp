package com.db.demoapp.ui.threedmotion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.db.demoapp.ui.pagingitems.PagingAppbarTabLayoutActivity;
import com.db.demoapp.ui.pagingitems.PagingBottomActivity;
import com.db.demoapp.ui.pagingux.test.GeneralPaging;
import com.db.demoapp.ui.threedmotion.test.GeneralThreeDMotion;

import java.util.Arrays;
import java.util.List;

public class ThreeDMotionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d_motion);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "기본 3D Motion",
                        "사실적인 텍스쳐, 생동감 있는 형태로 몰입감을 더하는 방식으로 활용"
                )
        );

        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, GeneralThreeDMotion.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

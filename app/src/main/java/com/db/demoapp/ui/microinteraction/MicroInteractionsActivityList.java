package com.db.demoapp.ui.microinteraction;

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
import com.db.demoapp.ui.apishowcase.ApiShowCaseActivity;
import com.db.demoapp.ui.loading.test.InfiniteScrollDemoActivity;
import com.db.demoapp.ui.loading.test.SkeletonDemoActivity;
import com.db.demoapp.ui.microinteraction.test.ApiShowCaseExample;
import com.db.demoapp.ui.microitems.SlideDownActivity;

import java.util.Arrays;
import java.util.List;

public class MicroInteractionsActivityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitiy_micro_interaction);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Micro Interaction",
                        "사용자 액션에 대한 반응형 애니메이션"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "SHOW CASE",
                        "사용자에게 어떻게 사용하는지 알려주기!"
                )
        );

        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, SlideDownActivity.class);
                    break;
                case 1:
                    intent = new Intent(this, ApiShowCaseExample.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

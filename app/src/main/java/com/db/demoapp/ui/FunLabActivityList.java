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
import com.db.demoapp.ui.funlab.StarbuksAppbarCollapActivity;
import com.db.demoapp.ui.funlab.StarbuksBottomNaviActiviy;
import com.db.demoapp.ui.funlab.StarbuksCardViewActivty;
import com.db.demoapp.ui.funlab.StarbuksCardViewPageIndicatorActivity;

import java.util.Arrays;
import java.util.List;

public class FunLabActivityList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_lab);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "StarbuksBottomNaviActiviy",
                        "스타벅스 바텀 네비게이터"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "StarbuksAppbarCollapActivity",
                        "스타벅스 앱 바"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "StarbuksCardViewActivty",
                        "스타벅스 카드뷰"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "StarbuksCardViewPageIndicatorActivity",
                        "스타벅스 페이징"
                )
        );

        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, StarbuksBottomNaviActiviy.class);
                    break;
                case 1:
                    intent = new Intent(this, StarbuksAppbarCollapActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, StarbuksCardViewActivty.class);
                    break;
                case 3:
                    intent = new Intent(this, StarbuksCardViewPageIndicatorActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

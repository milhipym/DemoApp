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
import com.db.demoapp.ui.funlab.StarbuksBottomSheetActivity;
import com.db.demoapp.ui.funlab.StarbuksCardViewActivty;
import com.db.demoapp.ui.funlab.StarbuksCardViewPageIndicatorActivity;
import com.db.demoapp.ui.funlab.StarbuksRecyclerViewActivity;

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
                        "STEP1 스타벅스 하단바 메뉴 구성",
                        "BottomNavigation 하단에 위치 하며 주요메뉴를 빠르게 이동."
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "STEP2 스타벅스 상단 배너 및 플로팅 버튼",
                        "CollapsingToolbarLayout 뉴스, 프로필, 배너 에서 많이 사용."
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "STEP3 스타벅스 퀵 주문 메뉴",
                        "CardView 정보 덩어리를 보기 좋게 담는 박스형 UI 컴포넌트"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "STEP4 스타벅스 인디케이터",
                        "뉴스가 얼마나 있는지 알려 주는 Indicator 및 무한 루프"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "STEP5 스타벅스 회사 배너",
                        "RecyclerView 소수의 뷰만 생성하여 메모리 절약"
                )/*,
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "STEP6 스타벅스 카드/간편결제",
                        "BottomSheet 효율적인 공간 활용 및 맥락 유지를 위한 모달 팝업"
                )*/
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
                case 4:
                    intent = new Intent(this, StarbuksRecyclerViewActivity.class);
                    break;
                /*case 5:
                    intent = new Intent(this, StarbuksBottomSheetActivity.class);
                    break;*/
            }
            if (intent != null) startActivity(intent);
        });
    }
}

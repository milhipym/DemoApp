package com.db.demoapp.ui.modal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;
import com.db.demoapp.comm.item.CommonItemAdapter;
import com.db.demoapp.comm.item.VerticalSpaceItemDecoration;
import com.db.demoapp.ui.modal.test.BottomSheetExpandedTestActivity;
import com.db.demoapp.ui.modal.test.BottomSheetTestActivity;
import com.db.demoapp.ui.modal.test.ModalDialogTestActivity;
import com.db.demoapp.ui.modal.test.SnackbarTestActivity;
import java.util.Arrays;
import java.util.List;

public class ModalUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_ui);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 16dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        // 공통 데이터 생성 (첨부 이미지의 순서/설명 반영)
        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "모달 팝업",
                        "중요한 정보전달/결정 필요"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "바텀 시트",
                        "기존 화면과 관련된 내용/메뉴 제공"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "확장 바텀 시트",
                        "기본 바텀 시트 확장형 제공"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "스낵바",
                        "Action에 대한 피드백/별도 액션 제공"
                )
        );

        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, ModalDialogTestActivity.class);
                    break;
                case 1:
                    intent = new Intent(this, BottomSheetTestActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, BottomSheetExpandedTestActivity.class);
                    break;
                case 3:
                    intent = new Intent(this, SnackbarTestActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

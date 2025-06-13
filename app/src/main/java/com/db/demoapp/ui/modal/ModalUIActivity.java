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
import com.db.demoapp.ui.modal.test.PersistentBottomSheetActivity;
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
                        "Modal PopUp",
                        "중요 결정, 알림, 사용자 입력에 사용 (예: 삭제 확인)"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Modal BottomSheet",
                        "연관된 작업, 옵션 선택 등 하단에서 등장 \n(예: 파일 첨부)"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Modal BottomSheet Extend",
                        "전체 화면에 가깝게 확장되는 하단 시트 \n(예: 상세 정보 보기)"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Snackbar ",
                        "간단한 메시지/액션을 하단에 표시 \n(예: 저장됨, 실행 취소 등의 안내)"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "Persistant BottomSheet",
                        "항상 하단에 위치, 추가 정보/입력 제공 \n(예: 음악 플레이어)"
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
                case 4:
                    intent = new Intent(this, PersistentBottomSheetActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }
}

package com.db.demoapp.ui.etc;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;
import com.db.demoapp.comm.item.CommonItemAdapter;
import com.db.demoapp.comm.item.VerticalSpaceItemDecoration;
import com.db.demoapp.ui.etc.healthconnect.test.HealthConnectTestActivity;
import com.db.demoapp.ui.etc.preparedstatement.test.PrepareStatementTestActivity;

import java.util.Arrays;
import java.util.List;

public class EtcActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etc);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int spaceInPx = (int) (getResources().getDisplayMetrics().density * 8); // 8dp 간격
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(spaceInPx));

        List<CommonItemAdapter.ItemData> items = Arrays.asList(
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "구글헬스커넥트",
                        "걸음 수 측정하기 API"
                ),
                new CommonItemAdapter.ItemData(
                        R.drawable.ic_micro_interaction,
                        "앱 내 저장소 사용하기",
                        "PreparedStatement 활용"
                )
        );
        CommonItemAdapter adapter = new CommonItemAdapter(this, items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Intent intent = null;
            if (position == 0) {
                intent = new Intent(this, HealthConnectTestActivity.class);
            }else if(position == 1){
                intent = new Intent(this, PrepareStatementTestActivity.class);
            }
            if (intent != null) startActivity(intent);
        });
    }
}

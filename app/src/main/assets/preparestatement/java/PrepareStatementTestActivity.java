package com.db.demoapp.ui.etc.preparedstatement.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.db.demoapp.ui.etc.preparedstatement.sql.StorageDbHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PrepareStatementTestActivity extends AppCompatActivity {

    private TextView textViewStorageData;
    private MaterialButton btnSave, btnDelete;
    private FloatingActionButton fabCode;
    private StorageDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_statement);

        textViewStorageData = findViewById(R.id.textViewStorageData);
        btnSave = findViewById(R.id.save);
        btnDelete = findViewById(R.id.delete);
        fabCode = findViewById(R.id.fabCode);

        dbHelper = new StorageDbHelper(this);

        // 앱 시작 시 전체 데이터 표시
        updateStorageDataList();

        btnSave.setOnClickListener(v -> {
            if (isFinishing() || isDestroyed()) return;
            dbHelper.insertStorageData("샘플 데이터 " + System.currentTimeMillis());
            updateStorageDataList();
            Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnClickListener(v -> {
            if (isFinishing() || isDestroyed()) return;
            dbHelper.deleteAllStorageData();
            updateStorageDataList();
            Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
        });

        fabCode.setOnClickListener(v -> {
            if (isFinishing() || isDestroyed()) return;
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "preparestatement"); // ✅ 핵심 포인트
            startActivity(intent);
        });

    }

    // 저장된 모든 데이터를 줄바꿈으로 표시
    private void updateStorageDataList() {
        if (isFinishing() || isDestroyed()) return;
        List<String> dataList = dbHelper.getAllStorageData();
        if (dataList.isEmpty()) {
            textViewStorageData.setText("데이터가 존재하지 않습니다.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String data : dataList) {
                sb.append(data).append("\n");
            }
            textViewStorageData.setText(sb.toString().trim());
        }
    }
}

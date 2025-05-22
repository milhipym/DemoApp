package com.db.demoapp.ui.microinteraction.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.db.demoapp.R;
import com.erkutaras.showcaseview.ShowcaseManager;
import com.google.android.material.button.MaterialButton;

public class ApiShowCaseExample extends AppCompatActivity {

    private MaterialButton btnApi1, btnApi2;
    private int showcaseStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_show_case_example);

        btnApi1 = findViewById(R.id.btnApi1);
        btnApi2 = findViewById(R.id.btnApi2);

        getSharedPreferences("intro", MODE_PRIVATE)
                .edit()
                .remove("api1_showcase")
                .remove("api2_showcase")
                .apply();

        btnApi1.setOnClickListener(v ->
                Toast.makeText(this, "API 1 실행!", Toast.LENGTH_SHORT).show()
        );
        btnApi2.setOnClickListener(v ->
                Toast.makeText(this, "API 2 실행!", Toast.LENGTH_SHORT).show()
        );

        // 항상 진입 시마다 안내
        btnApi1.post(this::showShowcaseStep1);

        ImageButton fab = findViewById(R.id.fabCode);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "show_case"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    private void showShowcaseStep1() {
        showcaseStep = 1;
        ShowcaseManager showcaseManager = new ShowcaseManager.Builder()
                .context(this)
                .key("api1_showcase")
                .view(btnApi1)
                .descriptionTitle("API 1 버튼")
                .descriptionText("여기를 눌러 API 1을 실행할 수 있습니다.")
                .buttonText("다음")
                .add()
                .build();
        showcaseManager.show();
    }

    private void showShowcaseStep2() {
        showcaseStep = 2;
        ShowcaseManager showcaseManager = new ShowcaseManager.Builder()
                .context(this)
                .key("api2_showcase")
                .view(btnApi2)
                .descriptionTitle("API 2 버튼")
                .descriptionText("여기를 눌러 API 2를 실행할 수 있습니다.")
                .buttonText("완료")
                .add()
                .build();
        showcaseManager.show();
    }

    // Showcase가 닫힐 때마다 호출됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ShowcaseManager.REQUEST_CODE_SHOWCASE && resultCode == Activity.RESULT_OK) {
            if (showcaseStep == 1) {
                showShowcaseStep2();
            }
        }
    }
}

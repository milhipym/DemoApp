package com.db.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

public class ModalDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_demo);

        FrameLayout container = findViewById(R.id.modal_container);

        // LinearLayout 생성 및 속성 설정
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        // 버튼 생성
        Button btnPopup = new Button(this);
        btnPopup.setText("팝업");
        Button btnBottomSheet = new Button(this);
        btnBottomSheet.setText("바텀시트");
        Button btnSnackbar = new Button(this);
        btnSnackbar.setText("스낵바");

        // 레이아웃에 버튼 추가
        layout.addView(btnPopup);
        layout.addView(btnBottomSheet);
        layout.addView(btnSnackbar);
        container.addView(layout);

        // 팝업 버튼 클릭 리스너
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ModalDemoActivity.this)
                    .setTitle("팝업")
                    .setMessage("이것은 팝업입니다.")
                    .setPositiveButton("확인", null)
                    .show();
            }
        });

        // 바텀시트 버튼 클릭 리스너
        btnBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog = new BottomSheetDialog(ModalDemoActivity.this);
                View view = LayoutInflater.from(ModalDemoActivity.this)
                    .inflate(R.layout.modal_custom_bottom_sheet, null, false);
                dialog.setContentView(view);
                dialog.show();
            }
        });

        // 스낵바 버튼 클릭 리스너
        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(container, "이것은 스낵바입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        ImageButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(v -> startActivity(new Intent(this, com.db.demoapp.code.SlideDownCodeActivity.class)));
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "modal"); // ✅ 핵심 포인트
            startActivity(intent);
        });

    }
}

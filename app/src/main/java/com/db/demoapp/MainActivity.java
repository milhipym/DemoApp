package com.db.demoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.ui.apishowcase.ApiShowCaseActivity;
import com.db.demoapp.ui.microinteraction.MicroInteractionsActivityList;
import com.db.demoapp.ui.threedmotion.ThreeDMotionActivity;
import com.db.demoapp.ui.loading.LoadingUIActivityList;
import com.db.demoapp.ui.etc.EtcActivity;
import com.db.demoapp.ui.modal.ModalUIActivity;
import com.db.demoapp.ui.pagingux.PagingUXListActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // Android 11 이상
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            // Android 10 이하
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT); // 네비게이션바 투명

        // 모달UI
        Button btnAnim = findViewById(R.id.btnAnimation);
        btnAnim.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModalUIActivity.class);
            startActivity(intent);
        });
        //로딩UI
        Button btnAnim1 = findViewById(R.id.btnAnimation1);
        btnAnim1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoadingUIActivityList.class);
            startActivity(intent);
        });
        //페이징UX
        Button btnAnim2 = findViewById(R.id.btnAnimation2);
        btnAnim2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PagingUXListActivity.class);
            startActivity(intent);
        });
        //마이크로인터랙션
        Button btnAnim3 = findViewById(R.id.btnAnimation3);
        btnAnim3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MicroInteractionsActivityList.class);
            startActivity(intent);
        });
        //3D UI/MOTION DESIGN
        Button btnAnim4 = findViewById(R.id.btnAnimation4);
        btnAnim4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThreeDMotionActivity.class);
            startActivity(intent);
        });
        //FUN_LAB
        Button btnAnim5 = findViewById(R.id.btnAnimation5);
        btnAnim5.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.FunLabActivityList.class);
            startActivity(intent);
        });
        //API SHOWCASE
        Button btnAnim6 = findViewById(R.id.btnAnimation6);
        btnAnim6.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ApiShowCaseActivity.class);
            startActivity(intent);
        });
        //COMOPOSE(ANDROID)
        /*Button btnAnim7 = findViewById(R.id.btnAnimation7);
        btnAnim7.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Comp.class);
            startActivity(intent);
        });*/
        //ETC
        Button btnAnim8 = findViewById(R.id.btnAnimation8);
        btnAnim8.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EtcActivity.class);
            startActivity(intent);
        });

    }
}
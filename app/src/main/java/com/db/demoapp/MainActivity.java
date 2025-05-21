package com.db.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.ui.etc.EtcActivity;
import com.db.demoapp.ui.modal.ModalUIActivity;
import com.db.demoapp.ui.PagingUXListActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 모달UI
        Button btnAnim = findViewById(R.id.btnAnimation);
        btnAnim.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModalUIActivity.class);
            startActivity(intent);
        });
        //로딩UI
        Button btnAnim1 = findViewById(R.id.btnAnimation1);
        btnAnim1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.AnimationListActivity.class);
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
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.MicroInteractionsActivityList.class);
            startActivity(intent);
        });
        //3D UI/MOTION DESIGN
        Button btnAnim4 = findViewById(R.id.btnAnimation4);
        btnAnim4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.AnimationListActivity.class);
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
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.AnimationListActivity.class);
            startActivity(intent);
        });
        //COMOPOSE(ANDROID)
        Button btnAnim7 = findViewById(R.id.btnAnimation7);
        btnAnim7.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.db.demoapp.ui.AnimationListActivity.class);
            startActivity(intent);
        });
        //ETC
        Button btnAnim8 = findViewById(R.id.btnAnimation8);
        btnAnim8.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EtcActivity.class);
            startActivity(intent);
        });

    }
}
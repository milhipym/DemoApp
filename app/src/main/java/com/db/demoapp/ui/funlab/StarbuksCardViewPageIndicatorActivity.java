package com.db.demoapp.ui.funlab;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.db.demoapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StarbuksCardViewPageIndicatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starbuks_bottomenavi);
        BottomNavigationView navView = findViewById(R.id.star_bottom_navigation);

        //최초 진입 시 home 프래그먼트 수동 설정
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.start_fragment_container,
                            StarbuksCardViewPageIndicatorFragment.newInstance("home"))
                    .commit();
            navView.setSelectedItemId(R.id.starbucks_home);
        }

        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.starbucks_home) {
                selectedFragment = StarbuksCardViewPageIndicatorFragment.newInstance("home");
            } else if (itemId == R.id.starbucks_pay) {
                selectedFragment = StarbuksCardViewPageIndicatorFragment.newInstance("pay 화면입니다");
            } else if (itemId == R.id.starbucks_order) {
                selectedFragment = StarbuksCardViewPageIndicatorFragment.newInstance("order 화면입니다");
            } else if (itemId == R.id.starbucks_shop) {
                selectedFragment = StarbuksCardViewPageIndicatorFragment.newInstance("shop 화면입니다");
            } else if (itemId == R.id.starbucks_other) {
                selectedFragment = StarbuksCardViewPageIndicatorFragment.newInstance("other 화면입니다");
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.start_fragment_container, selectedFragment).commit();
            return true;
        });

        navView.setSelectedItemId(R.id.nav_home);

        ImageButton fab = findViewById(R.id.fabCode);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "funlab_startbuks_cardview");
            startActivity(intent);
        });
    }
}

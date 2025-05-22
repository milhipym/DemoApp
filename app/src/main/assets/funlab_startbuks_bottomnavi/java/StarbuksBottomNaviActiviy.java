package com.db.demoapp.ui.funlab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.db.demoapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StarbuksBottomNaviActiviy extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starbuks_bottomenavi);
        BottomNavigationView navView = findViewById(R.id.star_bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.starbucks_home) {
                selectedFragment = StarbuksBottomFragment.newInstance("home 화면입니다");
            } else if (itemId == R.id.starbucks_pay) {
                selectedFragment = StarbuksBottomFragment.newInstance("pay 화면입니다");
            } else if (itemId == R.id.starbucks_order) {
                selectedFragment = StarbuksBottomFragment.newInstance("order 화면입니다");
            } else if (itemId == R.id.starbucks_shop) {
                selectedFragment = StarbuksBottomFragment.newInstance("shop 화면입니다");
            } else if (itemId == R.id.starbucks_other) {
                selectedFragment = StarbuksBottomFragment.newInstance("other 화면입니다");
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.start_fragment_container, selectedFragment).commit();
            return true;
        });

        navView.setSelectedItemId(R.id.nav_home);
    }
}

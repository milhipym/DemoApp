package com.db.demoapp.ui.pagingitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.db.demoapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PagingBottomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging_bottomnavigation);

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = PagingBottomFragment.newInstance("home 화면입니다");
            } else if (itemId == R.id.nav_pay) {
                selectedFragment = PagingBottomFragment.newInstance("pay 화면입니다");
            } else if (itemId == R.id.nav_order) {
                selectedFragment = PagingBottomFragment.newInstance("order 화면입니다");
            } else if (itemId == R.id.nav_shop) {
                selectedFragment = PagingBottomFragment.newInstance("shop 화면입니다");
            } else if (itemId == R.id.nav_other) {
                selectedFragment = PagingBottomFragment.newInstance("other 화면입니다");
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });

        navView.setSelectedItemId(R.id.nav_home);
    }
}

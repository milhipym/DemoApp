package com.db.demoapp.ui.modal.test;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class PersistentBottomSheetActivity extends AppCompatActivity {

    private BottomSheetBehavior<View> bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persistent_bottomsheet);

        View bottomSheet = findViewById(R.id.playerBottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setPeekHeight(180);
        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // 상태 변경 시 처리
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // 드래그 중 처리
            }
        });
    }
}

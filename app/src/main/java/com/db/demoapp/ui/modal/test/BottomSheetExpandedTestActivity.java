package com.db.demoapp.ui.modal.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.db.demoapp.R;
import com.db.demoapp.ui.modal.fragment.ModalExpandedBottomSheetFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetExpandedTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_expanded_test);

        // 1. Edge-to-Edge 레이아웃 적용
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        getWindow().setStatusBarColor(Color.parseColor("#F8FAFC")); // 배경색과 맞추기
        getWindow().setNavigationBarColor(Color.TRANSPARENT); // 네비게이션바 투명

        // 2. 시스템 바 영역만큼 padding 적용 (루트 ConstraintLayout의 id가 rootLayout라고 가정)
        View rootView = findViewById(R.id.rootLayout);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(sysInsets.left, sysInsets.top, sysInsets.right, sysInsets.bottom);
            return insets;
        });

        Button btnTest = findViewById(R.id.btnTestBottomSheet);
        btnTest.setOnClickListener(v -> test());

        ImageButton fab = findViewById(R.id.fabCode);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "modal_bottom");
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(fab, (v, insets) -> {
            int bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setTranslationY(-bottomInset);
            return insets;
        });
    }

    private void test(){
        ModalExpandedBottomSheetFragment fragment = new ModalExpandedBottomSheetFragment();
        fragment.show(getSupportFragmentManager(), "ModalExpandedBottomSheetFragment");
    }

    private void showCustomBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.RoundCornerBottomSheetDialogTheme);
        View view = getLayoutInflater().inflate(R.layout.modal_expanded_bottom_sheet, null);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        btnCancel.setOnClickListener(v -> bottomSheetDialog.dismiss());
        btnOk.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);

        bottomSheetDialog.setOnShowListener(dialog -> {
            BottomSheetDialog dialogc = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = dialogc.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                int screenHeight = getResources().getDisplayMetrics().heightPixels;
                int maxHeight = (int) (screenHeight * 0.8f);

                View contentRoot = bottomSheet.findViewById(R.id.bottomSheetRoot);
                View expandSpacer = view.findViewById(R.id.expandSpacer);

                if (contentRoot != null && expandSpacer != null) {
                    // Spacer 높이 동적 조정
                    contentRoot.post(() -> {
                        int contentHeight = contentRoot.getHeight();
                        int spacerHeight = Math.max(0, maxHeight - contentHeight);

                        expandSpacer.getLayoutParams().height = spacerHeight;
                        expandSpacer.setLayoutParams(expandSpacer.getLayoutParams());

                        BottomSheetBehavior<?> behavior = BottomSheetBehavior.from(bottomSheet);
                        try {
                            behavior.getClass().getMethod("setShouldRemoveExpandedCorners", boolean.class).invoke(behavior, true);
                        } catch (Exception e) { /* 구버전은 무시 */ }

                        behavior.setFitToContents(false);
                        behavior.setPeekHeight(contentHeight); // 처음엔 컨텐츠만 보임
                        behavior.setExpandedOffset(0);         // 확장 시 맨 위까지
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    });
                }
            }
        });

        bottomSheetDialog.show();
    }
}

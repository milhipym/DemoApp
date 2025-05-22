package com.db.demoapp.ui.modal.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.db.demoapp.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ModalExpandedBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modal_expanded_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        btnCancel.setOnClickListener(v -> dismiss());
        btnOk.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog instanceof BottomSheetDialog) {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
            View bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                int screenHeight = getScreenHeight(requireContext());
                int maxHeight = (int) (screenHeight * 0.8f);
                int expandedOffset = screenHeight - maxHeight;

                View contentRoot = bottomSheet.findViewById(R.id.bottomSheetRoot);
                View expandSpacer = contentRoot != null ? contentRoot.findViewById(R.id.expandSpacer) : null;

                if (contentRoot != null && expandSpacer != null) {
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
                        behavior.setExpandedOffset(expandedOffset); // 확장 시 80%까지만
                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    });
                }
            }
        }
    }


    private int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public int getTheme() {
        return R.style.RoundCornerBottomSheetDialogTheme;
    }
}

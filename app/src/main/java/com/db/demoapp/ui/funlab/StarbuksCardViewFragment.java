package com.db.demoapp.ui.funlab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.db.demoapp.R;

import java.util.Arrays;
import java.util.List;

public class StarbuksCardViewFragment extends Fragment {

    private static final String ARG_TEXT = "label";

    public static StarbuksCardViewFragment newInstance(String text) {
        StarbuksCardViewFragment fragment = new StarbuksCardViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String label = getArguments() != null ? getArguments().getString(ARG_TEXT) : "";

        if ("home".equals(label)) {
            View view = inflater.inflate(R.layout.starbuks_home_screen_cardview, container, false);

            ViewPager2 viewPager = view.findViewById(R.id.orderViewPager);
            // 리스트 생성
            List<StarbuksCardViewAdapter.DrinkItem> drinks = Arrays.asList(
                    new StarbuksCardViewAdapter.DrinkItem(R.drawable.starbuks_home_order_iceame, "아이스 카페 아메리카노", "ICED | Tall"),
                    new StarbuksCardViewAdapter.DrinkItem(R.drawable.starbuks_home_order_iceradde, "카페 라떼", "HOT | Tall"),
                    new StarbuksCardViewAdapter.DrinkItem(R.drawable.starbuks_home_order_icenokcha, "녹차 라떼", "HOT | Grand"),
                    new StarbuksCardViewAdapter.DrinkItem(R.drawable.starbuks_home_order_caramel, "카라멜 마끼아또", "ICED | Tall"),
                    new StarbuksCardViewAdapter.DrinkItem(R.drawable.starbuks_home_order_essop, "에스프레소", "HOT | Solo")
            );

            // 어댑터 연결
            StarbuksCardViewAdapter adapter = new StarbuksCardViewAdapter(drinks);
            viewPager.setAdapter(adapter);

            viewPager.setOffscreenPageLimit(3);
            viewPager.setPageTransformer((page, position) -> {
                float offsetPx = 30f;
                page.setTranslationX(position * -offsetPx);
                float scale = 0.95f + (1 - Math.abs(position)) * 0.05f;
                page.setScaleY(scale);
            });

            return view;
        } else {
            TextView textView = new TextView(getContext());
            textView.setText(label);
            textView.setTextSize(24f);
            textView.setGravity(Gravity.CENTER);
            return textView;
        }
    }
}

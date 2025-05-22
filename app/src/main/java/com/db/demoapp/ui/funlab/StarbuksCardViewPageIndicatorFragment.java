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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarbuksCardViewPageIndicatorFragment extends Fragment {

    private static final String ARG_TEXT = "label";

    public static StarbuksCardViewPageIndicatorFragment newInstance(String text) {
        StarbuksCardViewPageIndicatorFragment fragment = new StarbuksCardViewPageIndicatorFragment();
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
            View view = inflater.inflate(R.layout.starbuks_home_screen_cardview_indicate, container, false);

            //주문 카드뷰
            ViewPager2 oRderviewPager = view.findViewById(R.id.orderViewPager);
            // 리스트 생성
            List<StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem> drinks = Arrays.asList(
                    new StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem(R.drawable.starbuks_home_order_iceame, "아이스 카페 아메리카노", "ICED | Tall"),
                    new StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem(R.drawable.starbuks_home_order_iceradde, "카페 라떼", "HOT | Tall"),
                    new StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem(R.drawable.starbuks_home_order_icenokcha, "녹차 라떼", "HOT | Grand"),
                    new StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem(R.drawable.starbuks_home_order_caramel, "카라멜 마끼아또", "ICED | Tall"),
                    new StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem(R.drawable.starbuks_home_order_essop, "에스프레소", "HOT | Solo")
            );

            // 어댑터 연결
            StarbuksCardViewPageIndicatorOrderAdapter adapter = new StarbuksCardViewPageIndicatorOrderAdapter(drinks);
            oRderviewPager.setAdapter(adapter);

            oRderviewPager.setOffscreenPageLimit(3);
            oRderviewPager.setPageTransformer((page, position) -> {
                float offsetPx = 30f;
                page.setTranslationX(position * -offsetPx);
                float scale = 0.95f + (1 - Math.abs(position)) * 0.05f;
                page.setScaleY(scale);
            });

            //이벤트 카드뷰
            ViewPager2 newsCardViewPager = view.findViewById(R.id.eventViewPager);
            List<Integer> newsImages = Arrays.asList(
                    R.drawable.starbuks_event_one, R.drawable.starbuks_event_two
            );
            List<String> pageIndicators = Arrays.asList("1/2", "2/2");
            List<Integer> loopedImages = new ArrayList<>();
            List<String> loopedIndicators = new ArrayList<>();
            loopedImages.add(newsImages.get(newsImages.size()-1));
            loopedImages.addAll(newsImages);
            loopedImages.add(newsImages.get(0));
            loopedIndicators.add(pageIndicators.get(pageIndicators.size()-1));
            loopedIndicators.addAll(pageIndicators);
            loopedIndicators.add(pageIndicators.get(0));

            StarbuksCardViewPageIndicatorAdapter newsAdapter = new StarbuksCardViewPageIndicatorAdapter(loopedImages, loopedIndicators);
            newsCardViewPager.setAdapter(newsAdapter);
            newsCardViewPager.setOffscreenPageLimit(2);
            newsCardViewPager.setPadding(32, 0, 32, 0);
            newsCardViewPager.setClipToPadding(false);
            newsCardViewPager.setClipChildren(false);
            newsCardViewPager.setPageTransformer((page, position) -> {
                float scale = 0.95f + (1 - Math.abs(position)) * 0.05f;
                page.setScaleY(scale);
            });

            //카드뷰 무한루프
            newsCardViewPager.setCurrentItem(1, false);

            newsCardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    int itemCount = newsAdapter.getItemCount();
                    if (itemCount > 1) {
                        if (position == 0) {
                            newsCardViewPager.post(() -> newsCardViewPager.setCurrentItem(itemCount - 2, false));
                        }
                        else if (position == itemCount - 1) {
                            newsCardViewPager.post(() -> newsCardViewPager.setCurrentItem(1, false));
                        }
                    }
                }
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

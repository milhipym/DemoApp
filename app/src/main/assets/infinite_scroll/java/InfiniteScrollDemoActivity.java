package com.db.demoapp.ui.loading.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;

import java.util.ArrayList;
import java.util.List;

public class InfiniteScrollDemoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> items = new ArrayList<>();
    private RecyclerView.Adapter<RecyclerView.ViewHolder> rvAdapter;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_scroll_demo);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FrameLayout container = findViewById(R.id.infinite_container);
        container.addView(recyclerView);

        // 초기 데이터
        for (int i = 1; i <= 20; i++) {
            items.add("아이템 " + i);
        }

        rvAdapter = new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @Override
            public int getItemCount() {
                return items.size();
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView tv = new TextView(parent.getContext());
                tv.setTextSize(16f);
                int px16 = (int) (16 * parent.getContext().getResources().getDisplayMetrics().density);
                int px32 = (int) (32 * parent.getContext().getResources().getDisplayMetrics().density);
                tv.setPadding(px16, px32, px16, px32);
                return new RecyclerView.ViewHolder(tv) {};
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView).setText(items.get(position));
            }
        };

        recyclerView.setAdapter(rvAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
                if (!isLoading && lm.findLastCompletelyVisibleItemPosition() == items.size() - 1) {
                    Toast.makeText(getApplicationContext(), "아이템 조회중...", Toast.LENGTH_SHORT).show();
                    isLoading = true;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int start = items.size() + 1;
                            for (int i = 0; i < 10; i++) {
                                items.add("아이템 " + (start + i));
                            }
                            rvAdapter.notifyDataSetChanged();
                            isLoading = false;
                        }
                    }, 1200);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(fab, (v, insets) -> {
            int bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            v.setTranslationY(-bottomInset);
            return insets;
        });
    }
}

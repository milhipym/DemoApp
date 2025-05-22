package com.db.demoapp.ui.pagingux.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;
import com.db.demoapp.ui.pagingux.adapter.PagingAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class GeneralPaging extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MaterialButton btnPrev, btnNext;
    private TextView tvPageInfo;

    private final int pageSize = 10;
    private int currentPage = 1;
    private int totalPage = 1;
    private List<String> allItems;
    private PagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_paging);

        recyclerView = findViewById(R.id.recyclerView);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        tvPageInfo = findViewById(R.id.tvPageInfo);

        allItems = new ArrayList<>();
        for (int i = 1; i <= 47; i++) {
            allItems.add("아이템 " + i);
        }
        totalPage = (int) Math.ceil(allItems.size() / (double) pageSize);

        adapter = new PagingAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updatePage();

        btnPrev.setOnClickListener(v -> {
            if (currentPage > 1) {
                currentPage--;
                updatePage();
            }
        });

        btnNext.setOnClickListener(v -> {
            if (currentPage < totalPage) {
                currentPage++;
                updatePage();
            }
        });

        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "general_paging"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    private void updatePage() {
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, allItems.size());
        List<String> pageItems = allItems.subList(start, end);

        adapter.setItems(pageItems);
        tvPageInfo.setText(currentPage + " / " + totalPage);

        btnPrev.setEnabled(currentPage > 1);
        btnNext.setEnabled(currentPage < totalPage);
    }
}

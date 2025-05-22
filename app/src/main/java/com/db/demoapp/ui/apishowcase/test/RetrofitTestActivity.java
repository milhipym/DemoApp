package com.db.demoapp.ui.apishowcase.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.db.demoapp.ui.apishowcase.client.RetrofitClient;
import com.db.demoapp.ui.apishowcase.model.User;
import com.db.demoapp.ui.apishowcase.service.ApiService;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTestActivity extends AppCompatActivity {

    private MaterialButton btnTest;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        btnTest = findViewById(R.id.btnTest);
        tvResult = findViewById(R.id.tvResult);

        btnTest.setOnClickListener(v -> fetchUsers());

        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "retrofit"); // ✅ 핵심 포인트
            startActivity(intent);
        });
    }

    private void fetchUsers() {
        tvResult.setText("로딩 중...");
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body();
                    StringBuilder sb = new StringBuilder();
                    for (User user : userList) {
                        sb.append("이름: ").append(user.name)
                                .append("\n이메일: ").append(user.email)
                                .append("\n\n");
                    }
                    tvResult.setText(sb.toString());
                } else {
                    tvResult.setText("API 오류: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tvResult.setText("네트워크 오류: " + t.getMessage());
            }
        });
    }
}

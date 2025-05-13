package com.db.demoapp.code;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.db.demoapp.R;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SlideDownCodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        TextView codeView = findViewById(R.id.codeText);
        codeView.setText(loadAsset("java/slide_down.java"));
    }

    private String loadAsset(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            sb.append("파일 오류: ").append(e.getMessage());
        }
        return sb.toString();
    }
}
package com.db.demoapp.code;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CodeViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_web);

        WebView webView = findViewById(R.id.webCodeView);
        webView.getSettings().setJavaScriptEnabled(true);

        String code = loadAsset("java/slide_down.java");
        String html = loadTemplateWithCode(code);
        webView.loadDataWithBaseURL("file:///android_asset/highlight/", html, "text/html", "utf-8", null);
    }

    private String loadTemplateWithCode(String code) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("highlight/template.html")));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            return builder.toString().replace("{{CODE_HERE}}", code);
        } catch (Exception e) {
            return "<html><body>Error loading template: " + e.getMessage() + "</body></html>";
        }
    }

    private String loadAsset(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line.replace("<", "&lt;").replace(">", "&gt;")).append("\n");
            }
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            return "Failed to load source code: " + e.getMessage();
        }
    }
}
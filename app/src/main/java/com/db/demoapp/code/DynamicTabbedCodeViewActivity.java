package com.db.demoapp.code;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.google.android.material.tabs.TabLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DynamicTabbedCodeViewActivity extends AppCompatActivity {

    WebView webView;
    TabLayout tabLayout;
    String featureName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_dynamic);

        webView = findViewById(R.id.webCodeView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setJavaScriptEnabled(true);
        tabLayout = findViewById(R.id.tabLayout);

        featureName = getIntent().getStringExtra("feature");
        if (featureName == null) featureName = "blink";
        Log.e("CHOI", "onCreate: featureName>>"+featureName );

        tabLayout.addTab(tabLayout.newTab().setText("Java"));
        tabLayout.addTab(tabLayout.newTab().setText("xml"));
        tabLayout.addTab(tabLayout.newTab().setText("animation"));

        loadCode("java");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: loadCode("java"); break;
                    case 1: loadCode("xml"); break;
                    case 2: loadCode("animation"); break;
                }
            }
            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadCode(String type) {
        String path = "slide_down" + "/" + type + "/" + featureName + (type.equals("java") ? ".java" : ".xml");
        String language = type.equals("java") ? "java" : "xml";

        try {
            Log.e("CHOI", "loadCode: path>>"+path );
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(path)));
            StringBuilder code = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line.replace("<", "&lt;").replace(">", "&gt;")).append("\n");
            }
            reader.close();

            String template = loadTemplate();
            String html = template
                .replace("{{LANGUAGE}}", "language-" + language)
                .replace("{{CODE_HERE}}", code.toString());

            webView.loadDataWithBaseURL("file:///android_asset/highlight/", html, "text/html", "utf-8", null);
        } catch (Exception e) {
            webView.loadData("<html><body>Code not found: " + path + "</body></html>", "text/html", "utf-8");
        }
    }

    private String loadTemplate() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("highlight/template.html")));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            return "<html><body>Error loading template: " + e.getMessage() + "</body></html>";
        }
    }
}
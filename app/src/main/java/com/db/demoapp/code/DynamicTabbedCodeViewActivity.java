package com.db.demoapp.code;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DynamicTabbedCodeViewActivity extends AppCompatActivity {

    WebView webView;
    LinearLayout tabContainer;
    String featureName;
    HashMap<String, ArrayList<String>> folderFileMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_dynamic);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            // Android 11 이상
            getWindow().setDecorFitsSystemWindows(false);
        } else {
            // Android 10 이하
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT); // 네비게이션바 투명


        webView = findViewById(R.id.webCodeView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.getSettings().setJavaScriptEnabled(true);

        tabContainer = findViewById(R.id.tabContainer);

        featureName = getIntent().getStringExtra("feature");
        if (featureName == null) featureName = "blink";

        try {
            String[] folders = getAssets().list(featureName);
            if (folders != null) {
                boolean firstTabLoaded = false;

                for (String folder : folders) {
                    String folderPath = featureName + "/" + folder;
                    String[] files = getAssets().list(folderPath);
                    if (files != null) {
                        ArrayList<String> fileList = new ArrayList<>();
                        for (String file : files) {
                            fileList.add(file);

                            // 탭 커스텀 뷰 inflate
                            View customTabView = LayoutInflater.from(this)
                                    .inflate(R.layout.tab_custom_view, tabContainer, false);

                            TextView title = customTabView.findViewById(R.id.tabTitle);
                            TextView subtitle = customTabView.findViewById(R.id.tabSubtitle);

                            title.setText(folder.toUpperCase());
                            subtitle.setText(file);

                            final String fullPath = folder + "/" + file;

                            // 클릭 시 코드 로딩
                            customTabView.setOnClickListener(v -> loadCodeFromTab(fullPath));

                            tabContainer.addView(customTabView);

                            // 첫 번째 탭 자동 로딩
                            if (!firstTabLoaded) {
                                loadCodeFromTab(fullPath);
                                firstTabLoaded = true;
                            }
                        }
                        folderFileMap.put(folder, fileList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCodeFromTab(String tabText) {
        try {
            String[] parts = tabText.split("/");
            String folder = parts[0];
            String fileName = parts[1];
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            String language = ext.equals("java") ? "java" : ext.equals("kt") ? "kotlin" : "xml";

            String path = featureName + "/" + folder + "/" + fileName;
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(path)));
            StringBuilder code = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line.replace("<", "&lt;").replace(">", "&gt;")).append("\n");
            }
            reader.close();

            String template = loadTemplate();
            String html = template.replace("{{LANGUAGE}}", "language-" + language).replace("{{CODE_HERE}}", code.toString());
            webView.loadDataWithBaseURL("file:///android_asset/highlight/", html, "text/html", "utf-8", null);
        } catch (Exception e) {
            webView.loadData("<html><body>Code not found for tab: " + tabText + "</body></html>", "text/html", "utf-8");
        }
    }

    private String loadTemplate() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("highlight/template.html")));
            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line);
            }
            reader.close();
            return html.toString();
        } catch (Exception e) {
            return "<html><body>Error loading template</body></html>";
        }
    }
}


package com.db.demoapp.code;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.db.demoapp.R;
import com.google.android.material.tabs.TabLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DynamicTabbedCodeViewActivity extends AppCompatActivity {

    WebView webView;
    TabLayout tabLayout;
    String featureName;

    HashMap<String, ArrayList<String>> folderFileMap = new HashMap<>();

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

        try {
            String[] folders = getAssets().list(featureName);
            if (folders != null) {
                for (String folder : folders) {
                    String folderPath = featureName + "/" + folder;
                    String[] files = getAssets().list(folderPath);
                    if (files != null) {
                        ArrayList<String> fileList = new ArrayList<>();
                        for (String file : files) {
                            fileList.add(file);

                            // 🔽 커스텀 탭 레이아웃 설정
                            TabLayout.Tab tab = tabLayout.newTab();
                            View customTabView = LayoutInflater.from(this)
                                    .inflate(R.layout.tab_custom_view, null);
                            TextView title = customTabView.findViewById(R.id.tabTitle);
                            TextView subtitle = customTabView.findViewById(R.id.tabSubtitle);

                            title.setText(folder.toUpperCase());
                            subtitle.setText(file);

                            tab.setCustomView(customTabView);
                            tabLayout.addTab(tab);
                        }
                        folderFileMap.put(folder, fileList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if (tabLayout.getTabCount() > 0) {
            TabLayout.Tab firstTab = tabLayout.getTabAt(0);
            if (firstTab != null && firstTab.getCustomView() != null) {
                TextView subtitle = firstTab.getCustomView().findViewById(R.id.tabSubtitle);
                loadCodeFromTab(firstTab.getText() != null ? firstTab.getText().toString()
                        : subtitle.getText().toString());  // 초기 탭 로딩
            }
        }*/

        // 탭 생성 및 커스텀 뷰 설정 후
        if (tabLayout.getTabCount() > 0) {
            TabLayout.Tab firstTab = tabLayout.getTabAt(0);
            if (firstTab != null) {
                firstTab.select();
                // 100ms 딜레이 후 로딩
                new Handler().postDelayed(() -> {
                    if (firstTab.getCustomView() != null) {
                        TextView subtitle = firstTab.getCustomView().findViewById(R.id.tabSubtitle);
                        String folder = ((TextView) firstTab.getCustomView().findViewById(R.id.tabTitle)).getText().toString().toLowerCase();
                        String filename = subtitle.getText().toString();
                        loadCodeFromTab(folder + "/" + filename);
                    }
                }, 100);
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getCustomView() != null) {
                    TextView subtitle = tab.getCustomView().findViewById(R.id.tabSubtitle);
                    String folder = ((TextView) tab.getCustomView().findViewById(R.id.tabTitle)).getText().toString().toLowerCase();
                    String filename = subtitle.getText().toString();
                    loadCodeFromTab(folder + "/" + filename);
                }
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {}
            @Override public void onTabReselected(TabLayout.Tab tab) {}
        });
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

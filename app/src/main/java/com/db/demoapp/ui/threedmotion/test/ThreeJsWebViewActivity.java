package com.db.demoapp.ui.threedmotion.test;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

public class ThreeJsWebViewActivity extends AppCompatActivity {

    private WebView webView;
    private final String URL = "https://threejs-bjchh.run.goorm.site/threejs/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threed_webview);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // JavaScript 허용
        webSettings.setDomStorageEnabled(true); // 로컬 스토리지 허용

        webView.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

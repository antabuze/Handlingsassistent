package com.example.handlingsassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        WebView wv = findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://vadfanskajaglagatillmiddag.nu/");

    }
}
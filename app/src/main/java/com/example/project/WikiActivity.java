package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WikiActivity extends AppCompatActivity {

    private WebView wikiWebView;
    private String wikiUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //find WebView and set settings
        wikiWebView = findViewById(R.id.wikipedia_webview);
        wikiWebView.setWebViewClient(new WebViewClient());
        wikiWebView.getSettings().setJavaScriptEnabled(true);

        // URL from Intents
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
            wikiUrl = intentExtras.getString("wikiUrl");
            Log.d("wikipedia_link",wikiUrl);
        }

        wikiWebView.loadUrl(wikiUrl);

    }


}
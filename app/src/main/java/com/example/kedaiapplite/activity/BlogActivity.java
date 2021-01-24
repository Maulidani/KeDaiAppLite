package com.example.kedaiapplite.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.view.MenuItem;
import android.widget.Toast;

import com.example.kedaiapplite.MainActivity;
import com.example.kedaiapplite.R;

public class BlogActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(BlogActivity.this, MainActivity.class).putExtra("backblog",toString());
//        startActivity(intent);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbarblog);
        toolbar.setTitle("Blog");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
//                Intent intent = new Intent(BlogActivity.this, MainActivity.class).putExtra("backblog",toString());
//                startActivity(intent);
            }
        });


        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);;
            }
        },1000);

        getlink();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    void getlink(){
        WebView webview = (WebView) findViewById(com.example.kedaiapplite.R.id.webview);
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setSupportZoom(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(getIntent().getSerializableExtra("blog").toString());


    }

}
package com.example.kedaiapplite.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kedaiapplite.MainActivity;
import com.example.kedaiapplite.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EbookActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar toolbar;
    PDFView pdfView;


    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(EbookActivity.this, MainActivity.class).putExtra("backebook",toString());
//        startActivity(intent);
        finish();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        pdfView = findViewById(R.id.ebook);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getSupportActionBar().hide();
        toolbar = findViewById(R.id.toolbarebook);
        toolbar.setTitle("E-Book");

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(EbookActivity.this, MainActivity.class).putExtra("backebook",toString());
//                startActivity(intent);
                finish();
            }
        });

        new RetrievePDFStream().execute(getIntent().getSerializableExtra("ebook").toString());

    }

    public void StartDownloading(View view) {
//        String title1 = getIntent().getSerializableExtra("judul").toString();
//        String url = getIntent().getSerializableExtra("ebook").toString();
//        DownloadBooks(url, title1);
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title dialog
        alertDialogBuilder.setTitle("Download Ebook Pdf ?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(getIntent().getSerializableExtra("judul").toString())
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title1 = getIntent().getSerializableExtra("judul").toString();
                        String url = getIntent().getSerializableExtra("ebook").toString();
                        DownloadBooks(url, title1);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();

    }


    void DownloadBooks(String url, String title1){
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        String tempTitle=title1.replace("","");
        request.setTitle(tempTitle);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,tempTitle+".pdf");
        DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
    }

    class  RetrievePDFStream extends AsyncTask<String,Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }  catch (IOException e) {
                return null;
            }return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream).load();
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(EbookActivity.this, "Mohon tunggu...", Toast.LENGTH_SHORT).show();

        }


    }

}
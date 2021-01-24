package com.example.kedaiapplite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kedaiapplite.fragment.BlogFragment;
import com.example.kedaiapplite.fragment.EbookFragment;
import com.example.kedaiapplite.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack){
            super.onBackPressed();
            return;
        }
        this.doubleBack = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack=false;
            }
        },2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();
        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigasi);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


//        Intent intent = getIntent();
//        if (intent.getStringExtra("blog") != null){
//            bottomNavigationView.setSelectedItemId(R.id.blog_menu);
//        }
//
//        if (intent.getStringExtra("ebook") != null){
//            bottomNavigationView.setSelectedItemId(R.id.ebook_menu);
//
//        }
//        if (intent.getStringExtra("backblog") != null){
//            bottomNavigationView.setSelectedItemId(R.id.blog_menu);
//
//        }
//        if (intent.getStringExtra("backebook") != null){
//            bottomNavigationView.setSelectedItemId(R.id.ebook_menu);
//
//        }

    }

       boolean loadFragment(Fragment fragment) {
        if (fragment != null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.blog_menu:
                fragment = new BlogFragment();
                break;
            case R.id.ebook_menu:
                fragment = new EbookFragment();
                break;
        }
        return loadFragment(fragment);
    }

}
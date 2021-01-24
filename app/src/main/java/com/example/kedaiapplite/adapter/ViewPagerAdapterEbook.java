package com.example.kedaiapplite.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.kedaiapplite.fragment.semuablog.HardwareBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.JaringanBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.MultimediaBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.ProgramBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.SemuaBlogFragment;
import com.example.kedaiapplite.fragment.semuaebook.HardwareEbookFragment;
import com.example.kedaiapplite.fragment.semuaebook.JaringanEbookFragment;
import com.example.kedaiapplite.fragment.semuaebook.MultimediaEbookFragment;
import com.example.kedaiapplite.fragment.semuaebook.ProgramEbookFragment;
import com.example.kedaiapplite.fragment.semuaebook.SemuaEbookFragment;

public class ViewPagerAdapterEbook extends FragmentPagerAdapter {


    public ViewPagerAdapterEbook(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0){
            fragment = new SemuaEbookFragment();
        } else if (position == 1){
            fragment = new ProgramEbookFragment();
        } else if (position == 2){
            fragment = new MultimediaEbookFragment();
        } else if (position == 3){
            fragment = new JaringanEbookFragment();
        } else if (position == 4){
            fragment = new HardwareEbookFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;

        if (position == 0){
            title = "Semua";
        } else if (position == 1){
            title = "Program";
        } else if (position == 2){
            title = "Multimedia";
        } else if (position == 3){
            title = "Jaringan";
        } else if (position == 4){
            title = "Hardware";
        }
        return title;
    }
}

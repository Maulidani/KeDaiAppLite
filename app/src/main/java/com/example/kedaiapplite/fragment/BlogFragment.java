package com.example.kedaiapplite.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kedaiapplite.R;
import com.example.kedaiapplite.adapter.AdapterBlog;
//import com.example.kedaiapplite.api.ApiClient;
//import com.example.kedaiapplite.api.ApiInterface;
import com.example.kedaiapplite.adapter.ViewPagerAdapter;
import com.example.kedaiapplite.api.ApiClient;
import com.example.kedaiapplite.api.ApiInterface;
import com.example.kedaiapplite.fragment.semuablog.HardwareBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.JaringanBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.MultimediaBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.ProgramBlogFragment;
import com.example.kedaiapplite.fragment.semuablog.SemuaBlogFragment;
import com.example.kedaiapplite.model.DataBlog;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogFragment extends Fragment {

    View v;
    ViewPager viewPager;
    TabLayout tabblog;
    ViewPagerAdapter viewPagerAdapter;
    SwipeRefreshLayout swipe_refresh;
//    SemuaBlogFragment semuaBlogFragment;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          android.os.Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        v = view;

//        swipe_refresh = v.findViewById(R.id.swipe_refresh);
//        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipe_refresh.setRefreshing(false);
//                viewPagerAdapter = new ViewPagerAdapter(
//                        getActivity().getSupportFragmentManager());
//                viewPager.setAdapter(viewPagerAdapter);
//
//                tabblog.setupWithViewPager(viewPager);
//            }
//        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Blog");

        viewPager = v.findViewById(R.id.viewPagerBlog);
        tabblog = v.findViewById(R.id.tabblog);

        viewPagerAdapter = new ViewPagerAdapter(
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabblog.setupWithViewPager(viewPager,true);

    }

}










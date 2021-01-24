package com.example.kedaiapplite.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kedaiapplite.R;
import com.example.kedaiapplite.adapter.AdapterEbook;
import com.example.kedaiapplite.adapter.ViewPagerAdapter;
import com.example.kedaiapplite.adapter.ViewPagerAdapterEbook;
import com.example.kedaiapplite.api.ApiClient;
import com.example.kedaiapplite.api.ApiInterface;
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
import com.example.kedaiapplite.model.DataEbook;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EbookFragment extends Fragment {

    View v;
    ViewPager viewPager;
    TabLayout tabebook;
    ViewPagerAdapterEbook viewPagerAdapterEbook;

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          android.os.Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ebook, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        v = view;

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("E-Book");

        viewPager = v.findViewById(R.id.viewPagerEbook);
        tabebook = v.findViewById(R.id.tabebook);

        viewPagerAdapterEbook = new ViewPagerAdapterEbook(
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterEbook);

        tabebook.setupWithViewPager(viewPager,true);


    }

}
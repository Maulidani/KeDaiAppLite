package com.example.kedaiapplite.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kedaiapplite.R;
import com.example.kedaiapplite.adapter.AdapterBlogHome;
import com.example.kedaiapplite.adapter.AdapterEbookHome;
import com.example.kedaiapplite.api.ApiClient;
import com.example.kedaiapplite.api.ApiInterface;
import com.example.kedaiapplite.model.DataBlog;
import com.example.kedaiapplite.model.DataEbook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.recyclerview.widget.RecyclerView.HORIZONTAL;


public class HomeFragment extends Fragment {

    ImageView info ;
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterBlogHome adapterBlog;
    AdapterEbookHome adapterEbook;
    RecyclerView rv_blog, rv_ebook;
    View v;
    TextView lihat_semua_blog, lihat_semua_ebook;
    Context context;
    Intent lihatblog, lihatebook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(R.drawable.ic_outline_info_24);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);

//        Toolbar toolbar = view.findViewById(R.id.toolbarhome);
//        info = view.findViewById(R.id.info);
//        info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(view.getContext(), InfoActivity.class);
//                startActivity(intent);
//            }
//        });


        v = view;

//        lihat_semua_blog = view.findViewById(R.id.lihat_semua_blog);
//        lihat_semua_blog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(view.getContext(), MainActivity.class).putExtra("blog",toString()));
//            }
//        });
//
//        lihat_semua_ebook = view.findViewById(R.id.lihat_semua_ebook);
//        lihat_semua_ebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(view.getContext(), MainActivity.class).putExtra("ebook",toString()));
//            }
//        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataBlog();
                getDataEbook();
            }
        });

        rv_blog = view.findViewById(R.id.rv_blog);
        rv_blog.setLayoutManager(new LinearLayoutManager(view.getContext(), HORIZONTAL, false));
        getDataBlog();

        rv_ebook = view.findViewById(R.id.rv_ebook);
        rv_ebook.setLayoutManager(new LinearLayoutManager(view.getContext(), HORIZONTAL, false));
        getDataEbook();

    }

    public void getDataBlog() {

        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<DataBlog> call = apiInterface.getData();
        call.enqueue(new Callback<DataBlog>() {
            @Override
            public void onResponse(Call<DataBlog> call, Response<DataBlog> response) {


                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    adapterBlog = new AdapterBlogHome(response.body(), v.getContext());
                    adapterBlog.notifyDataSetChanged();
                    rv_blog.setAdapter(adapterBlog);
                }
            }

            @Override
            public void onFailure(Call<DataBlog> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(v.getContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDataEbook(){

        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<DataEbook> call = apiInterface.getDataEbook();
        call.enqueue(new Callback<DataEbook>() {
            @Override
            public void onResponse(Call<DataEbook> call, Response<DataEbook> response) {

                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() &&  response.body() != null){
                    adapterEbook = new AdapterEbookHome(response.body(), v.getContext());
                    adapterEbook.notifyDataSetChanged();
                    rv_ebook.setAdapter(adapterEbook);
                }
            }

            @Override
            public void onFailure(Call<DataEbook> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

}
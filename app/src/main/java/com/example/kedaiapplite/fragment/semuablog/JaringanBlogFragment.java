package com.example.kedaiapplite.fragment.semuablog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kedaiapplite.R;
import com.example.kedaiapplite.adapter.AdapterBlog;
import com.example.kedaiapplite.api.ApiClient;
import com.example.kedaiapplite.api.ApiInterface;
import com.example.kedaiapplite.model.DataBlog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JaringanBlogFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterBlog adapterBlog;
    RecyclerView rv;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jaringan_blog, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        v = view;
        getDataBlog();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataBlog();
            }
        });
    }

    public void getDataBlog(){

        swipeRefreshLayout.setRefreshing(true);

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<DataBlog> call = apiInterface.getDataKategorJaringaniBlog();
        call.enqueue(new Callback<DataBlog>() {
            @Override
            public void onResponse(Call<DataBlog> call, Response<DataBlog> response) {

                swipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful() &&  response.body() != null){
                    rv = v.findViewById(R.id.rv_blogjaringan);
                    rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    adapterBlog = new AdapterBlog(response.body().getBlog(), v.getContext());
                    adapterBlog.notifyDataSetChanged();
                    rv.setAdapter(adapterBlog);
                }
            }

            @Override
            public void onFailure(Call<DataBlog> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(v.getContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
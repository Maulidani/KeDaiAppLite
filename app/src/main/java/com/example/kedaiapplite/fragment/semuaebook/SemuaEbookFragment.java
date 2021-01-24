package com.example.kedaiapplite.fragment.semuaebook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
import com.example.kedaiapplite.adapter.AdapterEbook;
import com.example.kedaiapplite.api.ApiClient;
import com.example.kedaiapplite.api.ApiInterface;
import com.example.kedaiapplite.model.DataEbook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SemuaEbookFragment extends Fragment {
    SwipeRefreshLayout swipeRefreshLayout;
    AdapterEbook adapterEbook;
    RecyclerView rv;
    View v;
    android.widget.SearchView svebook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_semua_ebook, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        v = view;
        getDataEbook();
        svebook = view.findViewById(R.id.svebook);
        svebook.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterEbook.getFilter().filter(newText);
                return true;
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataEbook();
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
                    rv = v.findViewById(R.id.rv_ebook);
                    rv.setLayoutManager(new LinearLayoutManager(v.getContext()));
                    adapterEbook = new AdapterEbook(response.body().getEbook(), v.getContext());
                    adapterEbook.notifyDataSetChanged();
                    rv.setAdapter(adapterEbook);
                }
            }

            @Override
            public void onFailure(Call<DataEbook> call, Throwable t) {

                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(v.getContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
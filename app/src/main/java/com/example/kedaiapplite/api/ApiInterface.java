package com.example.kedaiapplite.api;

import com.example.kedaiapplite.model.DataBlog;
import com.example.kedaiapplite.model.DataEbook;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {


    //Blog

    @GET("read_artikel.php")
    Call<DataBlog> getData();

    @GET("read_kategori_artikel.php?kategori='Program'")
    Call<DataBlog> getDataKategoriProgramiBlog();

    @GET("read_kategori_artikel.php?kategori='Multimedia'")
    Call<DataBlog> getDataKategoriMultimediaBlog();

    @GET("read_kategori_artikel.php?kategori='Network'")
    Call<DataBlog> getDataKategorJaringaniBlog();

    @GET("read_kategori_artikel.php?kategori='Hardware'")
    Call<DataBlog> getDataKategoriHardwareiBlog();


    //Ebook

    @GET("read_ebook.php")
    Call<DataEbook> getDataEbook();

    @GET("read_kategori_ebook.php?kategori='Program'")
    Call<DataEbook> getDataKategoriProgramiEbook();

    @GET("read_kategori_ebook.php?kategori='Multimedia'")
    Call<DataEbook> getDataKategoriMultimediaEbook();

    @GET("read_kategori_ebook.php?kategori='Network'")
    Call<DataEbook> getDataKategoriJaringaniEbook();

    @GET("read_kategori_ebook.php?kategori='Hardware'")
    Call<DataEbook> getDataKategoriHardwareiEbook();


}

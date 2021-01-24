package com.example.kedaiapplite.model;

import com.google.gson.annotations.SerializedName;

public class BlogModel {
    @SerializedName("id")
    String id;

    @SerializedName("gambar")
    String gambar;

    @SerializedName("judul")
    String judul;

    @SerializedName("kategori")
    String kategori;

    @SerializedName("tanggal_upload")
    String upload;

    @SerializedName("link")
    String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

package com.example.kedaiapplite.model;

import com.google.gson.annotations.SerializedName;

public class EbookModel {


    @SerializedName("gambar")
    String gambar;

    @SerializedName("judul")
    String judul;

    @SerializedName("nama")
    String nama;

    @SerializedName("kategori")
    String kategori;

    @SerializedName("deskripsi")
    String deskripsi;

    @SerializedName("tanggal_upload")
    String upload;

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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }
}

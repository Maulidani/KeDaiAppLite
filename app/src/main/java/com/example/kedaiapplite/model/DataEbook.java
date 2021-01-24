package com.example.kedaiapplite.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataEbook {
    @SerializedName("data")
    ArrayList<EbookModel> ebook;

    public ArrayList<EbookModel> getEbook()
    {return ebook;}

    public void setEbook(ArrayList<EbookModel> ebook) {
        this.ebook = ebook;
    }
}

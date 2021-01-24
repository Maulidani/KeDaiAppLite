package com.example.kedaiapplite.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataBlog {
    @SerializedName("data")
    ArrayList<BlogModel> blog;

    public ArrayList<BlogModel> getBlog() {
        return blog;
    }

    public void setBlog(ArrayList<BlogModel> blog) {
        this.blog = blog;
    }
}

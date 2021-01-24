package com.example.kedaiapplite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kedaiapplite.R;
import com.example.kedaiapplite.activity.BlogActivity;
import com.example.kedaiapplite.model.DataBlog;


public class AdapterBlogHome extends RecyclerView.Adapter<AdapterBlogHome.ViewHolder> {

    private DataBlog dataBlog;
    private Context context;

    public AdapterBlogHome(DataBlog dataBlog, Context context) {
        this.dataBlog = dataBlog;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_blog_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context)
                .load(dataBlog.getBlog().get(position).getGambar())
                .into(holder.blog_cover_img);

        holder.blog_title.setText(dataBlog.getBlog().get(position).getJudul());
        holder.blog_date.setText(dataBlog.getBlog().get(position).getUpload());
        holder.blog_konsentrasi.setText(dataBlog.getBlog().get(position).getKategori());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BlogActivity.class).putExtra("blog", dataBlog.getBlog().get(position).getLink()+"?id="+dataBlog.getBlog().get(position).getId()));
            }
        });
    }
    @Override
    public int getItemCount()
    {

        return Math.min( dataBlog.getBlog()==null?0:dataBlog.getBlog().size(), 3);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView blog_cover_img;
        TextView blog_title, blog_date,blog_konsentrasi;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_konsentrasi = itemView.findViewById(R.id.blog_konsentrasi);
            blog_cover_img = itemView.findViewById(R.id.blog_cover_img);
            blog_title = itemView.findViewById(R.id.blog_title);
            blog_date = itemView.findViewById(R.id.blog_date);
            cardView = itemView.findViewById(R.id.cv_blog_home);
        }
    }
}

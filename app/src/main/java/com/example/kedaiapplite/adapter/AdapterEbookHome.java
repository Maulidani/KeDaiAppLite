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
import com.example.kedaiapplite.activity.EbookActivity;
import com.example.kedaiapplite.model.DataEbook;

public class AdapterEbookHome extends RecyclerView.Adapter<AdapterEbookHome.ViewHolder> {

    private DataEbook dataEbook;
    private Context context;

    public AdapterEbookHome(DataEbook dataEbook, Context context) {
        this.dataEbook = dataEbook;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_ebook_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(dataEbook.getEbook().get(position).getGambar())
                .into(holder.ebook_cover_img);
        holder.ebook_title.setText(dataEbook.getEbook().get(position).getJudul());
        holder.ebook_konsentrasi.setText(dataEbook.getEbook().get(position).getKategori());
        holder.ebook_date.setText(dataEbook.getEbook().get(position).getUpload());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EbookActivity.class).putExtra("ebook", dataEbook.getEbook().get(position).getNama()).putExtra("judul", dataEbook.getEbook().get(position).getJudul()));
            }
        });

    }
    @Override
    public int getItemCount() {

        return Math.min( dataEbook.getEbook()==null?0:dataEbook.getEbook().size(), 3);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ebook_cover_img;
        TextView ebook_title, ebook_konsentrasi, ebook_date;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ebook_cover_img = itemView.findViewById(R.id.ebook_cover_img);
            ebook_title = itemView.findViewById(R.id.ebook_title);
            ebook_konsentrasi = itemView.findViewById(R.id.ebook_konsentrasi);
            ebook_date = itemView.findViewById(R.id.ebook_date);
            cardView = itemView.findViewById(R.id.cv_ebook_home);
        }
    }
}


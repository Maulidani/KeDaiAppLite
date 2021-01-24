package com.example.kedaiapplite.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kedaiapplite.R;
import com.example.kedaiapplite.activity.EbookActivity;
import com.example.kedaiapplite.model.BlogModel;
import com.example.kedaiapplite.model.DataBlog;
import com.example.kedaiapplite.model.DataEbook;
import com.example.kedaiapplite.model.EbookModel;

import java.util.ArrayList;

public class AdapterEbook extends RecyclerView.Adapter<AdapterEbook.ViewHolder> implements Filterable {

    private DataEbook dataEbook;
    private DataEbook dataEbook2;
    private ArrayList<EbookModel> filtered;
    private ArrayList<EbookModel> liat;
    private Context context;


    public AdapterEbook(ArrayList<EbookModel> dataEbook1, Context context) {
        this.context = context;
        this.filtered = dataEbook1;
        this.liat = dataEbook1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list



        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_ebook, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context)
                .load(filtered.get(position).getGambar())
                .into(holder.ebook_cover_img);
        holder.ebook_title.setText(filtered.get(position).getJudul());
        holder.ebook_desk.setText(filtered.get(position).getDeskripsi());
        holder.ebook_konsentrasi.setText(filtered.get(position).getKategori());
        holder.ebook_date.setText(filtered.get(position).getUpload());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EbookActivity.class).putExtra("ebook",
                        filtered.get(position).getNama()).putExtra("judul",
                        filtered.get(position).getJudul()));
            }
        });

    }
    @Override
    public int getItemCount() {

        return filtered==null?0: filtered.size() ;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    filtered = liat;
                } else {
                    ArrayList<EbookModel> filtered1 = new ArrayList<>();
                    for (EbookModel model : liat) {
                        if (model.getJudul().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getJudul().toLowerCase().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getJudul().toUpperCase().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getKategori().contains(charString)){
                            filtered1.add(model);
                        } else if (model.getKategori().toLowerCase().contains(charString)){
                            filtered1.add(model);
                        }else if (model.getKategori().toUpperCase().contains(charString)){
                            filtered1.add(model);
                        }
                    }
                    filtered = filtered1;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    filtered = (ArrayList<EbookModel>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ebook_cover_img;
        TextView ebook_title, ebook_desk, ebook_konsentrasi, ebook_date;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ebook_cover_img = itemView.findViewById(R.id.ebook_cover_img);
            ebook_title = itemView.findViewById(R.id.ebook_title);
            ebook_desk = itemView.findViewById(R.id.ebook_desk);
            ebook_konsentrasi = itemView.findViewById(R.id.ebook_konsentrasi);
            ebook_date = itemView.findViewById(R.id.ebook_date);
            cardView = itemView.findViewById(R.id.cv_ebook);
        }
    }
}


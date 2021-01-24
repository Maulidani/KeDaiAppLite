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
import com.example.kedaiapplite.activity.BlogActivity;
import com.example.kedaiapplite.model.BlogModel;
import com.example.kedaiapplite.model.DataBlog;

import java.util.ArrayList;

public class AdapterBlog extends RecyclerView.Adapter<AdapterBlog.ViewHolder> implements Filterable {

    private DataBlog dataBlog;
    private DataBlog dataBlog2;
    private ArrayList<BlogModel> filtered;
    private ArrayList<BlogModel> liat;

    //    private List<BlogModel> dataBlog2;
    private Context context;

    public AdapterBlog(ArrayList<BlogModel> dataBlog1, Context context) {
        this.context = context;
        this.filtered = dataBlog1;
        this.liat = dataBlog1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Mendeklarasi tampilan list
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context)
                .load(filtered.get(position).getGambar())
                .into(holder.blog_cover_img);
        holder.blog_title.setText(filtered.get(position).getJudul());
        holder.blog_date.setText(filtered.get(position).getUpload());
        holder.blog_konsentrasi.setText(filtered.get(position).getKategori());
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, BlogActivity.class).putExtra("blog", filtered.get(position).getLink() + "?id=" + filtered.get(position).getId()));
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
                    ArrayList<BlogModel> filtered1 = new ArrayList<>();
                    for (BlogModel model : liat) {
                        if (model.getJudul().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getJudul().toLowerCase().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getJudul().toUpperCase().contains(charString)) {
                            filtered1.add(model);
                        } else if (model.getKategori().contains(charString)){
                            filtered1.add(model);
                        }else if (model.getKategori().toLowerCase().contains(charString)){
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
                    filtered = (ArrayList<BlogModel>) results.values;
                    notifyDataSetChanged();
                }
            }
        };


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView blog_cover_img;
        TextView blog_title, blog_date, blog_konsentrasi;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            blog_konsentrasi = itemView.findViewById(R.id.blog_konsentrasi);
            blog_cover_img = itemView.findViewById(R.id.blog_cover_img);
            blog_title = itemView.findViewById(R.id.blog_title);
            blog_date = itemView.findViewById(R.id.blog_date);
            cardView = itemView.findViewById(R.id.cv_blog);
        }
    }
}
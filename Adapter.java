package com.cech.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<DataIO> dados;
    Context context;
    CustomClickListener listener;

    public Adapter() { }

    public Adapter(Context context, List<DataIO> dados, CustomClickListener listener) {
        this.context = context;
        this.dados = dados;
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_text, short_desc_text;
        ImageView image;

        private String item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_text = itemView.findViewById(R.id.language_textView);
            short_desc_text = itemView.findViewById(R.id.short_desc_textView);
            image = itemView.findViewById(R.id.imageView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                listener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.with(context).load(dados.get(position).getUrl()).into(holder.image);
        holder.title_text.setText(dados.get(position).getTitle());
        holder.short_desc_text.setText(dados.get(position).getShort_desc());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

}

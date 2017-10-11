package com.example.mohammed.itunesapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammed.itunesapi.network.model.Result;
import com.example.mohammed.itunesapi.network.service.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.PopViewHolder>{

    List<Result> results;
    int list_item;
    Context applicationContext;
    private final OnItemClickListener listener;

    public PopAdapter(List<Result> results, int list_item, Context applicationContext, OnItemClickListener listener) {
        this.results =results;
        this.list_item = list_item;
        this.applicationContext = applicationContext;
        this.listener = listener;
    }

    public int getItemCount() {
        return results.size();
    }

    @Override
    public void onBindViewHolder(PopViewHolder holder, int position) {

        holder.tvArtist.setText(results.get(position).getArtistName());
        holder.tvCollection.setText(results.get(position).getCollectionName());
        holder.tvPrice.setText(results.get(position).getTrackPrice().toString());

        Picasso.with(applicationContext)
                .load(results.get(position).getArtworkUrl60())
                .resize(200, 200)
                .centerCrop()
                .into(holder.imgArt);

        holder.Bind(results.get(position), listener);
    }

    @Override
    public PopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);

        return new PopViewHolder(view);
    }

    public class PopViewHolder  extends RecyclerView.ViewHolder{

        @BindView(R.id.artistName)
                TextView tvArtist;
        @BindView(R.id.collectionName)
                TextView tvCollection;
        @BindView(R.id.imageView)
                ImageView imgArt;
        @BindView(R.id.trackPrice)
                TextView tvPrice;


        public PopViewHolder(View itemView){
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void Bind(Result result, OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onItemClick(result));
        }
    }
}

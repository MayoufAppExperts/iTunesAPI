package com.example.mohammed.itunesapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.mohammed.itunesapi.network.RealmController;
import com.example.mohammed.itunesapi.network.model.MusicList;
//import com.example.mohammed.itunesapi.network.model.RealmDB;
import com.example.mohammed.itunesapi.network.model.Result;
import com.example.mohammed.itunesapi.network.service.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;


/**
 * Created by Mohammed on 01/10/2017.
 */

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.PopViewHolder>{


    Realm realm;
    //RealmController realmController;
    //RealmDB musicRealm;
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

//        for (int i=0; i<results.size(); i++){
//
//            musicRealm = new RealmDB(
//                    results.get(position).getArtistName(),
//                    results.get(position).getCollectionName(),
//                    results.get(position).getTrackPrice().toString());
//            realmController.saveMusic(musicRealm);
//            Log.i("check", musicRealm.getResults().get(position).getArtistName());
//        }
    }

    @Override
    public PopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);

//        realm = Realm.getDefaultInstance();
//        realmController = new RealmController(realm);
        return new PopViewHolder(view);
    }

    public class PopViewHolder  extends RecyclerView.ViewHolder{

        TextView tvArtist, tvCollection, tvPrice;
        ImageView imgArt;

        public PopViewHolder(View itemView){
            super(itemView);

            tvArtist = (TextView)itemView.findViewById(R.id.artistName);
            tvCollection = (TextView)itemView.findViewById(R.id.collectionName);
            imgArt = (ImageView) itemView.findViewById(R.id.imageView);
            tvPrice = (TextView)itemView.findViewById(R.id.trackPrice);

        }

        public void Bind(Result result, OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(result);
                }
            });
        }
    }
}

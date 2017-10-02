package com.example.mohammed.itunesapi;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.network.model.Result;
import com.example.mohammed.itunesapi.network.service.OnItemClickListener;
import com.example.mohammed.itunesapi.ui.rockList.RockListPresenter;
import com.example.mohammed.itunesapi.ui.rockList.iRockListMvpView;
import com.example.mohammed.itunesapi.ui.utils.rx.AppSchedulerProvider;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class RockFragment extends Fragment implements iRockListMvpView {

    private RockListPresenter<iRockListMvpView> iRockListMvpViewRockListPresenter;
    public RecyclerView recyclerView;
    SwipeRefreshLayout mySwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,  container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        presenter();
        initaliseRecyclerView(view);
        swipeRefresh(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void swipeRefresh(View view) {

        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        presenter();
                        myUpdateOperation();
                        initaliseRecyclerView(view);
                    }

                    public void myUpdateOperation() {

                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void presenter() {

        iRockListMvpViewRockListPresenter = new RockListPresenter<>(
                new AppDataManager(),
                new AppSchedulerProvider(),
                new CompositeDisposable());

        iRockListMvpViewRockListPresenter.onAttach(this);
        iRockListMvpViewRockListPresenter.onViewPrepared();

    }

    private void initaliseRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.pop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        Log.i("RecyclerCheck", "has been initialised");
    }

    @Override
    public void onFetchRockCompleted(MusicList musicList) {
        recyclerView.setAdapter(new PopAdapter(musicList.getResults(), R.layout.list_item, getActivity().getApplicationContext(), new OnItemClickListener() {
            @Override
            @SuppressWarnings("deprecation")
            public void onItemClick(Result result) {
                Toast.makeText(getActivity().getApplicationContext(), result.getArtistName().toString(), Toast.LENGTH_SHORT).show();
                String URL = result.getPreviewUrl();
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
            }
            //Toast.makeText(getActivity().getApplicationContext(), result.getArtistName().toString(), Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }
}
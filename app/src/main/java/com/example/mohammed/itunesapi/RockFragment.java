package com.example.mohammed.itunesapi;

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

import com.example.mohammed.itunesapi.network.ReqClass;
import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.network.model.Result;
import com.example.mohammed.itunesapi.network.service.OnItemClickListener;
import com.example.mohammed.itunesapi.ui.RockViewModel;

import java.io.IOException;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

//import com.example.mohammed.itunesapi.ui.rockList.RockListPresenter;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class RockFragment extends Fragment {

    //private RockListPresenter<iRockListMvpView> iRockListMvpViewRockListPresenter;
    public RecyclerView recyclerView;
    SwipeRefreshLayout mySwipeRefreshLayout;
    private Subscription subscription = new CompositeSubscription();
    private RockViewModel rockViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rockViewModel = new RockViewModel(new ReqClass(),
                AndroidSchedulers.mainThread());


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

        subscription = rockViewModel.useCaseRockList()
                .subscribe(new Observer<MusicList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(MusicList musicList) {

                        recyclerView.setAdapter(new PopAdapter(musicList.getResults(), R.layout.list_item, getActivity().getApplicationContext(), new OnItemClickListener() {
                            @Override
                            @SuppressWarnings("deprecation")
                            public void onItemClick(Result result) {
                                Toast.makeText(getActivity().getApplicationContext(), result.getArtistName().toString(), Toast.LENGTH_SHORT).show();
                                if (((MainActivity) getActivity()).urlPlaying == result.getPreviewUrl()) {
                                    ((MainActivity) getActivity()).mediaPlayer.reset();
                                } else {
                                    ((MainActivity) getActivity()).urlPlaying = result.getPreviewUrl();

                                    try {
                                        ((MainActivity) getActivity()).mediaPlayer.reset();
                                        ((MainActivity) getActivity()).mediaPlayer.setDataSource(result.getPreviewUrl());
                                        ((MainActivity) getActivity()).mediaPlayer.prepare();
                                        ((MainActivity) getActivity()).mediaPlayer.start();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }));

                    }
                });
//
//        iRockListMvpViewRockListPresenter = new RockListPresenter<>(
//                new AppDataManager(),
//                new AppSchedulerProvider(),
//                new CompositeDisposable());
//
//        iRockListMvpViewRockListPresenter.onAttach(this);
//        iRockListMvpViewRockListPresenter.onViewPrepared();

    }

    private void initaliseRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.pop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        Log.i("RecyclerCheck", "has been initialised");
    }
}

/*
    @Override
    public void onFetchRockCompleted(MusicList musicList) {

    }
*/

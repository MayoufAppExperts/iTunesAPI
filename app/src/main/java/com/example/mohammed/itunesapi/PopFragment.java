package com.example.mohammed.itunesapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.mohammed.itunesapi.ui.PopViewModel;
import com.example.mohammed.itunesapi.ui.base.BaseFragment;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class PopFragment extends BaseFragment {
        //implements iPopListMvpView


    //private PopListPresenter<iPopListMvpView> iPopListMvpViewPopListPresenter;
    @BindView(R.id.pop_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mySwipeRefreshLayout;

    Subscription subscriptions = new CompositeSubscription();
    private PopViewModel popViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        popViewModel = new PopViewModel(new ReqClass(),
                AndroidSchedulers.mainThread());

        ButterKnife.bind(this, view);
        presenter();
        initaliseRecyclerView(view);
        swipeRefresh(view);



        super.onViewCreated(view, savedInstanceState);
    }

    private void swipeRefresh(View view) {

        //mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
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


        subscriptions = popViewModel.useCasePopList()
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
                            @SuppressWarnings("deprecation")
                            @Override
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

        /*iPopListMvpViewPopListPresenter = new PopListPresenter<>(
                new AppDataManager(),
                new AppSchedulerProvider(),
                new CompositeDisposable());

        iPopListMvpViewPopListPresenter.onAttach(this);
        iPopListMvpViewPopListPresenter.onViewPrepared();*/

    }

    private void initaliseRecyclerView(View view) {
        //recyclerView = (RecyclerView) view.findViewById(R.id.pop_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        Log.i("RecyclerCheck", "has been initialised");
    }

/*
    @Override
    public void onFetchPopCompleted(MusicList musicList) {
        recyclerView.setAdapter(new PopAdapter(musicList.getResults(), R.layout.list_item, getActivity().getApplicationContext(), new OnItemClickListener() {
            @SuppressWarnings("deprecation")
            @Override
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
*/

}

package com.example.mohammed.itunesapi.ui.rockList;

import com.example.mohammed.itunesapi.DataManager;
import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.ui.base.BasePresenter;
import com.example.mohammed.itunesapi.ui.utils.rx.SchedulerProvider;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class RockListPresenter
    <V extends iRockListMvpView>
    extends BasePresenter<V>
    implements iRockListMvpPresenter<V>{


    public RockListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getCompositeDisposable().add(getDataManager()
                .useCaseRockList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MusicList>() {
                               @Override
                               public void accept(@NonNull MusicList musicList) throws Exception {
                                   getMvpView().onFetchRockCompleted(musicList);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                getMvpView().onError(throwable.getMessage());
                            }
                        }));

    }
}

package com.example.mohammed.itunesapi.network;

import com.example.mohammed.itunesapi.network.model.API_Constants;
import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.network.service.ReqInterface;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by TheAppExperts on 02/10/2017.
 */

public class ReqClass implements ReqInterface {

    private ReqInterface reqInterface;

    public ReqClass() {

        //this.reqInterface = reqInterface;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        reqInterface = retrofit.create(ReqInterface.class);
    }

    @Override
    public Observable<MusicList> getRock() {
        return reqInterface.getRock().subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<MusicList> getClassic() {
        return reqInterface.getClassic().subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<MusicList> getPop() {
        return reqInterface.getPop().subscribeOn(Schedulers.io());
    }
}

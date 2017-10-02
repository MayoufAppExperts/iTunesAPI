package com.example.mohammed.itunesapi.ui;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.network.service.ReqInterface;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by TheAppExperts on 02/10/2017.
 */

public class ClassicViewModel {

    private ReqInterface reqInterface;
    private Scheduler scheduler;

    public ClassicViewModel(ReqInterface reqInterface, Scheduler scheduler) {
        this.reqInterface = reqInterface;
        this.scheduler = scheduler;
    }

    public Observable<MusicList> useCaseClassicList(){
        return reqInterface.getClassic().observeOn(scheduler);
    }
}

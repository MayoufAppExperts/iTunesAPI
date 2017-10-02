package com.example.mohammed.itunesapi.network;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.network.service.ConnectionService;
import com.example.mohammed.itunesapi.network.service.ReqInterface;

import io.reactivex.Observable;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class AppApiHelper  implements ApiHelper {

    private ReqInterface reqInterface;

    public AppApiHelper() {
        this.reqInterface = ConnectionService.getConnectionService();
    }

    @Override
    public Observable<MusicList> useCasePopList() {
        return reqInterface.getPop();
    }

    @Override
    public Observable<MusicList> useCaseRockList() {
        return reqInterface.getRock();
    }

    @Override
    public Observable<MusicList> useCaseClassicList() {
        return reqInterface.getClassic();
    }
}

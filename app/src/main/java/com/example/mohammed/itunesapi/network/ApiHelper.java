package com.example.mohammed.itunesapi.network;

import com.example.mohammed.itunesapi.network.model.MusicList;

import rx.Observable;


/**
 * Created by Mohammed on 01/10/2017.
 */

public interface ApiHelper {
    Observable<MusicList> useCasePopList();
    Observable<MusicList> useCaseRockList();
    Observable<MusicList> useCaseClassicList();
}

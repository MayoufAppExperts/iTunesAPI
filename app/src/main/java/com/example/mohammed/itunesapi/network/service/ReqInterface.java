package com.example.mohammed.itunesapi.network.service;

import com.example.mohammed.itunesapi.network.model.API_Constants;
import com.example.mohammed.itunesapi.network.model.MusicList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Mohammed on 01/10/2017.
 */

public interface ReqInterface {

    @GET (API_Constants.ROCK_URL)
    Observable<MusicList> getRock();


    @GET (API_Constants.CLASSIC_URL)
    Observable<MusicList> getClassic();


    @GET (API_Constants.POP_URL)
    Observable<MusicList> getPop();

}

package com.example.mohammed.itunesapi.ui.rockList;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.ui.base.MvpView;

/**
 * Created by Mohammed on 01/10/2017.
 */

public interface iRockListMvpView extends MvpView {

    void onFetchRockCompleted(MusicList musicList);
}

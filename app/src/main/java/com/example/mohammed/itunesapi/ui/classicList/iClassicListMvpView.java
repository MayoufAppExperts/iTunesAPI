package com.example.mohammed.itunesapi.ui.classicList;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.ui.base.MvpView;

/**
 * Created by Mohammed on 02/10/2017.
 */

public interface iClassicListMvpView extends MvpView{

    void onFetchClassicCompleted(MusicList musicList);


}

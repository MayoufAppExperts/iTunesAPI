package com.example.mohammed.itunesapi.ui.popList;

import com.example.mohammed.itunesapi.network.model.MusicList;
import com.example.mohammed.itunesapi.ui.base.MvpView;

/**
 * Created by Mohammed on 01/10/2017.
 */

public interface iPopListMvpView extends MvpView {

    void onFetchPopCompleted(MusicList musicList);
}

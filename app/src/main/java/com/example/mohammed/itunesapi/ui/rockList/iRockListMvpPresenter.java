package com.example.mohammed.itunesapi.ui.rockList;

import com.example.mohammed.itunesapi.ui.base.MvpPresenter;

/**
 * Created by Mohammed on 01/10/2017.
 */

public interface iRockListMvpPresenter <V extends iRockListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}

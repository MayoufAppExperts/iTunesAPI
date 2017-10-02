package com.example.mohammed.itunesapi.ui.classicList;

import com.example.mohammed.itunesapi.ui.base.MvpPresenter;

/**
 * Created by Mohammed on 02/10/2017.
 */

public interface iClassicListMvpPresenter <V extends iClassicListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}

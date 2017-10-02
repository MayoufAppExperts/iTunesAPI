package com.example.mohammed.itunesapi.ui.popList;

import com.example.mohammed.itunesapi.ui.base.MvpPresenter;

/**
 * Created by Mohammed on 01/10/2017.
 */

public interface iPopListMvpPresenter<V extends iPopListMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
}

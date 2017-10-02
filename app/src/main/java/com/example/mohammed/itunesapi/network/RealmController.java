package com.example.mohammed.itunesapi.network;

import com.example.mohammed.itunesapi.network.model.MusicList;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by TheAppExperts on 22/09/2017.
 */

public class RealmController {
    Realm realm;

    public RealmController(Realm realm) {
        this.realm = realm;
    }
    public void saveCustomer(final MusicList musicList){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(musicList);
            }
        });
    }
    public ArrayList<MusicList> getCustomerList(){

        ArrayList<MusicList> customerModelsArrayList = new ArrayList<>();
        RealmResults<MusicList> realmResults = realm.where(MusicList.class).findAll();

        for (MusicList musicList:realmResults){
            customerModelsArrayList.add(musicList);
        }

        return customerModelsArrayList;
    }
}

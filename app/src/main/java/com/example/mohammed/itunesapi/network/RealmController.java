//package com.example.mohammed.itunesapi.network;
//
//import com.example.mohammed.itunesapi.network.model.MusicList;
//import com.example.mohammed.itunesapi.network.model.RealmDB;
//
//import java.util.ArrayList;
//
//import io.realm.Realm;
//import io.realm.RealmResults;
//
///**
// * Created by TheAppExperts on 22/09/2017.
// */
//
//public class RealmController {
//    Realm realm;
//
//    public RealmController(Realm realm) {
//        this.realm = realm;
//    }
//    public void saveMusic(final RealmDB realmDB){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealm(realmDB);
//            }
//        });
//    }
//    public ArrayList<RealmDB> getMusicRealmList(){
//
//        ArrayList<RealmDB> musicModelsArrayList = new ArrayList<>();
//        RealmResults<RealmDB> realmResults = realm.where(RealmDB.class).findAll();
//
//        for (RealmDB realmDB:realmResults){
//            musicModelsArrayList.add(realmDB);
//        }
//
//        return musicModelsArrayList;
//    }
//}

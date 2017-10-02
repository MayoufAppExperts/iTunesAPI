package com.example.mohammed.itunesapi;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mohammed on 01/10/2017.
 */

public class MyApp extends Application {


    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        //realmCreate();
    }


//    private void realmCreate() {
//        Realm.init(getApplicationContext());
//        RealmConfiguration realmConfiguration =new RealmConfiguration.Builder()
//                .name(Realm.DEFAULT_REALM_NAME)
//                .schemaVersion(3)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm.setDefaultConfiguration(realmConfiguration);
}
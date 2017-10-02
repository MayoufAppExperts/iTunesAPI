//package com.example.mohammed.itunesapi.network.model;
//
///**
// * Created by Mohammed on 30/09/2017.
// */
//import java.util.List;
//
//import com.example.mohammed.itunesapi.PopAdapter;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//import io.realm.RealmModel;
//import io.realm.RealmObject;
//
//public class RealmDB extends RealmObject {
//
//    @SerializedName("resultCount")
//    @Expose
//    private Integer resultCount;
//    @SerializedName("results")
//    @Expose
//    private List<Result> results = null;
//
//    public RealmDB(String name, String collectionName, String artistName) {
//    }
//
//    public Integer getResultCount() {
//        return resultCount;
//    }
//
//    public void setResultCount(Integer resultCount) {
//        this.resultCount = resultCount;
//    }
//
//    public List<Result> getResults() {
//        return results;
//    }
//
//    public void setResults(List<Result> results) {
//        this.results = results;
//    }
//
//}
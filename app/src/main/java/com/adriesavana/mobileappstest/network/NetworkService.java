package com.adriesavana.mobileappstest.network;

import com.adriesavana.mobileappstest.network.gson.GArea;
import com.adriesavana.mobileappstest.network.gson.GDetail;
import com.adriesavana.mobileappstest.network.gson.GList;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by adri on 4/26/16.
 */
public interface NetworkService {

    @GET("/test/location.json")
    Observable<List<GArea>> getLocation();


    @GET("/test/datalist.json")
    Observable<List<GList>> getDataList();

    @GET("/test/profile/{id}.json")
    Observable<GDetail> getDetail(@Path("id") String id);
}

package com.adriesavana.mobileappstest.interactor;

import com.adriesavana.mobileappstest.network.gson.GArea;
import com.adriesavana.mobileappstest.network.gson.GDetail;
import com.adriesavana.mobileappstest.network.gson.GList;

import java.util.List;

import rx.Observable;

/**
 * Created by adri on 8/8/16.
 */

public interface Interactor {


    Observable<List<GArea>> getLocation();

    Observable<List<GList>> getDataList();

    Observable<GDetail> getDetail(String id);
}

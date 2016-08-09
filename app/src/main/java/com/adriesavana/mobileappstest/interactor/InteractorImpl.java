package com.adriesavana.mobileappstest.interactor;

import com.adriesavana.mobileappstest.network.NetworkService;
import com.adriesavana.mobileappstest.network.gson.GArea;
import com.adriesavana.mobileappstest.network.gson.GDetail;
import com.adriesavana.mobileappstest.network.gson.GList;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by adri on 8/8/16.
 */

public class InteractorImpl implements Interactor {

    private NetworkService mNetworkManager;

    public InteractorImpl(NetworkService networkManager) {
        this.mNetworkManager = networkManager;
    }

    @Override
    public Observable<List<GArea>> getLocation() {
        return mNetworkManager.getLocation().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<GList>> getDataList() {
        return mNetworkManager.getDataList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    @Override
    public Observable<GDetail> getDetail(String id) {
        return mNetworkManager.getDetail(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}

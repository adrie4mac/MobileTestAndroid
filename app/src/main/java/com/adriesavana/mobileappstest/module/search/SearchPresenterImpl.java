package com.adriesavana.mobileappstest.module.search;

import com.adriesavana.mobileappstest.interactor.Interactor;
import com.adriesavana.mobileappstest.interactor.InteractorImpl;
import com.adriesavana.mobileappstest.network.NetworkManager;
import com.adriesavana.mobileappstest.network.gson.GArea;

import java.util.List;

import rx.Subscriber;

/**
 * Created by adri on 8/9/16.
 */

public class SearchPresenterImpl implements  SearchPresenter {

    private SearchView mView;
    private Interactor mInteractor;

    public SearchPresenterImpl(SearchView view) {
        mView = view;
        mInteractor = new InteractorImpl(NetworkManager.getInstance());
    }

    @Override
    public void loadData() {
        mView.showLoading();
        mInteractor.getLocation().subscribe(new Subscriber<List<GArea>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
            }

            @Override
            public void onNext(List<GArea> gAreas) {
                mView.hideLoading();
                mView.loadData(getStringArrayArea(gAreas));
            }
        });
    }

    private String[] getStringArrayArea(List<GArea> areas) {
        String[] array = new String[areas.size()];

        for (int i = 0; i < areas.size(); i++) {
            array[i] = areas.get(i).area + ", " + areas.get(i).city;
        }

        return array;
    }
}

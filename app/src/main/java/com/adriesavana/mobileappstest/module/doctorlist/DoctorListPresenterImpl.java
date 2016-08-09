package com.adriesavana.mobileappstest.module.doctorlist;

import com.adriesavana.mobileappstest.interactor.Interactor;
import com.adriesavana.mobileappstest.interactor.InteractorImpl;
import com.adriesavana.mobileappstest.network.NetworkManager;
import com.adriesavana.mobileappstest.network.gson.GList;

import java.util.List;

import rx.Subscriber;

/**
 * Created by adri on 8/8/16.
 */

public class DoctorListPresenterImpl implements DoctorListPresenter {

    private DoctorListView mView;
    private Interactor mInteractor;
    private List<GList> tempList;

    public DoctorListPresenterImpl(DoctorListView mView) {
        this.mView = mView;
        this.mInteractor = new InteractorImpl(NetworkManager.getInstance());
    }

    @Override
    public void getDataList() {
        mView.showLoading();
        mInteractor.getDataList().subscribe(new Subscriber<List<GList>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
            }

            @Override
            public void onNext(List<GList> gDetails) {
                tempList = gDetails;
                for (int i = 0; i < gDetails.size(); i++) {
                    mView.addDoctor(gDetails.get(i));
                }
                mView.hideLoading();
            }
        });
    }

    @Override
    public List<GList> getList() {
        return tempList;
    }

    @Override
    public int getSizeList() {
        return tempList == null ? -1 : tempList.size();
    }

    @Override
    public void addList() {
        if (tempList.size() > 0) {
            int loop = 10;
            if (tempList.size() < loop) loop = tempList.size()-1;
            for (int i = 0; i < loop; i++) {
                mView.addDoctor(tempList.get(i));
                tempList.remove(i);
            }
        }
    }
}

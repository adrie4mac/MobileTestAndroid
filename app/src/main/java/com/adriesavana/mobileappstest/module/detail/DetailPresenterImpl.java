package com.adriesavana.mobileappstest.module.detail;

import com.adriesavana.mobileappstest.interactor.Interactor;
import com.adriesavana.mobileappstest.interactor.InteractorImpl;
import com.adriesavana.mobileappstest.network.NetworkManager;
import com.adriesavana.mobileappstest.network.gson.GDetail;

import rx.Subscriber;

/**
 * Created by adri on 8/8/16.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private DetailView mView;
    private Interactor mInteractor;

    public DetailPresenterImpl(DetailView mView) {
        this.mView = mView;
        this.mInteractor = new InteractorImpl(NetworkManager.getInstance());
    }

    @Override
    public void getDoctorById(int id) {
        mInteractor.getDetail(String.valueOf(id)).subscribe(new Subscriber<GDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GDetail mDetail) {
                onSuccessLoadData(mDetail);
            }
        });
    }

    private void onSuccessLoadData(GDetail mDetail) {
        mView.setName("dr." + mDetail.name);
        mView.setDescription(mDetail.description);
        mView.setImage(mDetail.photo);
        mView.setLatLng(mDetail.area, mDetail.latitude, mDetail.longitute);
        mView.setRatingView(mDetail.recommendation);
        mView.setSechedule(mDetail.schedule);
        mView.setSpecialist(mDetail.speciality);
    }
}

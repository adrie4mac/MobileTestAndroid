package com.adriesavana.mobileappstest.module.doctorlist;

import com.adriesavana.mobileappstest.network.gson.GList;

/**
 * Created by adri on 8/8/16.
 */

public interface DoctorListView {

    void addDoctor(GList mDetails);

    void showLoading();

    void hideLoading();

    void setNextPage(boolean isActive);

}

package com.adriesavana.mobileappstest.module.doctorlist;

import com.adriesavana.mobileappstest.network.gson.GList;

import java.util.List;

/**
 * Created by adri on 8/8/16.
 */

public interface DoctorListPresenter {

    void getDataList();

    List<GList> getList();

    int getSizeList();

    void addList();
}

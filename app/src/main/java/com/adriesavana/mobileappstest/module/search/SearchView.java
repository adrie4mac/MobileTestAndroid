package com.adriesavana.mobileappstest.module.search;

/**
 * Created by adri on 8/9/16.
 */

public interface SearchView {

    void showLoading();

    void hideLoading();

    void loadData(String[] item);
}

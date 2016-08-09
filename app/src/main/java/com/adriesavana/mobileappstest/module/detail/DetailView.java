package com.adriesavana.mobileappstest.module.detail;

import com.adriesavana.mobileappstest.network.NetworkService;

/**
 * Created by adri on 8/8/16.
 */

public interface DetailView {

    void setRatingView(int recommendation);

    void setName(String name);

    void setImage(String imgUrl);

    void setSpecialist(String specialist);

    void setSechedule(String sechedule);

    void setDescription(String description);

    void setLatLng(String area, double var1, double var3);
}

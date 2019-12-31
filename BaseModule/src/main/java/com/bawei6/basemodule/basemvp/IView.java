package com.bawei6.basemodule.basemvp;

import androidx.lifecycle.LifecycleOwner;

public interface IView {

    void showLoading();
    void hideLoading();
    LifecycleOwner getowner();
}

package com.bawei6.basemodule.utils;

import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObserable {

    public static <T>  void doObserver(Observable<T> observable, BaseObserver<T> observer,LifecycleOwner owner){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
                .subscribe(observer);
    }
}

package com.bawei6.basemodule.basemvp;

abstract public class BasePresenter<V extends IView,M extends IModel> {

     V iView;
    M iModel;


    public void AttachView(V view){

    }

    public void DettachView(){
        if (iView!=null){
            iView=null;
        }
    }
}

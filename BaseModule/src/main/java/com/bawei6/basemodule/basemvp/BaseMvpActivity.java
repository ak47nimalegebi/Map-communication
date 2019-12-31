package com.bawei6.basemodule.basemvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseMvpActivity<P extends BasePresenter<IView,IModel>> extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Layoutid());
    }

    public  int Layoutid() {
        return 0;
    }
}

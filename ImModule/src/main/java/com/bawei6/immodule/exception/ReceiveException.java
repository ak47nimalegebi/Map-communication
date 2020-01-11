package com.bawei6.immodule.exception;

import com.bawei6.basemodule.utils.LogUtils;

public class ReceiveException extends Exception {

    public ReceiveException(String msg){
        super(msg);
        LogUtils.i(msg);
    }
}

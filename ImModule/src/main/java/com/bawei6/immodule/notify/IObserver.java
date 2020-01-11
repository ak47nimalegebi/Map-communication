package com.bawei6.notify;

import com.bawei6.immodule.entity.BaseMsg;

public interface IObserver {
    void nodify(BaseMsg msg);
}

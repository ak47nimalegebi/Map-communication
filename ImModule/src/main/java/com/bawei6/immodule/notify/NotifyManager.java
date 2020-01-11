package com.bawei6.notify;

import com.bawei6.immodule.entity.BaseMsg;

import java.util.ArrayList;
import java.util.List;

public class NotifyManager {
    private static NotifyManager instance=new NotifyManager();

    private NotifyManager() {
    }
    public static NotifyManager getInstance(){
        return instance;
    }
    private List<IObserver> list=new ArrayList<>();

    public void addObserver(IObserver observer){
        if(list.contains(observer)){
            return;
        }
        list.add(observer);
    }

    public void removeObserver(IObserver observer){
        if(list.contains(observer)){
            list.remove(observer);
        }
    }

    public void notifyAllObserver(BaseMsg msg){
        for (IObserver observer : list){
            observer.nodify(msg);
        }
    }
}

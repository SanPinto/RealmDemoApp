package com.realm.demo.realmmvpdemoapp;

/**
 * Created by sangeetha on 19/7/17.
 */

public interface BasePresenter<T> {
    void setView(T view);

    void clearView();

    void closeRealm();
}

package com.realm.demo.realmmvpdemoapp;

import android.app.Application;

import com.realm.demo.realmmvpdemoapp.data.DataBaseHelper;

/**
 * Created by sangeetha on 19/7/17.
 */

public class RealmDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
    }


    private void initRealmConfiguration() {
        DataBaseHelper.getInstance().initRealm(this);
    }


}

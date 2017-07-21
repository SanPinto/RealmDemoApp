package com.realm.demo.realmmvpdemoapp.data;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sangeetha on 20/7/17.
 */

public class DataBaseHelper {
    private static DataBaseHelper mDbHelper;

    private DataBaseHelper() {

    }

    public static DataBaseHelper getInstance() {
        if (mDbHelper == null) {
            mDbHelper = new DataBaseHelper();
        }
        return mDbHelper;
    }

    public void initRealm(Context context) {
        // Initialize Realm
        Realm.init(context);
        // configure Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public Realm getRealmInstance() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        return realm;
    }
}

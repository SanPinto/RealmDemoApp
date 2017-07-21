package com.realm.demo.realmmvpdemoapp.person;

import android.content.Context;

import com.realm.demo.realmmvpdemoapp.BasePresenter;
import com.realm.demo.realmmvpdemoapp.person.model.Person;

/**
 * Created by sangeetha on 19/7/17.
 */

public class PersonsContract {

    public interface PersonsPresenter extends BasePresenter<PersonsView> {

        void getUsersFromRealm();

        void onCreate(Context context);

        void showAddOrUpdatePersonDetailsDialog(Person person, int position);
    }

    public interface PersonsView {

        void notifyPersonsAdapter();

    }
}

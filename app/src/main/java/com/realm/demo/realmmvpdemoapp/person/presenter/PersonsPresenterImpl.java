package com.realm.demo.realmmvpdemoapp.person.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.realm.demo.realmmvpdemoapp.R;
import com.realm.demo.realmmvpdemoapp.data.DataBaseHelper;
import com.realm.demo.realmmvpdemoapp.person.PersonsContract;
import com.realm.demo.realmmvpdemoapp.person.model.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.R.id.message;

/**
 * Created by sangeetha on 19/7/17.
 */

public class PersonsPresenterImpl implements PersonsContract.PersonsPresenter {
    private PersonsContract.PersonsView mPersonsView;
    private Realm mRealm;
    private Context mContext;

    @Override
    public void setView(PersonsContract.PersonsView view) {
        mPersonsView = view;
    }

    @Override
    public void clearView() {
        mPersonsView = null;
    }

    @Override
    public void closeRealm() {

    }

    @Override
    public void getUsersFromRealm() {

    }

    @Override
    public void onCreate(Context context) {
        mContext = context;
        mRealm = DataBaseHelper.getInstance().getRealmInstance();
    }


    @Override
    public void showAddOrUpdatePersonDetailsDialog(Person person, int position) {
        android.support.v7.app.AlertDialog.Builder errorDialgoue = setErrorDialogue();
        setPersonDetailsDailogue(person, errorDialgoue);
    }

    private void setPersonDetailsDailogue(Person person, final android.support.v7.app.AlertDialog.Builder subDialogue) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View promptsView = inflater.inflate(R.layout.prompt_dialogue, null);
        final EditText personName, personEmail, personAddress, personAge;
        personName = (EditText) promptsView.findViewById(R.id.etAddPersonName);
        personEmail = (EditText) promptsView.findViewById(R.id.etAddPersonEmail);
        personAddress = (EditText) promptsView.findViewById(R.id.etAddPersonAddress);
        personAge = (EditText) promptsView.findViewById(R.id.etAddPersonAge);
        if (person != null) {
            personName.setText(person.name);
            personEmail.setText(person.email);
            personAddress.setText(person.address);
            personAge.setText(person.age);
        }

        //Warning Message Dialogue
        android.support.v7.app.AlertDialog.Builder personDetailDialogue =
                new android.support.v7.app.AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);
        personDetailDialogue.setView(promptsView);

        personDetailDialogue.setCancelable(false)
                .setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!TextUtils.isEmpty(personName.getText()) && !TextUtils.isEmpty(personEmail.getText()) && !TextUtils.isEmpty(personAddress.getText()) && !TextUtils.isEmpty(personAge.getText())) {
                            Person person = new Person();
                            person.name = personName.getText().toString();
                            person.email = personEmail.getText().toString();
                            person.address = personAddress.getText().toString();
                            person.age = (Integer.parseInt(personAge.getText().toString()));

//                            if (person == null)
//                                addDataToRealm(person);
//                            else
//                                updatePersonDetails(person, position, model.getId());

                            dialog.cancel();
                        } else {
                            subDialogue.show();
                        }
                    }
                })
                .setNegativeButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        final android.support.v7.app.AlertDialog dialog = personDetailDialogue.create();
        dialog.show();
    }

    private android.support.v7.app.AlertDialog.Builder setErrorDialogue() {
        //Warning Message Dialogue
        android.support.v7.app.AlertDialog.Builder subDialog =
                new android.support.v7.app.AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);

        subDialog.setMessage("Please enter all the details!!!");
        subDialog.setCancelable(false);
        subDialog.setPositiveButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dlg2, int which) {
                dlg2.cancel();
            }
        });
        return subDialog;
    }


    private void getAllUsers() {
        RealmResults<Person> results = mRealm.where(Person.class).findAll();

        /**
         * To perform any transaction on realm you must call beginTransaction() method to begin and commitTransaction() method to commit the transaction
         */
        mRealm.beginTransaction();
        List<Person> personsList = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            personsList.add(results.get(i));
        }

//        if(results.size() > 0)
//            id = mRealm.where(Person.class).max("id").intValue() + 1;
        mRealm.commitTransaction();
        mPersonsView.notifyPersonsAdapter();
    }
}

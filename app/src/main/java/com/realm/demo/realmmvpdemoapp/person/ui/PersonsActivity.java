package com.realm.demo.realmmvpdemoapp.person.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;


import com.realm.demo.realmmvpdemoapp.R;
import com.realm.demo.realmmvpdemoapp.person.PersonsContract;
import com.realm.demo.realmmvpdemoapp.person.model.Person;
import com.realm.demo.realmmvpdemoapp.person.presenter.PersonsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class PersonsActivity extends AppCompatActivity implements PersonsContract.PersonsView {

    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.fabAddPerson)
    public FloatingActionButton mFabAddPerson;

    private PersonsListAdapter mAdapter;
    private PersonsPresenterImpl mPresenter;
    private List<Person> mPersonsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initPersonsAdapter();
        initPresenter();
        showPersonsIfPresent();

    }

    private void showPersonsIfPresent() {
        mPresenter.getUsersFromRealm();
    }

    private void initPresenter() {
        mPresenter = new PersonsPresenterImpl();
        mPresenter.setView(this);
        mPresenter.onCreate(this);
    }


    @OnClick(R.id.fabAddPerson)
    public void addOrUpdatePersonDetailsDialog(View view) {
      mPresenter.showAddOrUpdatePersonDetailsDialog(null, -1);
    }


    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initPersonsAdapter() {
        mAdapter = new PersonsListAdapter(mPersonsList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.clearView();
    }

    @Override
    public void notifyPersonsAdapter() {
       if(mAdapter != null) {
           mAdapter.notifyDataSetChanged();
       }
    }
}

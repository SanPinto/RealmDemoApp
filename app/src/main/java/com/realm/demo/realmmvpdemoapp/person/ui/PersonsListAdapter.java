package com.realm.demo.realmmvpdemoapp.person.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realm.demo.realmmvpdemoapp.R;
import com.realm.demo.realmmvpdemoapp.person.model.Person;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sangeetha on 19/7/17.
 */

class PersonsListAdapter extends RecyclerView.Adapter<PersonsListAdapter.ViewHolder> {
    private OnBookClickListener mOnBookClickListener;
    private List<Person> mPersonsList;

    public PersonsListAdapter(List<Person> personsList) {
        mPersonsList = personsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnBookClickListener {
        void onBookClick(int id);
    }

    public void setOnBookClickListener(final OnBookClickListener onBookClickListener) {
        mOnBookClickListener = onBookClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

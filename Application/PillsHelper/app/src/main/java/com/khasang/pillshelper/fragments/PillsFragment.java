package com.khasang.pillshelper.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Drug;

import java.util.List;

public class PillsFragment extends Fragment {


    public PillsFragment() {
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText search_field;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pills, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drug_list);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        PillsDBHelper.init(view.getContext());
        mAdapter = new DrugAdapter(PillsDBHelper.getInstance().getAllDrugs());
        mRecyclerView.setAdapter(mAdapter);

        search_field = (EditText) view.findViewById(R.id.search_field);
        search_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter = new DrugAdapter(PillsDBHelper.getInstance().findDrugsByName(String.valueOf(search_field.getText())));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }
}

class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {
    private List<Drug> drugs;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTextView;
        private Drug item;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            mTextView.setOnClickListener(this);
        }

        public void setItem(Drug item){
            this.item = item;
            mTextView.setText(item.getName());
        }

        @Override
        public void onClick(View v) {
            //TODO launch drug activity to show detail info
        }
    }

    public DrugAdapter(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public DrugAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.drug_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(drugs.get(position));
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }
}
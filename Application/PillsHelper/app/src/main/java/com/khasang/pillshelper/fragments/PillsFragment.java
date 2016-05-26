package com.khasang.pillshelper.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return view;
    }
}

class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {
    private List<Drug> drugs;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public DrugAdapter(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public DrugAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(drugs.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }
}

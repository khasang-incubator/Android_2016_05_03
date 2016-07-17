package com.khasang.pillshelper.fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.khasang.pillshelper.DrugActivity;
import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class PillsFragment extends android.support.v4.app.Fragment {


    public PillsFragment() {
    }

    private RecyclerView mRecyclerView;
    private DrugAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText search_field;
    private static Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pills, container, false);

        context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drug_list);

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new DrugAdapter(PillsDBHelper.getInstance().getAllDrugs());
        mRecyclerView.setAdapter(mAdapter);

        search_field = (EditText) view.findViewById(R.id.search_field);
        search_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentText = String.valueOf(search_field.getText());
                mAdapter.getFilter().filter(currentText);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    static class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> implements Filterable{
        private List<Drug> drugs;
        private List<Drug> allDrugs;

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    List<Drug> filteredDrugs = new ArrayList<>();
                    for (Drug drug : allDrugs) {
                        if (drug.getName().toUpperCase().contains(String.valueOf(constraint).toUpperCase())) {
                            filteredDrugs.add(drug);
                        }
                    }
                    FilterResults results = new FilterResults();
                    results.values = filteredDrugs;
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    drugs = (List<Drug>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

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
                Intent intent = new Intent(context, DrugActivity.class);
                intent.putExtra(DrugActivity.DRUG_ID, item.getId());
                context.startActivity(intent);
            }
        }

        public DrugAdapter(List<Drug> drugs) {
            this.drugs = drugs;
            this.allDrugs = drugs;
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
}


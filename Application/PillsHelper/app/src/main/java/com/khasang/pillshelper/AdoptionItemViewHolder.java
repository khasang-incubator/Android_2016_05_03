package com.khasang.pillshelper;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

public class AdoptionItemViewHolder extends ChildViewHolder {

    TextView timeToTake;
    TextView drugName;

    public AdoptionItemViewHolder(View itemView) {
        super(itemView);
        timeToTake = (TextView) itemView.findViewById(R.id.tv_time_to_take);
        drugName = (TextView) itemView.findViewById(R.id.tv_drug_name);
    }
}

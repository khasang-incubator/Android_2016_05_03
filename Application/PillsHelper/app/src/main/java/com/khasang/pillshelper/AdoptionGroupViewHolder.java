package com.khasang.pillshelper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

public class AdoptionGroupViewHolder extends ParentViewHolder{

    ImageView image;
    TextView adoptionGroupName;

    public AdoptionGroupViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.grop_image);
        adoptionGroupName = (TextView) itemView.findViewById(R.id.adoption_group_name_item);
    }
}

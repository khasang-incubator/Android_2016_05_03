package com.khasang.pillshelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.khasang.pillshelper.db.model.Drug;

public class DrugActivity extends AppCompatActivity {

    public static final String DRUG_ID = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fillDrugDescription();
    }

    private void fillDrugDescription() {
        Drug drug;
        Intent income_intent = getIntent();
        if (income_intent.hasExtra(DRUG_ID)) {
            int drug_id = income_intent.getIntExtra(DRUG_ID, 0);
            drug = new Drug(drug_id);
        } else return;

        TextView drugDescr = (TextView) findViewById(R.id.drug_description);

        String data = "";

        // TODO: 30.05.16 вынести в asynctask

        setTitle(drug.getName());

        data +=
                "<header><b>" + drug.getFirm() + "</b></header>" +
                        "<article><p>" + drug.getComposition() +  "</p>" +
                        "<p>" + drug.getContras() +  "</p>" +
                        "<p>" + drug.getDosage() +  "</p>" +
                        "<p>" + drug.getInteraction() +  "</p>" +
                        "<p>" + drug.getOverdose() +  "</p>" +
                        "<p>" + drug.getPharmAction() +  "</p>" +
                        "<p>" + drug.getPharmGroup() +  "</p>" +
                        "<p>" + drug.getSideEffect() +  "</p>" +
                        "<p>" + drug.getSpecial() +  "</p>" +
                        "<p>" + drug.getUsage() +  "</p>" +
                        "<p>" + drug.getStorage() + "</p></article>";

        drugDescr.setText(Html.fromHtml(data));

        //if no HTML - to use TextUtils.EllipsizeCallback

    }
}

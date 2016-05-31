package com.khasang.pillshelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Drug;

import org.w3c.dom.Text;

import java.security.PrivateKey;

public class DrugActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Drug Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.khasang.pillshelper/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Drug Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.khasang.pillshelper/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

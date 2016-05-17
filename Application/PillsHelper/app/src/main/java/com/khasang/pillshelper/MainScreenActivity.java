package com.khasang.pillshelper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.khasang.pillshelper.db.PillsDBHelper;

import java.util.ArrayList;

public class MainScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrugAdapter adapter;
    private RecyclerView recyclerView;

    private ArrayList<Drug> drugs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filter by criteria \"алка\"", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                adapter.getFilter().filter("алка");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new DrugAdapter(drugs);
        recyclerView.setAdapter(adapter);

        initDB();
    }

    private void initDB() {
        PillsDBHelper.getInstance(this)
                .runRequestAsync(
                        PillsDBHelper.INITIALIZE_DB,
                        new PillsDBHelper.Listener<Boolean>() {
                            @Override
                            public void onHelperAnswerReceived(int code, Boolean response) {
                                switch (code) {
                                    // TODO: 16.05.16 прописать поведение после инициализации БД
                                    case PillsDBHelper.RESULT_OK:
                                        if (response.booleanValue()) {
                                            Log.i("DRUGS", "Initializing Success");
                                        } else {
                                            Log.i("DRUGS", "Initializing Failed");
                                        }
                                        break;
                                    default:
                                        Log.i("DRUGS", "Something Wrong");
                                        break;
                                }
                            }
                        });
    }

    private void showAllDrugs() {
        drugs = new ArrayList<>();
        PillsDBHelper.getInstance(this)
                .runRequestAsync(
                        PillsDBHelper.GET_ALL_DRUGS,
                        new PillsDBHelper.Listener<ArrayList<Drug>>() {
                            @Override
                            public void onHelperAnswerReceived(int code, ArrayList<Drug> response) {
                                switch (code) {
                                    case PillsDBHelper.RESULT_OK:
                                        ArrayList<Drug> localDrugs = ((DrugAdapter) recyclerView.getAdapter()).mDrugs;
                                        localDrugs.clear();
                                        for (Drug drug : response) {
                                            localDrugs.add(drug);
                                        }

                                        adapter.notifyDataSetChanged();

                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            showAllDrugs();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DrugHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public DrugHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void bindView(Drug drug) {
            textView.setText(drug.toString());
        }
    }

    private class DrugAdapter extends RecyclerView.Adapter<DrugHolder> implements Filterable {
        private ArrayList<Drug> mDrugs;
        private ArrayList<Drug> originCollection;

        public DrugAdapter(ArrayList<Drug> drugs) {
            mDrugs = drugs;
            originCollection = drugs;
        }

        @Override
        public DrugHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainScreenActivity.this);
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

            return new DrugHolder(view);
        }

        @Override
        public void onBindViewHolder(DrugHolder holder, int position) {
            holder.bindView(mDrugs.get(position));
        }

        @Override
        public int getItemCount() {
            return mDrugs.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    ArrayList<Drug> filteredDrugs = new ArrayList<>();
                    for (Drug drug : originCollection) {
                        if (drug.getName().toLowerCase().contains(constraint)) {
                            filteredDrugs.add(drug);
                        }
                    }

                    FilterResults results = new FilterResults();
                    results.values = filteredDrugs;

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mDrugs = (ArrayList<Drug>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}

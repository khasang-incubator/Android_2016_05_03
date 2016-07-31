package com.khasang.pillshelper;


import android.app.AlarmManager;
import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.fragments.AllCourseFragment;
import com.khasang.pillshelper.fragments.MainFragment;
import com.khasang.pillshelper.fragments.NewCourseFragment;
import com.khasang.pillshelper.fragments.PillsFragment;
import com.khasang.pillshelper.notification.Job;
import com.khasang.pillshelper.notification.NotificationHelper;
import com.khasang.pillshelper.notification.NotificationPublisher;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.List;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AllCourseFragment frAllCorse;
    private MainFragment frMain;
    private PillsFragment frAllPills;
    private int currentPosition = 0;

    private Menu navigation_view_menu;

    void initMainFragment(){
        frMain.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, frMain).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initDB();

        frAllCorse = new AllCourseFragment();
        frMain = new MainFragment();
        frAllPills = new PillsFragment();

        /*
        if (savedInstanceState == null) {
            frMain.setArguments(getIntent().getExtras());
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.container, frMain).commit();
        }
        */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_fab_base);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigation_view_menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void initDB(){
        new AsyncTask<Context, Void, Void>(){
            private Context context;
            @Override
            protected Void doInBackground(Context... params) {
                this.context = params[0];
                PillsDBHelper.init(params[0]);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                MenuItem allPillsItem = navigation_view_menu.findItem(R.id.all_pills);
                allPillsItem.setEnabled(true);
                NotificationHelper.init(context);
                PillsDBHelper.getInstance().fillDBTest();
                initMainFragment();
            }
        }.execute(this);
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
        getMenuInflater().inflate(R.menu.drawer, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.new_course) {
            //ft.replace(R.id.container, frNewCourse);
            startActivity(new Intent(this, AddCourseActivity.class));

        } else if (id == R.id.all_course) {
            ft.replace(R.id.container, frAllCorse);

        } else if (id == R.id.all_pills) {
            ft.replace(R.id.container, frAllPills);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickMainFragment(View view) {

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, frMain);
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }
}

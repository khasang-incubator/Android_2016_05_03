package com.github.miki48ru.pillshelper;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.miki48ru.pillshelper.fragments.AllCourseFragment;
import com.github.miki48ru.pillshelper.fragments.CurrentCourseFragment;
import com.github.miki48ru.pillshelper.fragments.MainFragment;
import com.github.miki48ru.pillshelper.fragments.NewCourseFragment;
import com.github.miki48ru.pillshelper.fragments.PillsFragment;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private NewCourseFragment frNewCourse;
    private AllCourseFragment frAllCorse;
    private CurrentCourseFragment frCurrentCourse;
    private MainFragment frMain;
    private PillsFragment frAllPills;
    private int currentPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        frNewCourse = new NewCourseFragment();
        frAllCorse = new AllCourseFragment();
        frCurrentCourse = new CurrentCourseFragment();
        frMain = new MainFragment();
        frAllPills = new PillsFragment();
        if (savedInstanceState == null) {
        frMain.setArguments(getIntent().getExtras());
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

               fragmentTransaction.add(R.id.container, frMain).commit();
        }


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
        navigationView.setNavigationItemSelectedListener(this);


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

        @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        if (id == R.id.new_course) {
            ft.replace(R.id.container, frNewCourse);

        } else if (id == R.id.all_course) {
            ft.replace(R.id.container, frAllCorse);

        } else if (id == R.id.current_course) {
            ft.replace(R.id.container, frCurrentCourse);

        } else if (id == R.id.all_pills) {
            ft.replace(R.id.container, frAllCorse);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

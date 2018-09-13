package net.husnilkamil.kamusindo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.husnilkamil.kamusindo.fragments.EnglishFragment;
import net.husnilkamil.kamusindo.fragments.IndoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EnglishFragment engFragment;
    IndoFragment indFragment;
    AppConfig appConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appConfig = new AppConfig(this);
        if(appConfig.getFirstRun()){
            doPreloadData();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        indFragment = new IndoFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_kamus, indFragment)
                .commit();
    }

    private void doPreloadData() {
        Intent preloadIntent = new Intent(this, PreloadActivity.class);
        startActivity(preloadIntent);
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

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction;
        switch (id){
            case R.id.nav_indo_eng:

                if(indFragment == null){
                    indFragment = new IndoFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_kamus, indFragment)
                        .commit();

                break;
            case R.id.nav_eng_indo:

                if(engFragment == null){
                    engFragment = new EnglishFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_kamus, engFragment)
                        .commit();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

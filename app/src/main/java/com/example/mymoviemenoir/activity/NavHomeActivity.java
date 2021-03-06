package com.example.mymoviemenoir.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mymoviemenoir.R;
import com.example.mymoviemenoir.fragment.HomeFragment;
import com.example.mymoviemenoir.fragment.MapsFragment;
import com.example.mymoviemenoir.fragment.MemoirFragment;
import com.example.mymoviemenoir.fragment.ReportFragment;
import com.example.mymoviemenoir.fragment.SearchFragment;
import com.example.mymoviemenoir.fragment.WatchlistFragment;
import com.google.android.material.navigation.NavigationView;

public class NavHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);
        //Add the user to shared preference
        Intent intent = this.getIntent();



        //Add the tool bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nv);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //show navigation drawer icon on the top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);


        if(intent.hasExtra("start")){
            replaceFragment(new WatchlistFragment());
        }else{
            replaceFragment(new HomeFragment());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    private void replaceFragment(Fragment nextFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, nextFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            //Home fragment
            case R.id.homeScreen:
                replaceFragment(new HomeFragment());
                break;
            //Search fragment
            case R.id.movieSearchScreen:
                replaceFragment(new SearchFragment());
                break;
            //Memoir fragment
            case R.id.movieMenoirScreen:
                replaceFragment(new MemoirFragment());
                break;
            //Watchlist fragment
            case R.id.watchlistScreen:
                replaceFragment(new WatchlistFragment());
                break;
            //Reports fragment
            case R.id.reportsScreen:
                replaceFragment(new ReportFragment());
                break;
            //Maps fragment
            case R.id.mapsScreen:
                replaceFragment(new MapsFragment());
        }

        //Closes the drawer after selection
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                replaceFragment(new MapsFragment());
            }
        }
    }
}

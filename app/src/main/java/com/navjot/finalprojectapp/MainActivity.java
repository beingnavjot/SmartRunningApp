package com.navjot.finalprojectapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.navjot.finalprojectapp.viewcontroller.TechCrunchNewsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;

            switch (item.getItemId()) {

              //  case R.id.navigation_news:
                    //mTextMessage.setText(R.string.title_newsFeed);
                    //return true;
                  // Intent intentnews = new Intent(MainActivity.this, TechCrunchNewsActivity.class);
                 //   startActivity(intentnews);
                 //  finish();
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                   // return true;
                  //  selectedFragment= new MapsActivity();
                   // Intent intentt = new Intent(MainActivity.this, MapsActivity.class);
                   // startActivity(intentt);
                    //finish();
              //     selectedFragment = new HomeFragment();

                   break;
                case R.id.navigation_plans:
                   // mTextMessage.setText(R.string.title_plans);
                   // return true;
                    selectedFragment = new PlanFragment();
                    break;
                case R.id.navigation_profile:
                   mTextMessage.setText(R.string.title_profile);
                    return true;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
       mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

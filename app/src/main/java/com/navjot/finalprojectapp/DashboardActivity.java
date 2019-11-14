package com.navjot.finalprojectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

//import com.navjot.finalprojectapp.Running.RunningActivity;
import com.navjot.finalprojectapp.viewcontroller.TechCrunchNewsActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView start,newsFeed,plan,profile,bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //define cards
        start=(CardView) findViewById(R.id.startRunning);
        newsFeed=(CardView) findViewById(R.id.newsFeed);
        plan=(CardView) findViewById(R.id.plan);
        profile=(CardView) findViewById(R.id.profile);
        bmi=(CardView) findViewById(R.id.bmi);

        //add click listener
        start.setOnClickListener(this);
        newsFeed.setOnClickListener(this);
        plan.setOnClickListener(this);
        profile.setOnClickListener(this);
        bmi.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId())
        {
            case R.id.startRunning : i = new Intent(this, MapsActivity.class);
            startActivity(i);
                break;
            case R.id.newsFeed : i = new Intent(this, TechCrunchNewsActivity.class);
                startActivity(i);
                break;
            case R.id.plan : i = new Intent(this,PlanActivity.class);
                startActivity(i);
                break;
            case R.id.profile : i = new Intent(this,ProfileActivity.class);
                startActivity(i);
                break;
            case R.id.bmi : i = new Intent(this,BMIActivity.class);
                startActivity(i);
                break;

            default:break;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
       // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();


        if(id==R.id.menuItem2){
            Intent intent = new Intent(DashboardActivity.this,NewNotificationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

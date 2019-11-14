package com.navjot.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.navjot.finalprojectapp.viewcontroller.TechCrunchNewsActivity;

public class PlanActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView plan1,plan2,plan3,plan4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        getSupportActionBar().setTitle("Workout Plans");
        Intent rcv= getIntent();



        //define cards
        plan1=(CardView) findViewById(R.id.plan1);
        plan2=(CardView) findViewById(R.id.plan2);
        plan3=(CardView) findViewById(R.id.plan3);
        plan4=(CardView) findViewById(R.id.plan4);

        //add click listener
        plan1.setOnClickListener(this);
        plan2.setOnClickListener(this);
        plan3.setOnClickListener(this);
        plan4.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId())
        {
            case R.id.plan1 : i = new Intent(this,PlanOneActivity.class);
                startActivity(i);
                break;
            case R.id.plan2 : i = new Intent(this, PlanTwoActivity.class);
                startActivity(i);
                break;
            case R.id.plan3 : i = new Intent(this,PlanThreeActivity.class);
                startActivity(i);
                break;
            case R.id.plan4 : i = new Intent(this,PlanFourActivity.class);
                startActivity(i);
                break;

            default:break;

        }

    }
}

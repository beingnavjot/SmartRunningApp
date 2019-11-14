package com.navjot.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

     private EditText height,weight;
     private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        getSupportActionBar().hide();
        Intent rcv= getIntent();

        height=findViewById(R.id.etxtHeight);
        weight=findViewById(R.id.etxtWeigth);
        result=findViewById(R.id.txtViewResult);
    }

    public void calculateBMI(View v)
    {
        String heightstr = height.getText().toString();
        String weightstr = weight.getText().toString();

        if(heightstr != null && !"".equals(heightstr)
        && weightstr !=null && !"".equals(weightstr)){

            float heightValue= Float.parseFloat(heightstr) /100;
            float weightValue= Float.parseFloat(weightstr) ;

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void  displayBMI(float bmi){

        String bmiLabel="";

        if (Float.compare(bmi,15f) > 0 && Float.compare(bmi, 16f) <= 0){
            bmiLabel="Severely Underweight";
        }
        if (Float.compare(bmi,16f) > 0 && Float.compare(bmi, 18.5f) <= 0){
            bmiLabel="Underweight";
        }
        if (Float.compare(bmi,18.5f) > 0 && Float.compare(bmi, 25f) <= 0){
            bmiLabel="normal";
        }
        if (Float.compare(bmi,25f) > 0 && Float.compare(bmi, 30f) <= 0){
            bmiLabel="Overweight";
        }
        if (Float.compare(bmi,30f) > 0 && Float.compare(bmi, 35f) <= 0){
            bmiLabel="Obese class I";
        }
        if (Float.compare(bmi,35f) > 0 && Float.compare(bmi, 40f) <= 0){
            bmiLabel="Obese class II";
        }
        if (Float.compare(bmi,40f) > 0 && Float.compare(bmi, 50f) <= 0){
            bmiLabel="Obese class III";
        }


        bmiLabel = bmi + "\n\n" + bmiLabel;

    result.setText(bmiLabel);
    }

}

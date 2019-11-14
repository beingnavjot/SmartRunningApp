package com.navjot.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.navjot.finalprojectapp.model.CountryData;

public class AuthenticationActivity extends AppCompatActivity {

    Spinner spinner;
    private EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide();

        spinner =findViewById(R.id.spinnerCountry);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText =findViewById(R.id.editTextMobile);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number=editText.getText().toString().trim();

                if(number.isEmpty()|| number.length() < 10)
                {
                    editText.setError("Enter valid Mobile Number");
                    editText.requestFocus();
                    return;
                }
                String phoneNumber ="+"+ code + number;

                Intent intent = new Intent(AuthenticationActivity.this,OtpActivity.class);
                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){  //if user is already logged in ,, do not start the authentication activity and start app from DashboardActivity

            Intent intent=new Intent(this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  |  Intent.FLAG_ACTIVITY_CLEAR_TASK); //will clear everything from the stack and start new activity  ie. On clicking back button , Activity will not go back to the login screen
            startActivity(intent);

        }
    }
}

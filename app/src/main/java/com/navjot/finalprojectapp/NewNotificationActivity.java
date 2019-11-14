package com.navjot.finalprojectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class NewNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notification);

        findViewById(R.id.buttonEnableNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(this,"Daily Notifications Enabled",Toast.LENGTH_SHORT).show();
                // Toast.makeText(this,"Enabled",Toast.LENGTH_LONG).show();
                Calendar calendar = Calendar.getInstance();


                calendar.set(Calendar.HOUR_OF_DAY,15);
                calendar.set(Calendar.MINUTE,34);



                Intent intent = new Intent(getApplicationContext(), NewNotificationReciever.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                //Indicates that the pending intent can be updated in the future

                AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                // RTC_WAKEUP ensures that alarm will be triggered even if the phone goes to sleep mode

            }
        });
    }
}

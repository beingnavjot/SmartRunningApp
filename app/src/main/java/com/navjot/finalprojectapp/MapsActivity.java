package com.navjot.finalprojectapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener {

    final static int PERMISSION_ALL = 1;
    final static String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_FINE_LOCATION};

    private GoogleMap mMap;

   // FusedLocationProviderClient client;
   // LocationCallback locationCallback;
  //  double latitude,longitude;
  //  double end_latitude,getEnd_longitude;
    LocationManager locationManager;
    MarkerOptions no;
    Marker marker;

    private Button stopButton;
    private Button startButton;
    private Chronometer chronometer;




    private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;



    private double totalDistance = 0.0D;
    private long totalTime = 0L;

    private boolean mBug23937Checked = false;
    private long mBug23937Delta = 0;

    private TextView distanceValue;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



      timerValue = (TextView) findViewById(R.id.timerValue);
     //   distanceValue = (TextView) findViewById(R.id.distanceValue);


       timerValue.setText(getString(R.string.time) + ": " + "00:00");
//     distanceValue.setText(getString(R.string.distance) + ": " + "00:00 " + getString(R.string.unit_km));

     //   startButton = (Button) findViewById(R.id.startButton);
      //  stopButton = (Button) findViewById(R.id.stopButton);


        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.start();
            }
        });


        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        Intent rcv = getIntent();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // This is a Request to Google Server to fetch Google Map Images in the background
        mapFragment.getMapAsync(this);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        no = new MarkerOptions().position(new LatLng(0,0)).title("My Current Location");

        if (Build.VERSION.SDK_INT >=23 && !isPermissionGranted()){
            requestPermissions(PERMISSIONS,PERMISSION_ALL);
        }else{
            requestLocation();
        }
       if (!isLocationEnabled()){
           showAlert(1);
       }

    }


    // onMapReady will be executed when we have got the response from Google Server
    @Override
    public void onMapReady(GoogleMap googleMap) { // Reference Variable googleMap holds HashCode of GoogleMap Object

        mMap = googleMap;
        marker = mMap.addMarker(no);


    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng myCoordinates = new LatLng(location.getLatitude(),location.getLongitude());
        marker.setPosition(myCoordinates);
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates ,16));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myCoordinates, 16));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

      //  mMap.setTrafficEnabled(true);

        //mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public  void requestLocation(){

        Criteria criteria=new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        String provider = locationManager.getBestProvider(criteria,true);
        locationManager.requestLocationUpdates(provider,2000,10,this);
    }


    private boolean isLocationEnabled(){
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    private boolean isPermissionGranted(){

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)  {
            Log.v("mylog", "Permission is granted");
            return true;
        }else{
            Log.v("mylog", "Permission not granted");
            return false;
        }

    }


    private void showAlert(final int status){
        String message,title, btnText;
        if (status == 1){
            message="Go in your settings and Enable Location Permission";
            title="Enable Location";
            btnText="Location Settings";
        }else{
            message="Allow this app to access Location";
            title="Access Permission";
            btnText="Grant";
        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        if (status ==1){
                            Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(myIntent);
                        }else{
                            requestPermissions(PERMISSIONS,PERMISSION_ALL);
                        }

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        dialog.show();
    }


}

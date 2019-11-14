//package com.navjot.finalprojectapp;
//import android.content.Intent;
//
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//import android.os.Handler;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentActivity;
//
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//import java.util.List;
//
//public class HomeFragment extends FragmentActivity implements OnMapReadyCallback {
//
//
//    private GoogleMap mMap;
//
//    FusedLocationProviderClient client;
//    LocationCallback locationCallback;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//
//        // This is a Request to Google Server to fetch Google Map Images in the background
//        mapFragment.getMapAsync(this);
//
//
//        // 1. Create Location Request
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(5000); // After every 10 secs
//        locationRequest.setFastestInterval(4000);   // Fastest Interval
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//
//
//
//        // 2. LocationCallback : Handles Fetched Location from Google APIs
//        locationCallback = new LocationCallback(){
//            @Override
//            public void onLocationResult(LocationResult locationResult) {
//
//                Location location = locationResult.getLocations().get(0);
//
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//
//                float speed = location.getSpeed(); // mtrs per sec
//
//                try{
//
//                    Geocoder geocoder = new Geocoder(HomeFragment.this);
//                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//
//                    Address address = addresses.get(0);
//                    String adrsLine = address.getAddressLine(address.getMaxAddressLineIndex());
//
//                    Toast.makeText(HomeFragment.this, adrsLine+"\n"+latitude+" - "+longitude+" \n" + speed, Toast.LENGTH_LONG).show();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        // 3. Use FusedLocationProviderClient to request Locations
//        client = LocationServices.getFusedLocationProviderClient(this);
//        client.requestLocationUpdates(locationRequest, locationCallback, null);
//
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        mMap = googleMap;
//
//
//        // FusedLocationProviderClient API will give us the last known location of User from GPS :)
//        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
//
//        // 2 Permissions ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION must be in Manifest
//        // Make Sure you grant permissions to your application while executing it :)
//        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if(location!=null){
//
//                    double latitude = location.getLatitude();
//                    double longitude = location.getLongitude();
//
//                    LatLng myLocation = new LatLng(latitude,longitude);
//
//
//                    // Geocoding
//                    // Till now we have obtained Mathematical Numbers i.e. Latitude and Longitude
//
//                    // Reverse Geocoding : Obtain Address from Latitude and Longitude
//
//                    try{
//
//                        Geocoder geocoder = new Geocoder(HomeFragment.this);
//                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//
//                        Address address = addresses.get(0);
//                        String adrsLine = address.getAddressLine(address.getMaxAddressLineIndex());
//
//                        mMap.addMarker(new MarkerOptions().position(myLocation).title(adrsLine).snippet("Location: "+latitude+" "+longitude));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 16));
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//
//
//                }else{
//                    Toast.makeText(HomeFragment.this, "Location Not Available", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }
//
//
//@Override
//protected void onDestroy() {
//        super.onDestroy();
//        client.removeLocationUpdates(locationCallback); // No More Location Tracking
//        }
//}

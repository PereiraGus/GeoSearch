package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.GnssAntennaInfo;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class selectionMap extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
fetchAdress.OnTaskCompleted{
    private static final String MAPS_URL = "https://www.google.com.br/maps/place";

    private static final String PREFERENCES_NAME = "com.example.android.fetchLocation";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";

    private String lastLatitude;
    private String lastLongitude;
    private String lastAddress;

    private boolean mTrackingLocation;

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    private BottomNavigationView nav;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_map);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        nav = (BottomNavigationView) findViewById(R.id.bottomNav);
        nav.setSelectedItemId(R.id.btnBarMap);
        nav.setOnNavigationItemSelectedListener(this);

        mLocationCallback = new LocationCallback() {
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (mTrackingLocation) {
                    new fetchAdress(getApplicationContext(), selectionMap.this::onTaskCompleted)
                            .execute(locationResult.getLastLocation());
                }
            }
        };
        begin();
        createMapUrl();
    }

    public void onLocationResult (@NonNull LocationResult locationResult){
            if (mTrackingLocation) {
                new fetchAdress(getApplicationContext(), selectionMap.this::onTaskCompleted)
                        .execute(locationResult.getLastLocation());
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    begin();
                }else{
                    Toast.makeText(this, R.string.mapPermissionDenied, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public LocationRequest getLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        return locationRequest;
    }

    private void begin(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }else{
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if(location!= null){
                        Geocoder geocoder = new Geocoder(selectionMap.this, Locale.getDefault());
                        try{
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            addresses.get(0).getCountryName();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void stop(){
        if(mTrackingLocation){
            mTrackingLocation = false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.home:
                Intent intentH = new Intent(getApplicationContext(), mainMenu.class);
                startActivity(intentH);
                return true;
            case R.id.map:
                return true;
            case R.id.prof:
                Intent intentP = new Intent(getApplicationContext(), profileActivity.class);
                startActivity(intentP);
                return true;
        }
        return false;
    }
    @Override
    public void onTaskCompleted(String[] result){
        if(mTrackingLocation){
            lastAddress = result[0];
            lastLatitude = result[1];
            lastLongitude = result[2];
        }
    }

    public void createMapUrl(){
        WebView webViewMap = (WebView) findViewById(R.id.webViewMap);
        WebSettings mapSttgs = webViewMap.getSettings();
        mapSttgs.setJavaScriptEnabled(true);
        Uri buildUri = Uri.parse(MAPS_URL).buildUpon()
            .appendPath(lastLatitude + lastLongitude)
                .build();
        webViewMap.loadUrl(buildUri.toString());
    }
}
package com.example.maulikjagtap.googlemap;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng  user1=new LatLng(26.878388, 75.809351);
        googleMap.addMarker(new MarkerOptions().position(user1)
                .title("meet"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(user1));
        LatLng  user2=new LatLng(26.878355, 75.806210);
        googleMap.addMarker(new MarkerOptions().position(user2)
                .title("raj"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(user2));
        LatLng  user3=new LatLng(26.877379, 75.806148);
        googleMap.addMarker(new MarkerOptions().position(user3)
                .title("jay"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(user3));

        LatLng your = new LatLng(
                26.877421,75.811655);

        googleMap.addMarker(new MarkerOptions().position(your)
                .title("your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(your));
          googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
              @Override
              public boolean onMarkerClick(Marker marker) {
                  startActivity(new Intent(getBaseContext(),ChatSystem.class));
                  return true;
              }
          });



    }
}

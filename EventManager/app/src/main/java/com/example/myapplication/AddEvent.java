package com.example.myapplication;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddEvent extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    Marker marker;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        SupportMapFragment mapFrag = (SupportMapFragment)
                getSupportFragmentManager().
                        findFragmentById(R.id.mapAdd);
        mapFrag.getMapAsync(this);

        if(mMap != null) {

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick (LatLng latLng){
                    Geocoder geocoder =
                            new Geocoder(AddEvent.this);
                    List<Address> list;
                    try {
                        list = geocoder.getFromLocation(latLng.latitude,
                                latLng.longitude, 1);
                    } catch (IOException e) {
                        return;
                    }
                    Address address = list.get(0);
                    if (marker != null) {
                        marker.remove();
                    }

                    MarkerOptions options = new MarkerOptions()
                            .title(address.getLocality())
                            .position(new LatLng(latLng.latitude,
                                    latLng.longitude));
                    marker = mMap.addMarker(options);
                }
            });
            Button Addbtn = findViewById(R.id.Addbutton);
            final EditText editText = findViewById(R.id.editText2);
            final EditText editText1 = findViewById(R.id.editText3);

            Addbtn.setOnClickListener((v)->{
                    MainActivity ma = new MainActivity();
                    MainActivity.setEvents(new Event("sdf", "wefda", marker.getPosition().latitude, marker.getPosition().longitude));
                Intent inte = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(inte);

            });

        }
    }
    public void onMapReady(final GoogleMap map) {
        this.mMap = map;
    }
    public void findLocation(View v) throws IOException {

        EditText et = (EditText)findViewById(R.id.editText);
        String location = et.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = geocoder.getFromLocationName(location, 1);
        Address add = list.get(0);
        String locality = add.getLocality();
        LatLng ll = new LatLng(add.getLatitude(), add.getLongitude());
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
        mMap.moveCamera(update);
        if(marker != null)
            marker.remove();
        MarkerOptions markerOptions = new MarkerOptions()
                .title(locality)
                .position(new LatLng(add.getLatitude(), add.getLongitude()));
        marker = mMap.addMarker(markerOptions);

    }
}

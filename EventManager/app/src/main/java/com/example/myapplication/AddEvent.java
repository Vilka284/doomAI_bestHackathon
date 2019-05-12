package com.example.myapplication;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddEvent extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    double lattitude;
    double longitude;
    public static String currentEventString, currentEventDescriptionString;

    ArrayList<Event> eventsMarker = new ArrayList<>();

    TextInputLayout currentEvent;
    TextInputLayout currentEventDescription;
    TextInputEditText currentEventEdit;
    TextInputEditText currentEventDescriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        currentEvent = findViewById(R.id.textAddEventName);
        currentEventDescription = findViewById(R.id.textAddDescription);
        currentEventEdit = findViewById(R.id.AddEventEditText);;
        currentEventDescriptionEdit = findViewById(R.id.AddDescriptionEditText);
        Button AddEvent = findViewById(R.id.AddEvent);
        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsMarker.add(new Event(currentEventString, currentEventDescriptionString, lattitude, longitude));
                MainActivity.setEvents(eventsMarker);

                Intent buttonIntent = new Intent(AddEvent.this, MainActivity.class);
                startActivity(buttonIntent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapAdd);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(49.7, 23.9)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));

    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}

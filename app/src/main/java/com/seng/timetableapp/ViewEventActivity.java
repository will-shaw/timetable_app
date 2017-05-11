package com.seng.timetableapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ViewUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import domain.TTEvent;

public class ViewEventActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private TTEvent ttEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_event);
        ttEvent = (TTEvent) getIntent().getSerializableExtra("ttEvent");
        mapView = (MapView) findViewById(R.id.viewEventMap);

        if (ttEvent != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("View " + ttEvent.getId());
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            fillDetails();

            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        updateMap();
    }

    private void fillDetails() {
        TextView pCode = (TextView) findViewById(R.id.viewPaperCode);
        TextView pTimeDate = (TextView) findViewById(R.id.viewTimeDate);
        TextView pName = (TextView) findViewById(R.id.viewPaperTitle);
        TextView pRoomCode = (TextView) findViewById(R.id.viewRoomCode);
        TextView pColour = (TextView) findViewById(R.id.viewColour);
        TextView pLocation = (TextView) findViewById(R.id.viewLocation);

        pCode.setText(ttEvent.getId());
        pTimeDate.setText(ttEvent.getDate().toString());
        pName.setText(ttEvent.getPaperName());
        pRoomCode.setText(ttEvent.getRoomCode());
        pColour.setBackgroundColor(Color.RED); // TODO: Remove static assignment.
        pLocation.setText(ttEvent.getGmapsUrl());
    }

    private void updateMap() {
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        MapsInitializer.initialize(ViewEventActivity.this);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ttEvent.getLat(), ttEvent.getLon()), 10);
        map.moveCamera(cameraUpdate);

        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(ttEvent.getLat(), ttEvent.getLon()), 17);
                map.animateCamera(cameraUpdate);

                map.addMarker(new MarkerOptions()
                        .position(new LatLng(ttEvent.getLat(), ttEvent.getLon()))
                        .title(ttEvent.getRoomCode())
                        .snippet(ttEvent.getBuildingName()));
                mapView.onResume();
            }
        });
        mapView.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.edit:
                Intent intent = new Intent(ViewEventActivity.this, EditEventActivity.class);
                // TODO: Pass through either Event, or just the ID for the next activity to pull.
                intent.putExtra("ttEvent", ttEvent);
                startActivity(intent);
                break;

            case android.R.id.home:
                this.finish();
                break;

            default:
                break;
        }
        return true;
    }

}

package com.example.prateek.androidapp;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {

    private final LatLng LOCATION_JUIT=new LatLng(31.0163,77.0702);
    private final LatLng LOCATION_DELHI=new LatLng(28.6139,77.2090);

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map=((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(LOCATION_JUIT).title("I STudy Here"));

    }

    public void city(View v){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(LOCATION_JUIT, 14);
        map.animateCamera(update);
    }

    public void survey(View v){
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(LOCATION_DELHI, 9);
        map.animateCamera(update);
    }
}

package com.example.cityguide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event);
        TextView eventNameTV = findViewById(R.id.eventNameTV);
        TextView descriptionTV = findViewById(R.id.descriptionTV);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        String itemID = getIntent().getStringExtra("id");
        int itemNameID = getResources().getIdentifier(itemID + "Name", "string" , getPackageName());
        int itemDescriptionID = getResources().getIdentifier(itemID + "Description", "string" , getPackageName());
        int ivID = getResources().getIdentifier(itemID + "a", "drawable", getPackageName());
        int iv2ID = getResources().getIdentifier(itemID + "b", "drawable", getPackageName());
        int iv3ID = getResources().getIdentifier(itemID + "c", "drawable", getPackageName());

        eventNameTV.setText(getString(itemNameID));
        descriptionTV.setText(getString(itemDescriptionID));
        imageView.setImageResource(ivID);
        imageView2.setImageResource(iv2ID);
        imageView3.setImageResource(iv3ID);

        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        // 51.109126, 17.032759
        map.addMarker(new MarkerOptions().position(new LatLng(51.109126, 17.032759)).title("Marker"));
    }
}

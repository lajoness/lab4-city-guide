package com.example.cityguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        super.onStart();

        Button eventsButton = findViewById(R.id.eventsButton);
        Button placesButton = findViewById(R.id.placesButton);
        Button tourButton = findViewById(R.id.tourButton);
        Button accButton = findViewById(R.id.accButton);

        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
                listIntent.putExtra("type", R.string.events);
                startActivity(listIntent);
            }
        });
        placesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
                listIntent.putExtra("type", R.string.places);
                startActivity(listIntent);
            }
        });
        tourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
                listIntent.putExtra("type", R.string.tours);
                startActivity(listIntent);
            }
        });
        accButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(getApplicationContext(), ListActivity.class);
                listIntent.putExtra("type", R.string.acc);
                startActivity(listIntent);
            }
        });

    }
}
